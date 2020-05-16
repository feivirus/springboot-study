package com.feivirus.common.kafka.message;

/**
 * @author feivirus
 */
public enum KafkaMessageTypeEnum {
    USER_INFO("user_info");

    private String value;

    KafkaMessageTypeEnum(String type) {
        this.value = type;
    }

    public String getValue() {
        return value;
    }
}
