package com.test.transfer;

import com.test.dBUtil.DBUtil;
import com.test.main.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class LoginMain {
    Scanner sc = new Scanner(System.in);
    Main main = new Main();
    Bank bank = new Bank();

    public void loginView() {
        ResultSet rs = null;
        System.out.println("안녕하세요 DK 은행 입니다.");
        System.out.println("로그인 중.....");
        System.out.println("Id를 입력해주세요");
        String accountNumber = sc.next();
        System.out.print("accountPW 숫자로 입력해주세요: ");
        int accountPW = sc.nextInt();
        String sql = "SELECT accountpw FROM account WHERE accountNumber = ?";
        try {
            Connection conn = DBUtil.getInstance().getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, accountNumber);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                if (rs.getInt(1) == (accountPW)) {

                    System.out.println("로그인에 성공하셨습니다.");
                    System.out.println("번호는 입력해 원하는 서비스를 선택해주세요.");
                    System.out.println("\n1.입금");
                    System.out.println("2.출금");
                    System.out.println("3.계좌이체");
                    System.out.print("번호 : ");
                    int num = sc.nextInt();
                    if (num == 1) {
                        bank.deposit();
                    } else if (num == 2) {
                        bank.withdraw();
                    } else if (num == 3) {
                        bank.banktransfer();
                    }
                } else {
                    System.out.println("비밀번호가 틀렸습니다.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}



