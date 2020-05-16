package com.feivirus.common.kafka.config;

import lombok.Data;

/**
 * @author feivirus
 */
@Data
public class KafkaConfig {
    /**
     * ip:port,ip:port
     */
    private String serversConfig;
}
