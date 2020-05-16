package com.feivirus.common.utils.enums;

/**
 * @author feivirus
 */
public enum KafkaSourceEnum implements BaseIntegerEnum {
    SPRINGBOOT_STUDY(1, "springboot_study");

    private Integer key;

    private String value;


    KafkaSourceEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }


    @Override
    public Integer getKey() {
        return key;
    }

    @Override
    public String getValue() {
        return value;
    }

    public KafkaSourceEnum getEnumBykey(Integer key) {
        if (key != null) {
            for(KafkaSourceEnum element : KafkaSourceEnum.values()) {
                if (element.key.equals(key)) {
                    return element;
                }
            }
        }
        return null;
    }
}
