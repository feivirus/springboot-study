package com.feivirus.redpacket.service.impl;

import com.feivirus.redpacket.dao.UserAccountMapper;
import com.feivirus.redpacket.domain.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author feivirus
 * 对表t_user_account操作的服务
 */
@Service
public class UserAccountService {
    @Autowired
    private UserAccountMapper userAccountMapper;

    public UserAccount selectUserAccountById(Integer userId) {

        return userAccountMapper.selectByPrimaryKey(userId);
    }

    public int updateByPrimaryKey(UserAccount userAccount) {
        return userAccountMapper.updateByPrimaryKey(userAccount);
    }
}
