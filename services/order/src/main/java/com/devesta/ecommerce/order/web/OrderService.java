package com.devesta.ecommerce.order.web;

import com.devesta.ecommerce.customer.CustomerClient;
import com.devesta.ecommerce.customer.CustomerResponse;
import com.devesta.ecommerce.exceptions.BusinessException;
import com.devesta.ecommerce.exceptions.OrderNotFoundException;
import com.devesta.ecommerce.kafka.OrderConfirmation;
import com.devesta.ecommerce.kafka.OrderProducer;
import com.devesta.ecommerce.order.model.Order;
import com.devesta.ecommerce.order.model.OrderMapper;
import com.devesta.ecommerce.order.model.OrderRequest;
import com.devesta.ecommerce.order.model.OrderResponse;
import com.devesta.ecommerce.orderline.model.OrderLineRequest;
import com.devesta.ecommerce.orderline.web.OrderLineService;
import com.devesta.ecommerce.payment.PaymentClient;
import com.devesta.ecommerce.payment.PaymentRequest;
import com.devesta.ecommerce.product.ProductClient;
import com.devesta.ecommerce.product.PurchaseRequest;
import com.devesta.ecommerce.product.PurchaseResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository repository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderLineService orderLineService;
    private final PaymentClient paymentClient;
    private final OrderMapper mapper;
    private final OrderProducer producer;

    public Integer createOrder(OrderRequest request) {
        CustomerResponse customerResponse = customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException(
                        String.format("customer with ID: %s not found to complete order", request.customerId())
                ));

        List<PurchaseResponse> purchaseResponses = productClient.purchaseProducts(request.products());

        Order order = repository.save(mapper.toOrder(request));

        for (PurchaseRequest purchaseRequest : request.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity())
            );
        }

        paymentClient.requestOrderPayment(new PaymentRequest(
                request.amount(),
                request.paymentMethod(),
                order.getId(),
                order.getReference(),
                customerResponse
        ));

        producer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customerResponse,
                        purchaseResponses
                )
        );


        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toOrderResponse)
                .collect(Collectors.toList());
    }

    public OrderResponse findOrderById(Integer id) {
        return repository.findById(id)
                .map(mapper::toOrderResponse)
                .orElseThrow(() -> new OrderNotFoundException(
                        String.format("Order with id:%d not found", id)
                ));
    }
}
