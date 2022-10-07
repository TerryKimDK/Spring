package com.appmv.main;

import com.appmv.spring.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainForAssembler {
    public static void main(String[] args) throws IOException {
        BufferedReader rd =
                new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("명령어를 입력하세요");
            String command = rd.readLine();
            if (command.equalsIgnoreCase("exit")) {
                System.out.println("종료합니다.");
                break;
            }
            if (command.startsWith("new ")) {
                newCommand(command.split(" "));
                continue;
            } else if (command.startsWith("change ")) {
                changeCommand(command.split(" "));
                continue;
            }
            errorLog();
        }
    }

    private static Assembler assembler = new Assembler();

    private static void newCommand(String[] args) {
        if(args.length != 5){
            errorLog();
            return;
        }
        MemberRegisterService regSvc = assembler.getMemberRegisterService();
        RegisterRequest req = new RegisterRequest();
        req.setEmail(args[1]);
        req.setName(args[2]);
        req.setPassword(args[3]);
        req.setConfirmPassword(args[4]);
        String email= req.getEmail();
        System.out.println(email);

        if (!req.isPasswordEqualToConfirmPassword()) {
            System.out.println("암호와 확인이 일치하지 않습니다.\n");
            return;
        }
        try {
            regSvc.regist(req);
            System.out.println("mainforassem"+req.getEmail());
            System.out.println("등록했습니다.\n");
        } catch (DuplicateMemberException e) {
            System.out.println("이미 존재하는 이메일입니다.\n");
        }

    }

    private static void changeCommand(String[] args) {
        if(args.length != 4){
            errorLog();
            return;
        }
        ChangePasswordService ChangePwdSvc= assembler.getChangePasswordService(); //pwdSvc
        try{
            ChangePwdSvc.ChangePassword(args[1], args[2], args[3] );
        }catch (MemberNotFoundException e){
            System.out.println("존재하지 않는 이메일 입니다. \n");
        }catch (WrongIdPasswordException e) {
            System.out.println("이메일과 암호가 일치하지 일치하지 않습니다. \n");
        }
    }

    private static void errorLog() {
        System.out.println();
        System.out.println("잘못된 명령입니다. 아래 명령어 사용법을 확인하세요.");
        System.out.println("명령어 사용법:");
        System.out.println("new 이메일 이름 암호 암호확인");
        System.out.println("change 이메일 현재비번 변경비번");
        System.out.println();
    }

}

