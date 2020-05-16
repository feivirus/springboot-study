package com.feivirus.common.kafka.message;

import com.feivirus.common.kafka.message.body.Body;
import com.feivirus.common.utils.validate.Validatable;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author feivirus
 */
@Data
public class KafkaMessage<T extends Body> implements Serializable, Validatable {

    private static final long serialVersionUID = 3485539750188721177L;

    private String messageType;

    private Source source;

    private Date sendTime;

    private T body;

    @Override
    public boolean validate() {
        if (messageType == null ||
                source == null ||
                sendTime == null ||
                body == null) {
            return false;
        }
        return body.validate();
    }
}
