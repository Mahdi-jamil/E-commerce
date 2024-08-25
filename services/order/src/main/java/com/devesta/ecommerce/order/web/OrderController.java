package com.devesta.ecommerce.order.web;

import com.devesta.ecommerce.order.model.OrderRequest;
import com.devesta.ecommerce.order.model.OrderResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@AllArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping
    public ResponseEntity<Integer> createOrder(
            @RequestBody @Valid OrderRequest request
    ){
        return new ResponseEntity<>(service.createOrder(request), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll(){
        return new ResponseEntity<>(service.findAll(),HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<OrderResponse> findOrderById(@PathVariable Integer id){
        return new ResponseEntity<>(service.findOrderById(id),HttpStatus.OK);
    }

}
