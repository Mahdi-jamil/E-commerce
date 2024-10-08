package com.devesta.ecommerce.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaPaymentTopicConfig {

    @Value("${kafka.topic.payment}")
    private String topicName;
    @Bean
    public NewTopic paymentTopic(){
        return TopicBuilder
                .name(topicName)
                .build();
    }

}
