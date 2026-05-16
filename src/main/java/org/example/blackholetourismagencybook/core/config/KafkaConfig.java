package org.example.blackholetourismagencybook.core.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    public static final String TOPIC_TICKET_ISSUED = "topic-ticket-issued";

    @Bean
    public NewTopic ticketIssuedTopic(){
        return TopicBuilder.name(TOPIC_TICKET_ISSUED)
                .partitions(1)
                .replicas(1)
                .build();
    }
}
