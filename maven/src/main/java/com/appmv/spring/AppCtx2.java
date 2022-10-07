package com.appmv.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class AppCtx2 {
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private MemberPrinter memberPrinter;

    @Bean
    public MemberRegisterService memberRegSvc(){
        return new MemberRegisterService(memberDao);
    }
    @Bean
    public ChangePasswordService changePwdSvc(){
        ChangePasswordService pwdSvc = new ChangePasswordService(memberDao);
        pwdSvc.setMemberDao(memberDao);
        return pwdSvc;
    }
    @Bean
    public MemberListService listPrinter(){
        return new MemberListService(memberDao, memberPrinter);
    }


}
