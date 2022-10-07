package com.appmv.controller;

import com.appmv.spring.MySqlMemberDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberListController {

    private MySqlMemberDao memberDao;
    public MemberListController setMemberDao(MySqlMemberDao memberDao) {
        this.memberDao = memberDao;
        return this;
    }

    @RequestMapping(value="member/list")
    public String list(Model model) throws Exception {
        model.addAttribute("members", memberDao.selectList());
        return "/member/MemberList";
    }
}