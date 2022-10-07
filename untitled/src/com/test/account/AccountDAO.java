package com.test.account;

import com.test.customer.CustomerDTO;
import com.test.dBUtil.DBUtil;
import com.test.main.Main;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AccountDAO {
    Main main = new Main();
    Connection conn = null;
    PreparedStatement pstmt = null;
    Statement stmt = null;
    ResultSet rs = null;

    // 1. 입출금 통장 개설
    public void insert(AccountDTO dto) { // [3] 입력된 클래스의 정보를
        // sql = value(accountNumber,customerNumber,accountPW,balance)
        String sql = "insert into account values (?,?,?,?)";
        try (Connection conn = DBUtil.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            // [4] 사용해서 쿼리문을 dto클래스 내부의 get메서드를 사용해서 private 변수들의 값들을 가져와서
            pstmt.setString(1, dto.getAccountNumber());
            pstmt.setInt(2, dto.getCustomerPhone());
            pstmt.setInt(3, dto.getAccountPW());
            pstmt.setLong(4, dto.getBalance());

            int count = pstmt.executeUpdate(); // [5] PreparedStatement클래스의 executeUpdate메서드를 통해 업데이트를 하게된다. * 참고로 ps는
            // conn으로 sql이 연동되어있음을 확인하자.
            if (count > 0) {
                System.out.println(count + "입력됨");
            } else {
                System.out.println("입력 실패");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
//              if (conn != null)
//                    conn.close();
                main.reMain(); //다시 돌아오게하는 메소드
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 2. 통장 정보 보기
    public AccountDTO getAccount(String accountNumber) {
        AccountDTO accountDTO = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<AccountDTO> accountList = new ArrayList<>();
        try {
            conn = DBUtil.getInstance().getConnection();
            String sql = "select * from account where accountNumber = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, accountNumber);
            rs = pstmt.executeQuery();
            //결과값 얻어오기 없는데이터를 검색했을때 에러나니까 조건문 사용
            if (rs.next()) {
                accountDTO = new AccountDTO();
                accountDTO.setAccountNumber(rs.getString(1)); // 뒤에 숫자는 컬럼순 (컬럼명 직접 지정해줘도 됨 "")
                accountDTO.setCustomerPhone(rs.getInt(2));
                accountDTO.setAccountPW(rs.getInt(3));
                accountDTO.setBalance(rs.getLong(4));
                accountList.add(accountDTO);
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
//              if (conn != null)
//                    conn.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return accountDTO;
    }

    public int selectAllId(String accountNumber){
        PreparedStatement pstmt = null;
        Scanner sc = new Scanner(System.in);
        int result = -1;
        try{
            conn = DBUtil.getInstance().getConnection();
            String sql = "Select accountnumber from account where accountnumber = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, accountNumber);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                result = 1;
//                System.out.println("result 값 : " + result);
                System.out.println("Id가 중복됩니다.");
                sc.close();
                System.exit(0);
            }else {
                result = 0;
                System.out.println("result 값: " + result);
                System.out.println("Id가 중복되지 않습니다.");
            }
            rs.close();
            pstmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    public void deleteAccount(String accountId) {

        String sql = "delete from account where accountNumber = ?";
        try (Connection conn = DBUtil.getInstance().getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setString(1, accountId);
            int count = ps.executeUpdate();
            if (count > 0) {
                System.out.println(count + "입력됨");
            } else {
                System.out.println("입력 실패");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public List<AccountDTO> getAccountList() {

        List<AccountDTO> accountList = new ArrayList<AccountDTO>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getInstance().getConnection();

            String sql = "select * from account"; // *로 하기보다는 그냥 나열
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            // 결과값 얻어오기 없는데이터를 검색했을때 에러나니까 조건문 사용
            while (rs.next()) {
                AccountDTO accountDTO = new AccountDTO(); // 한 행씩 정보를 저장해서 리스트에 객체를 넣어야 하니까 매 반복문 마다 DeptDTO객체를 만들어야함
                accountDTO.setAccountNumber(rs.getString(1));
                accountDTO.setCustomerPhone(rs.getInt(2));
                accountDTO.setAccountPW(rs.getInt(3));
                accountDTO.setBalance(rs.getLong(4));

                accountList.add(accountDTO);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {

        }
        return accountList;
    }
}