package com.appmv.Config;

import com.appmv.spring.MySqlMemberDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class MemberConfig {
    @Bean(destroyMethod = "close")
    public HikariDataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/webapp");
        dataSource.setUsername("root");
        dataSource.setPassword("1234");
        return dataSource;
    }
    @Bean
    public MySqlMemberDao memberDao() {
        MySqlMemberDao member = new MySqlMemberDao();
        member.setDataSource(dataSource());
        return member;
    }

}
