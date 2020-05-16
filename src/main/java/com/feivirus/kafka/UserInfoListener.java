package com.feivirus.kafka;

import com.alibaba.fastjson.JSONObject;
import com.feivirus.common.kafka.listener.AbstractKafkaMessageListener;
import com.feivirus.common.kafka.message.KafkaMessage;
import com.feivirus.common.kafka.message.body.UserInfo;
import com.google.common.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author feivirus
 * UserInfo的消费者
 */
@Component
@Slf4j
public class UserInfoListener extends AbstractKafkaMessageListener<String, String, UserInfo> {

    @Override
    public Logger getLogger() {
        return LOGGER;
    }

    @Override
    public void handle(KafkaMessage<UserInfo> kafkaMessage) {
        UserInfo userInfo = kafkaMessage.getBody();

        //入库等业务逻辑
    }

    @KafkaListener(containerGroup = "${kafka.user.info.group.name}",
                    topics = "${kafka.user.info.topic.name}")
    @Override
    public void listen(ConsumerRecord<String, String> record) {
        doListen(record);
    }

    @Override
    public KafkaMessage<UserInfo> parseMessage(String value) {
        KafkaMessage<UserInfo> result = JSONObject.parseObject(value,
                new TypeToken<KafkaMessage<UserInfo>>(){}.getType());
        return result;
    }
}
