package com.feivirus.transaction.service;

import com.feivirus.transaction.dao.MemberMapper;
import com.feivirus.transaction.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author feivirus
 */
@Service
public class MemberService {
    @Autowired
    private MemberMapper memberMapper;

    @Transactional(rollbackFor = Exception.class)
    public int insertMember(String name, String sex, Integer age) {
        Member member = new Member();
        member.setAge(age);
        member.setName(name);
        member.setSex(sex);

        return memberMapper.insert(member);
    }
}
