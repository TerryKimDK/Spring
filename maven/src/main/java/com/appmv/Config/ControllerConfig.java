package com.appmv.Config;

import com.appmv.controller.LogInController;
import com.appmv.controller.MemberListController;
import com.appmv.spring.MySqlMemberDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ControllerConfig implements WebMvcConfigurer {
//    @Autowired
    private MySqlMemberDao memberDao;


    @Bean
    public LogInController loginController() {
        LogInController logInController = new LogInController();
        logInController.setMemberDao(getMemberDao());
        return logInController;
    }

    @Bean
    public MemberListController memberListController() {
        MemberListController memberListController = new MemberListController();
        memberListController.setMemberDao(getMemberDao());
        return memberListController;
    }

    public MySqlMemberDao getMemberDao() {
        return memberDao;
    }

    public void setMemberDao(MySqlMemberDao memberDao) {
        this.memberDao = memberDao;
    }
}