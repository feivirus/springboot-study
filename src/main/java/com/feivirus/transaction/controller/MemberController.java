package com.feivirus.transaction.controller;

import com.feivirus.transaction.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author feivirus
 */
@Controller
public class MemberController {
    @Autowired
    private MemberService memberService;

    @RequestMapping("insert")
    @ResponseBody
    private String insert(String name) {
        memberService.insertMember(name, name, 1);
        return "ok";
    }
}
