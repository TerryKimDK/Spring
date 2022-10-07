package com.appmv.spring;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.stereotype.Repository;


@Repository
public class MySqlMemberDao {
    @Autowired
    private HikariDataSource dataSource;
    public void setDataSource(HikariDataSource dataSource) {
        // TODO Auto-generated method stub
        this.dataSource = dataSource;
    }
    public Member exist(String email, String pwd) throws Exception {
        // TODO Auto-generated method stub
        Connection conn = dataSource.getConnection();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Member member = null;
        String sql = "SELECT EMAIL,PWD FROM MEMBER WHERE EMAIL=? AND PWD=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, pwd);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                member = new Member().setEmail(rs.getString(1)).setPwd(rs.getString(2));
            }
        }catch(SQLException e) {
            throw e;
        }
        return member;
    }

    public List<Member> selectList() throws Exception {
        // TODO Auto-generated method stub

        Connection conn = dataSource.getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Member> members = null;

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM MEMBER ORDER BY EMAIL ASC");
            members = new ArrayList<Member>();
            while(rs.next()) {
                members.add(new Member().setEmail(rs.getString("email")).setPwd(rs.getString("pwd")));
            }
        }catch(Exception e) {
            throw e;
        }
        return members;
    }

    public int update(Member member) throws Exception{

        return -1;
    }
}