package com.feivirus.common.kafka.message.body;

import lombok.Data;

/**
 * @author feivirus
 */
@Data
public class UserInfo implements Body {
    private Integer id;

    private String name;

    private Integer age;

    @Override
    public boolean validate() {
        return true;
    }
}
