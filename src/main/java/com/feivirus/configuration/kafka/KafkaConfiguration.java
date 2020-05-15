package com.feivirus.configuration.kafka;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;

/***
 * @author feivirus
 */
@Configuration
@EnableKafka
public class KafkaConfiguration {
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return null;
    }
}
