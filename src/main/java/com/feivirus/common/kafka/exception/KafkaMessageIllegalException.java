package com.feivirus.common.kafka.exception;

/**
 * @author feivirus
 */
public class KafkaMessageIllegalException extends RuntimeException {
    public KafkaMessageIllegalException(String message) {
        super(message);
    }
}
