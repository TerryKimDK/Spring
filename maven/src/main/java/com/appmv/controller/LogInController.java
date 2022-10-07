package com.appmv.controller;

import com.appmv.spring.Member;
import com.appmv.spring.MySqlMemberDao;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LogInController {

    //밑에 주석처리하고 여기만 어노테이션 쓰면 필드로 사용가능
    //@Autowired
    private MySqlMemberDao memberDao;

    public LogInController setMemberDao(MySqlMemberDao memberDao) {
        this.memberDao = memberDao;
        return this;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String form(Member loginCommand, HttpSession session) {
        if(session.getAttribute("authInfo") != null) {
            if(((Member)session.getAttribute("authInfo")).getEmail() != loginCommand.getEmail()){
                return "/member/MemberList";
            }
        }
        return "/member/loginForm";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String submit(Member loginCommand, HttpSession session, Errors errors) {
        if(errors.hasErrors()) {
            return "/member/loginForm";
        }
        try {

            Member authInfo = memberDao.exist(loginCommand.getEmail(), loginCommand.getPwd());
            System.out.println(authInfo);

            if(authInfo == null){
                throw new Exception("회원자 없음!");
            }
            session.setAttribute("authInfo", authInfo);
            return "redirect:/member/list";
        } catch (Exception e) {
            return "/member/loginForm";
        }

    }
}