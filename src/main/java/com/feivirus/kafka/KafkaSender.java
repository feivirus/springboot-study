package com.feivirus.kafka;

import com.alibaba.fastjson.JSONObject;
import com.feivirus.common.kafka.message.KafkaMessage;
import com.feivirus.common.kafka.message.KafkaMessageTypeEnum;
import com.feivirus.common.kafka.message.Source;
import com.feivirus.common.kafka.message.body.Body;
import com.feivirus.common.kafka.message.body.UserInfo;
import com.feivirus.common.utils.enums.KafkaSourceEnum;
import kafka.utils.json.JsonObject;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author feivirus
 */
@Component
public class KafkaSender {

    private final Source source;

    public KafkaSender() {
        source = new Source();
        source.setKafkaSource(KafkaSourceEnum.SPRINGBOOT_STUDY.getKey());
    }

    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendUserInfo(UserInfo userInfo) {
         send(KafkaMessageTypeEnum.USER_INFO, "user_info", userInfo.getId().toString(), userInfo);
    }

    /**
     *
     * @param typeEnum
     * @param topic
     * @param key 用来做partition的键
     * @param body
     * @param <T>
     */
    private <T extends Body> void send(KafkaMessageTypeEnum typeEnum,
                                       String topic,
                                       String key,
                                       T body) {
        KafkaMessage<T> kafkaMessage = new KafkaMessage<>();

        kafkaMessage.setBody(body);
        kafkaMessage.setMessageType(typeEnum.getValue());
        kafkaMessage.setSendTime(new Date());
        kafkaMessage.setSource(source);

        String message = JSONObject.toJSONString(kafkaMessage);
        kafkaTemplate.send(topic, key, message);
    }
}
