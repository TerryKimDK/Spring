package com.test.transfer;

import com.test.account.AccountDTO;
import com.test.dBUtil.DBUtil;

import java.sql.*;
import java.util.Scanner;

public class Bank {

    public void deposit(){
        AccountDTO accountDTO = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Scanner sc = new Scanner(System.in);
        Statement stmt = null;

        try {
            conn = DBUtil.getInstance().getConnection();
            System.out.println("고객님의 휴대폰 번호를 입력해주세요");
            int customerphone = sc.nextInt();

            String sql = "select balance from account where customerphone=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, customerphone);
            rs = pstmt.executeQuery();

            while (rs.next()) {
               int balances = rs.getInt(1);
                System.out.println("계좌의 잔액은 : " + balances + " 원 입니다");
                System.out.println("입금 금액을 입력하세요 : ");
                int plusM = sc.nextInt();
                balances += plusM;
                System.out.println(balances);
                String Sqls = "UPDATE account SET balance='"+ balances +"' Where customerPhone ='" +customerphone+ "'";
                stmt = conn.createStatement();
                int result = stmt.executeUpdate(Sqls);
                String msg = result > -1 ? "입금에 성공하셨습니다." : "입금에 오류가 생겼습니다.";
                System.out.println(msg);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void withdraw(){
        AccountDTO accountDTO = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Scanner sc = new Scanner(System.in);
        Statement stmt = null;

        try {
            conn = DBUtil.getInstance().getConnection();
            System.out.println("고객님의 휴대폰 번호를 입력해주세요");
            int customerphone = sc.nextInt();

            String sql = "select balance from account where customerphone=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, customerphone);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int balances = rs.getInt(1);
                System.out.println("계좌의 잔액은 : " + balances + " 원 입니다");
                System.out.println("출금 금액을 입력하세요 : ");
                int minusM = sc.nextInt();
                balances -= minusM;
                String Sqls = "UPDATE account SET balance='"+ balances +"' Where customerPhone ='" +customerphone+ "'";
                stmt = conn.createStatement();
                int result = stmt.executeUpdate(Sqls);
                String msg = result > -1 ? "출금에 성공하셨습니다." : "출금에 오류가 생겼습니다.";
                System.out.println(msg);
                System.out.println("회원님의 잔액은" + balances);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void banktransfer(){
        AccountDTO accountDTO = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Scanner sc = new Scanner(System.in);
        Statement stmt = null;
        try {
            conn = DBUtil.getInstance().getConnection();
            System.out.print("고객님의 휴대폰 번호를 입력해주세요 :");
            int customerphone = sc.nextInt();

            String sql = "select balance from account where customerphone=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, customerphone);
            rs = pstmt.executeQuery();
            while (rs.next()){
                int balances = rs.getInt(1);
                if(balances > 0){
                    System.out.println("계좌의 잔액은 : " + balances + " 원 입니다");
                    System.out.print("이체하실 계좌번호를 입력해주세요 : ");
                    int transferto = sc.nextInt();
                    try{
                        String sql2 = "select balance from account where customerphone=?";
                        pstmt = conn.prepareStatement(sql2);
                        pstmt.setInt(1, transferto);
                        rs = pstmt.executeQuery();
                        while(rs.next()){
                            int acceptPersonMoney = rs.getInt(1);
                            System.out.print("이체하실 금액을 입력해주세요 :");
                            int transmoney = sc.nextInt();
                            String Sqls = "UPDATE account SET balance='"+ (acceptPersonMoney+transmoney) +"' Where customerPhone ='" +transferto+ "'";
                            stmt = conn.createStatement();
                            int result = stmt.executeUpdate(Sqls);
                            String msg = result > -1 ? "이체에 성공하셨습니다." : "이체에 오류가 생겼습니다.";
                            System.out.println(msg);
                            String Sqlme = "UPDATE account SET balance='"+ (balances-transmoney) +"' Where customerPhone ='" +customerphone+ "'";
                            int results = stmt.executeUpdate(Sqlme);
                            String msgs = results > -1 ? "금액이 차감." : "이체에 오류가 생겼습니다.";
                            System.out.println(msgs);
                            System.out.println("회원님의 잔액은 " + (balances-transmoney) + " 원 입니다.");
                        }

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                } else{
                    System.out.println("잔액이 부족합니다.");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
