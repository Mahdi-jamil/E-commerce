package com.devesta.ecommerce.kafka;


import com.devesta.ecommerce.email.EmailService;
import com.devesta.ecommerce.kafka.order.OrderConfirmation;
import com.devesta.ecommerce.kafka.payment.PaymentConfirmation;
import com.devesta.ecommerce.notification.Notification;
import com.devesta.ecommerce.notification.NotificationRepository;
import com.devesta.ecommerce.notification.NotificationType;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationConsumer {

    private final NotificationRepository repository;
    private final EmailService emailService;

    @KafkaListener(topics = "${kafka.topic.payment}", groupId = "paymentGroup")
    public void consumerPaymentNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info("Consuming message from payment-topic {}", paymentConfirmation);

        repository.save(
                Notification.builder()
                        .type(NotificationType.PAYMENT_CONFIRMATION)
                        .paymentConfirmation(paymentConfirmation)
                        .notificationDate(LocalDateTime.now())
                        .build()
        );

        emailService.sendPaymentSuccessEmail(
                paymentConfirmation.customerEmail(),
                paymentConfirmation.customerName(),
                paymentConfirmation.totalAmount(),
                paymentConfirmation.orderReference()
        );
    }
    @KafkaListener(topics = "${kafka.topic.order}", groupId = "orderGroup")
    public void consumerOrderConfirmationNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info("Consuming message from order-topic {}", orderConfirmation);

        repository.save(
                Notification.builder()
                        .type(NotificationType.ORDER_CONFIRMATION)
                        .orderConfirmation(orderConfirmation)
                        .notificationDate(LocalDateTime.now())
                        .build()
        );

        emailService.sendOrderConfirmationEmail(
                orderConfirmation.customer().email(),
                orderConfirmation.customer().name(),
                orderConfirmation.totalAmount(),
                orderConfirmation.orderReference(),
                orderConfirmation.products()
        );
    }


}
