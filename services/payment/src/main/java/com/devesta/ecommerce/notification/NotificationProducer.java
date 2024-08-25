package com.devesta.ecommerce.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationProducer {

    @Value("${kafka.topic.payment}")
    private String topicName;
    private final KafkaTemplate<String, PaymentConfirmation> kafkaTemplate;

    public void sendOrderConfirmation(PaymentConfirmation paymentConfirmation) {
        log.info("Sending payment notification with body {}", paymentConfirmation);
        Message<PaymentConfirmation> message = MessageBuilder
                .withPayload(paymentConfirmation)
                .setHeader(KafkaHeaders.TOPIC, topicName)
                .build();
        kafkaTemplate.send(message);
    }

}
