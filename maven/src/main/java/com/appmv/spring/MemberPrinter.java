package com.appmv.spring;

public class MemberPrinter {

    public void print(Member member) {
        // TODO Auto-generated method stub
        System.out.printf(
                "회원 정보: 아이디=%d, 이메일=%s, 이름=%s, 등록일=%tF\n",
                member.getMno(), member.getEmail(),
                member.getMname(), member.getCre_date());

    }

}