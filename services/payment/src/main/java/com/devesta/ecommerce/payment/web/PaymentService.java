package com.devesta.ecommerce.payment.web;

import com.devesta.ecommerce.notification.NotificationProducer;
import com.devesta.ecommerce.notification.PaymentConfirmation;
import com.devesta.ecommerce.payment.model.Payment;
import com.devesta.ecommerce.payment.model.PaymentMapper;
import com.devesta.ecommerce.payment.model.PaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository repository;
    private final PaymentMapper mapper;
    private final NotificationProducer producer;

    public Integer createPayment(PaymentRequest request) {
        Payment payment = repository.save(mapper.toPayment(request));

        producer.sendOrderConfirmation(
                new PaymentConfirmation(
                        request.orderReference(),
                        request.amount(),
                        request.paymentMethod(),
                        request.customer().name(),
                        request.customer().email()
                )
        );

        return payment.getId();
    }
}
