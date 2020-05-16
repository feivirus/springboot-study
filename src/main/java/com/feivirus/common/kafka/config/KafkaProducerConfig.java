package com.feivirus.common.kafka.config;

import lombok.Data;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.support.ProducerListener;

/**
 * @author feivirus
 */
@Data
public class KafkaProducerConfig<K, V> extends KafkaConfig {
    private ProducerListener<K, V> producerListener;

    private static final Class<? extends Serializer> DEFAULT_KEY_SERIALIZE = IntegerSerializer.class;

    private static final Class<? extends Serializer> DEFAULT_VALUE_SERIALIZE = StringSerializer.class;

    private Class<? extends Serializer> keySerialize = DEFAULT_KEY_SERIALIZE;

    private Class<? extends Serializer> valueSerilize = DEFAULT_VALUE_SERIALIZE;


}
