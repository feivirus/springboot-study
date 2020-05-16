package com.feivirus.common.kafka.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;

/**
 * @author feivirus
 */
public interface KafkaMessageListener<K, V> {
    /**
     * 监听原始消息
     * @param record
     */
    void listen(ConsumerRecord<K, V> record);
}
