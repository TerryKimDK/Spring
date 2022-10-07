package exampleboot.mapper;

import java.time.LocalDateTime;
import java.util.Date;

public class Member {
    private int mno;
    private String mname;
    private String email;
    private String pwd;
    private Date cre_date;

    private Date mod_date;

    public Date getMod_date() {
        return mod_date;
    }

    public void setMod_date(Date mod_date) {
        this.mod_date = mod_date;
    }

    public LocalDateTime getRegisterDateTime() {
        return registerDateTime;
    }

    public void setRegisterDateTime(LocalDateTime registerDateTime) {
        this.registerDateTime = registerDateTime;
    }

    private LocalDateTime registerDateTime;


    public Member(String email, String pwd, String mname, Date cre_date) {
        this.email = email;
        this.pwd = pwd;
        this.mname = mname;
        this.cre_date = cre_date;
    } // 생성자

    public Member() {

    }

    public Member(String email, String password, String name, LocalDateTime now) {
        this.email = email;
        this.pwd = pwd;
        this.mname = mname;
        this.registerDateTime = registerDateTime;
    }


    public int getMno() {
        return mno;
    }

    public void setMno(int mno) {
        this.mno = mno;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getEmail() {
        return email;
    }

    public Member setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPwd() {
        return pwd;
    }

    public Member setPwd(String pwd) {
        this.pwd = pwd;
        return this;
    }

    public Date getCre_date() {
        return cre_date;
    }

    public void setCre_date(Date cre_date) {
        this.cre_date = cre_date;
    }
//
//    public void ChangePassword(String oldPassword, String newPassword){
//        if(!pwd.equals(oldPassword)){
//            throw new WrongIdPasswordException();
//        }
//        this.pwd = newPassword;
//    }
}
