package com.feivirus.common.kafka.listener;

import com.feivirus.common.kafka.message.KafkaMessage;
import com.feivirus.common.kafka.message.body.Body;

/**
 * @author feivirus
 */
public interface KafkaMessageHandler<T extends Body> {
    /**
     * 处理消息
     * @param kafkaMessage
     */
    void handle(KafkaMessage<T> kafkaMessage);
}
