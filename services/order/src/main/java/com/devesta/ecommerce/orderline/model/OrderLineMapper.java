package com.devesta.ecommerce.orderline.model;

import com.devesta.ecommerce.order.model.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest orderLineRequest) {
        return OrderLine.builder()
                .id(orderLineRequest.orderId())
                .order(
                        Order.builder()
                                .id(orderLineRequest.orderId())
                                .build()
                ).quantity(orderLineRequest.quantity())
                .productId(orderLineRequest.productId())
                .build();
    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse(
                orderLine.getId(),
                orderLine.getQuantity()
        );
    }
}
