package com.devesta.ecommerce.web;

import com.devesta.ecommerce.exceptions.CustomerNotFoundException;
import com.devesta.ecommerce.model.Customer;
import com.devesta.ecommerce.model.CustomerMapper;
import com.devesta.ecommerce.model.CustomerRequest;
import com.devesta.ecommerce.model.CustomerResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper mapper;

    public String createCustomer(CustomerRequest request) {
        Customer customer = customerRepository.save(mapper.toCustomer(request));
        return customer.getId();
    }

    public void updateCustomer(CustomerRequest request) {
        Customer customer = customerRepository.findById(request.id())
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("Customer with id: %s not found", request.id()))
                );
        updateCustomerWithRequest(customer, request);
        customerRepository.save(customer);
    }

    private void updateCustomerWithRequest(Customer customer, CustomerRequest request) {
        if (StringUtils.isNotBlank(request.name()))
            customer.setName(request.name());
        if (StringUtils.isNotBlank(request.email()))
            customer.setEmail(request.email());
        if (request.address() != null)
            customer.setAddress(request.address());
    }

    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(mapper::fromCustomer)
                .collect(Collectors.toList());
    }

    public CustomerResponse findById(String id) {
        return customerRepository.findById(id)
                .map(mapper::fromCustomer)
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("Customer with id: %s not found", id))
                );
    }

    public Boolean existsById(String id) {
        return customerRepository.findById(id).isPresent();
    }

    public void deleteCustomer(String id) {
        customerRepository.deleteById(id);
    }
}
