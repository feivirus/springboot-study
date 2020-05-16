package com.feivirus.common.kafka.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ErrorHandler;
import org.springframework.kafka.listener.LoggingErrorHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author feivirus
 * 工厂模式
 * 主要创建KafkaListenerContainerFactory消费者,KafkaAdmin,KafkaTemplate
 */
public class KafkaFactory<K, V> {
    public static <K, V> KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<K, V>>
            kafkaListenerContainerFactory(KafkaConsumerConfig<K, V> config) {
        ConcurrentKafkaListenerContainerFactory<K, V> factory = new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(consumerFactory(config));
        factory.setConcurrency(config.getConcurrency());
        factory.getContainerProperties().setPollTimeout(3000);

        ErrorHandler errorHandler = config.getErrorHandler();
        if (errorHandler == null) {
            errorHandler = new LoggingErrorHandler();
        }
        factory.setErrorHandler(errorHandler);
        return factory;
    }

    public static KafkaAdmin kafkaAdmin(KafkaConfig config) {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, config.getServersConfig());
        KafkaAdmin kafkaAdmin = new KafkaAdmin(props);
        kafkaAdmin.setFatalIfBrokerNotAvailable(true);
        return kafkaAdmin;
    }

    public static <K, V> KafkaTemplate<K, V> kafkaTemplate(KafkaProducerConfig<K, V> config) {
        return new KafkaTemplate<K, V>(producerFactory(config));
    }

    private static <K, V>ProducerFactory<K, V> producerFactory(KafkaProducerConfig<K, V> config) {
        return new DefaultKafkaProducerFactory<>(producerConfigs(config));
    }

    private static <K, V> Map<String, Object> producerConfigs(KafkaProducerConfig<K, V> config) {
        Map<String, Object> props = new HashMap<>();

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, config.getServersConfig());
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        props.put(ProducerConfig.RETRIES_CONFIG, 3);
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, config.getKeySerialize());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, config.getValueSerilize());

        return props;
    }

    private static <K, V>ConsumerFactory<K, V> consumerFactory(KafkaConsumerConfig<K, V> config) {
        return new DefaultKafkaConsumerFactory<K, V>(consumerConfigs(config));
    }

    private static <K, V> Map<String, Object> consumerConfigs(KafkaConsumerConfig<K, V> config) {
        Map<String, Object> props = new HashMap<>();

        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, config.getServersConfig());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, config.getGroupId());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        if (config.getPartitionAssignorClass() != null) {
            props.put(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG,
                    Collections.singletonList(config.getPartitionAssignorClass()));
        }
        return props;
    }
}
