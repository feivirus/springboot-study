package com.feivirus.configuration.kafka;

import com.feivirus.common.kafka.config.KafkaConfig;
import com.feivirus.common.kafka.config.KafkaConsumerConfig;
import com.feivirus.common.kafka.config.KafkaFactory;
import com.feivirus.common.kafka.config.KafkaProducerConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.StickyAssignor;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

/***
 * @author feivirus
 * 工厂模式配置
 */
@Configuration
@EnableKafka
public class KafkaConfiguration {
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        KafkaProducerConfig<String, String> config = new KafkaProducerConfig<>();
        config.setServersConfig("127.0.0.1");
        config.setKeySerialize(StringSerializer.class);
        config.setValueSerilize(StringSerializer.class);
        return KafkaFactory.kafkaTemplate(config);
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>>
            kafkaListenerContainerFactory() {
        KafkaConsumerConfig<String, String> config = new KafkaConsumerConfig<>();

        config.setConcurrency(1);
        config.setGroupId("springboot-study.dev");
        config.setServersConfig("127.0.0.1");
        config.setPartitionAssignorClass(StickyAssignor.class);
        //config.setErrorHandler();
        return KafkaFactory.kafkaListenerContainerFactory(config);
    }

    @Bean
    public KafkaAdmin kafkaAdmin() {
        KafkaConfig kafkaConfig = new KafkaConfig();
        kafkaConfig.setServersConfig("127.0.0.1");
        return KafkaFactory.kafkaAdmin(kafkaConfig);
    }

    /**
     * 创建topic
     * @return
     */
    @Bean
    public NewTopic topic() {
        return new NewTopic("springboot_study_hello", 1, (short)1);
    }
}
