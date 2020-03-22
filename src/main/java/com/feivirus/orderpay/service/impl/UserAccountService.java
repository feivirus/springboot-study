package com.feivirus.orderpay.service.impl;

import com.feivirus.orderpay.dao.UserAccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author feivirus
 */
@Component
public class UserAccountService {
    @Autowired
    private UserAccountMapper userAccountMapper;
}
