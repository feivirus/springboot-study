package com.feivirus.common.kafka.message;

import com.feivirus.common.utils.enums.KafkaSourceEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @author feivirus
 */
@Data
public class Source implements Serializable {
    private static final long serialVersionUID = -3705531971497245339L;

    /**
     * 消息发送方
     * {@link KafkaSourceEnum}
     */
    private Integer kafkaSource;


    /**
     * 发送方用户标识
     */
    private String userId;

    /**
     * 时间来源
     * {@link com.feivirus.common.utils.enums.FromType}
     */
    private String fromType;
}
