package com.appmv.main;

import com.appmv.spring.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainForXmlSpring3 {
    private static ApplicationContext ctx = null;

    public static void main(String[] args) throws Exception {
        ctx = new FileSystemXmlApplicationContext("appCtx1.xml");
//                new GenericXmlApplicationContext("classpath:appCtx.xml");
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
            } else if (command.startsWith("list ")) {
                processListCommand(command.split(" "));
                continue;
            } else if (command.startsWith("version ")) {
                processVersionCommand(command.split(" "));
                continue;
            }
            errorLog();

        }
    }

    private static void newCommand(String[] args) {
        if (args.length != 5) {
            errorLog();
            return;
        }
        MemberRegisterService regSvc =
                ctx.getBean("memberRegSvc", MemberRegisterService.class);
        RegisterRequest req = new RegisterRequest();
        req.setEmail(args[1]);
        req.setName(args[2]);
        req.setPassword(args[3]);
        req.setConfirmPassword(args[4]);
        if (!req.isPasswordEqualToConfirmPassword()) {
            System.out.println("암호와 확인이 일치하지 않습니다.\n");
            return;
        }
        try {
            regSvc.regist(req);
            System.out.println("등록했습니다.\n");
        } catch (DuplicateMemberException e) {
            System.out.println("이미 존재하는 이메일입니다.\n");
        }
    }

    private static void changeCommand(String[] args) {
        if (args.length != 4) {
            errorLog();
            return;
        }
        ChangePasswordService changePwdSvc =
                ctx.getBean("changePwdSvc", ChangePasswordService.class);
        try {
            changePwdSvc.ChangePassword(args[1], args[2], args[3]);
            System.out.println("암호를 변경하였습니다.\n");
        } catch (MemberNotFoundException e) {
            System.out.println("존재하지 않는 이메일 입니다.\n");
        } catch (WrongIdPasswordException e) {
            System.out.println("이메일과 암호가 일치하지 않습니다. \n");
        }
    }

    private static void processListCommand(String[] args) {
        MemberListService listPrinter =
                ctx.getBean("listPrinter", MemberListService.class);
        try{
            listPrinter.memberList();
            System.out.println("member List 출력 !!!");
        }catch (MemberNotFoundException e){
            System.out.println("리스트가 존재하지 않습니다.");
        }

    }

    private static void processVersionCommand(String[] args) {
        VersionPrinter versionPrinter =
                ctx.getBean("versionPrinter", VersionPrinter.class);
            versionPrinter.print();
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
