package com.feivirus.common.kafka.config;

import lombok.Data;
import org.apache.kafka.clients.consumer.internals.PartitionAssignor;
import org.springframework.kafka.listener.ErrorHandler;

/**
 * @author feivirus
 */
@Data
public class KafkaConsumerConfig<K, V> extends KafkaConfig {
    private String groupId;

    private Integer concurrency;

    private ErrorHandler errorHandler;

    private Class<? extends PartitionAssignor> partitionAssignorClass;
}
