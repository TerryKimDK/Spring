package com.appmv.spring;

public class Assembler {
    private MemberRegisterService regSvc;
    private ChangePasswordService pwdSvc;
    private MemberDao memberDao;
    public Assembler(){
        memberDao= new MemberDao();
        regSvc = new MemberRegisterService(memberDao); // 생성자를 통해서 객체주입
        pwdSvc = new ChangePasswordService(memberDao);
        pwdSvc.setMemberDao(memberDao);
    }
    public MemberDao getMemberDao(){
        return memberDao;
    }

    public MemberRegisterService getMemberRegisterService(){

        return regSvc;
    }

    public ChangePasswordService getChangePasswordService(){

        return pwdSvc;
    }

}
