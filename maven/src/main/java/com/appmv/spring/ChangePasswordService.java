package com.appmv.spring;

public class ChangePasswordService {
    private MemberDao memberDao;

    public ChangePasswordService(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }
    public void ChangePassword(String email, String oldPwd,
                               String newPwd) throws MemberNotFoundException {
        Member member = memberDao.selectByEmail(email);
        if(member == null) throw new MemberNotFoundException(); // 예외가 발생하면 아래로 못내려감
        member.ChangePassword(oldPwd, newPwd);
        memberDao.update(member);
        System.out.println("비번 바꾸기 성공");
    }
}
