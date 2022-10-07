package com.appmv.spring;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class MemberDao {
    private static int nextId = 0;

    private Map<String, Member> map = new HashMap<>();

    public Member selectByEmail(String email){
        return map.get(email);
    }
    public void insert(Member member){
        member.setMno(++nextId);
        map.put(member.getEmail(), member);
    }
    public void update(Member member){
        map.put(member.getEmail(), member);
    }

    public Collection<Member> selectAll() {
//        return map.values();
        HikariDataSource datasource = null;
        List<Member> data = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            conn = datasource.getConnection();
            pstmt = conn.prepareStatement("select  * from members order by mno desc");
            rs = pstmt.executeQuery();
            while(rs.next()){
                LocalDateTime date = LocalDateTime.parse(rs.getString(5),
                        DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm:ss") );
                Member member = new Member(rs.getString(2), rs.getString(3),
                        rs.getString(4), date);
                member.setMno(rs.getInt(1));
                data.add(member);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }
        return data;
    }
}
