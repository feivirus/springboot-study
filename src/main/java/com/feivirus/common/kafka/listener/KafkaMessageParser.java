package com.feivirus.common.kafka.listener;

import com.feivirus.common.kafka.message.KafkaMessage;
import com.feivirus.common.kafka.message.body.Body;

/**
 * @author feivirus
 */
public interface KafkaMessageParser<T extends Body, V> {
    /**
     * 解析消息
     * @param value
     * @return
     */
    KafkaMessage<T> parseMessage(V value);
}
