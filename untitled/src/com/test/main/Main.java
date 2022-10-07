package com.test.main;

import com.test.account.AccountService;
import com.test.admin.AdminLogin;
import com.test.chat.ServerChat;
import com.test.customer.CustomerService;
import com.test.transfer.LoginMain;

import java.util.Calendar;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CustomerService customerService = new CustomerService();
        AccountService accountService = new AccountService();
        LoginMain loginMain = new LoginMain();
        AdminLogin admin = new AdminLogin();
//        new Server(7979);
        Scanner sc = new Scanner(System.in);

        while (true) {
            Calendar today = Calendar.getInstance();
            int year = today.get(Calendar.YEAR);
            int month = today.get(Calendar.MONTH);
            int date = today.get(Calendar.DATE);
            int hour12 = today.get(Calendar.HOUR);
            int minute = today.get(Calendar.MINUTE);
            int second = today.get(Calendar.SECOND);

            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("안녕하세요 DK 은행 입니다.");
            System.out.println("오늘은 " + year + "년 " + (month + 1) + "월 " + date + "일");
            System.out.println("현재 시각은 " + hour12 + "시 " + minute + "분 " + second + "초 입니다.");
            System.out.println("번호를 입력해 원하시는 서비스를 선택해주세요.");
            System.out.println("\n1. 고객관리");
            System.out.println("2. 통장개설");
            System.out.println("3. 입출금 및 계좌이체");
            System.out.println("4. 고객센터");
            System.out.println("5. 관리자");
            System.out.println("6. 종료");

            System.out.print("번호 : ");
            int num = sc.nextInt();
            if (num == 1) {
                customerService.customerView();
            } else if (num == 2) {
                accountService.accountView();
            } else if (num == 3) {
                loginMain.loginView();

            } else if (num == 4) {
//                new Server(7979);
                new ServerChat("localhost");

            } else if (num == 5) {
                AdminLogin.loginAdmin();
            } else if (num == 6) {
                sc.close();
                System.exit(0);
            } else {
                System.out.println("오타");
            }
        }
    }

    public void reMain() {
        CustomerService customerService = new CustomerService();
        AccountService accountService = new AccountService();
        Scanner sc = new Scanner(System.in);
        LoginMain loginMain = new LoginMain();

        System.out.println("안녕하세요 DK 은행 입니다.");
        System.out.println("번호는 입력해 원하는 서비스를 선택해주세요.");
        System.out.println("\n1. 고객관리");
        System.out.println("2. 통장개설");
        System.out.println("3. 계좌이체");
        System.out.println("4. 고객센터");
        System.out.println("5. 관리자");
        System.out.println("6. 종료");
        System.out.print("번호 : ");
        int num = sc.nextInt();
        if (num == 1) {
            customerService.customerView();
        } else if (num == 2) {
            accountService.accountView();
        } else if (num == 3) {
            loginMain.loginView();
        } else if (num == 4) {
            new ServerChat("localhost");
        } else if (num == 5) {
            AdminLogin.loginAdmin();
        } else if (num == 6) {
            sc.close();
            System.exit(0);
        } else {
            System.out.println("오타");
        }
    }

}