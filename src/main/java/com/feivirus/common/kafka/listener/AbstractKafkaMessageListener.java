package com.feivirus.common.kafka.listener;

import com.feivirus.common.kafka.exception.KafkaMessageIllegalException;
import com.feivirus.common.kafka.message.KafkaMessage;
import com.feivirus.common.kafka.message.body.Body;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;

/**
 * @author feivirus
 * 模板方法模式
 *
 */
public abstract class AbstractKafkaMessageListener<K, V, T extends Body> implements
            KafkaMessageHandler<T>, KafkaMessageListener<K, V>, KafkaMessageParser<T, V> {
    public abstract Logger getLogger();

    public void doListen(ConsumerRecord<K, V> record) {
        getLogger().info("[kafka] receive message {}", record);

        if (isBlankMessage(record.value())) {
            getLogger().info("[kafka] receive blank message, {}", record.topic());
        }

        //解析消息
        KafkaMessage<T> kafkaMessage = null;
        try {
            kafkaMessage = parseMessage(record.value());
        } catch (Exception ex) {
            throw new KafkaMessageIllegalException("[kafka] parse message error: {}" + ex.getMessage());
        }

        //校验消息
        if (kafkaMessage == null || !kafkaMessage.validate()) {
            getLogger().info("[kafka] validate message error, topic: {}, value: {}", record.topic(), record.value());
            throw  new KafkaMessageIllegalException("[kafka] validate message error");
        }

        //处理消息
        try {
            handle(kafkaMessage);
        } catch (Exception ex) {
            getLogger().info("[kafka] handle message error: {}", ex.getMessage());
            throw new KafkaMessageIllegalException("[kafka] handle message error: {}" + ex.getMessage());
        }
    }

    private boolean isBlankMessage(V value) {
        if (value == null) {
            return true;
        }

        if (value instanceof String) {
            String msg = ((String) value).trim();

            if (msg.isEmpty()) {
                return true;
            }

            if ("{}".equals(msg)) {
                return true;
            }
        }

        return false;
    }
}
