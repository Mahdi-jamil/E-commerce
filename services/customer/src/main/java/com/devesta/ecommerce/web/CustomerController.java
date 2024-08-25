package com.devesta.ecommerce.web;


import com.devesta.ecommerce.model.CustomerRequest;
import com.devesta.ecommerce.model.CustomerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<String> createCustomer(
            @RequestBody @Validated CustomerRequest request
    ){
        return new ResponseEntity<>(customerService.createCustomer(request),HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<Void> updateCustomer(
            @RequestBody @Validated CustomerRequest request
    ){
        customerService.updateCustomer(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllCustomers(){
        return new ResponseEntity<>(customerService.getAllCustomers(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable String id){
        return new ResponseEntity<>(customerService.findById(id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String id){
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> existsById(@PathVariable String id){
        return new ResponseEntity<>(customerService.existsById(id),HttpStatus.OK);
    }


}
