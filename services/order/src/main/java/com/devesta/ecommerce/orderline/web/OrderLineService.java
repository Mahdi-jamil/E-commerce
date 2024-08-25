package com.devesta.ecommerce.orderline.web;

import com.devesta.ecommerce.orderline.model.OrderLine;
import com.devesta.ecommerce.orderline.model.OrderLineMapper;
import com.devesta.ecommerce.orderline.model.OrderLineRequest;
import com.devesta.ecommerce.orderline.model.OrderLineResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderLineService {

    private final OrderLineRepository repository;
    private final OrderLineMapper mapper;

    public Integer saveOrderLine(OrderLineRequest orderLineRequest) {
        OrderLine orderLine = repository.save(mapper.toOrderLine(orderLineRequest));
        return orderLine.getId();
    }

    public List<OrderLineResponse> findByOrderId(Integer orderId) {
        return repository.findAllByOrderId(orderId)
                .stream()
                .map(mapper::toOrderLineResponse)
                .collect(Collectors.toList());
    }
}
