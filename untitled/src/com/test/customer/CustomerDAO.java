package com.test.customer;

import com.test.dBUtil.DBUtil;
import com.test.main.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    Main main = new Main();
    // 1. 고객 생성
    public void insert(CustomerDTO dto) { // [3] 입력된 클래스의 정보를
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getInstance().getConnection();
            String sql = "insert into customer values (?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dto.getCustomerName());
            pstmt.setLong(2, dto.getCustomerPhone());
            pstmt.setString(3, dto.getCustomerAddress());
            pstmt.setString(4, dto.getCustomerRRN());
            pstmt.setString(5, dto.getCustomerGender());
            int result = pstmt.executeUpdate();
            String msg = result > -1 ? "성공" : "실패";
            System.out.println();
            System.out.println(msg);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (pstmt != null)
                    pstmt.close();
//                if (conn != null)
//                    conn.close();
                main.reMain(); //다시 돌아오게하는 메소드
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    // 2. 고객 전체 보기
    public List<CustomerDTO> getCustomerList() {

        List<CustomerDTO> customerList = new ArrayList<CustomerDTO>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getInstance().getConnection();

            String sql = "select customerName,customerPhone,customerAddress,customerRRN,customerGender from customer"; // *로 하기보다는 그냥 나열
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            // 결과값 얻어오기 없는데이터를 검색했을때 에러나니까 조건문 사용
            while (rs.next()) {
                CustomerDTO customerDTO = new CustomerDTO(); // 한 행씩 정보를 저장해서 리스트에 객체를 넣어야 하니까 매 반복문 마다 DeptDTO객체를 만들어야함
                customerDTO.setCustomerName(rs.getString(1));
                customerDTO.setCustomerPhone(rs.getInt(2));
                customerDTO.setCustomerAddress(rs.getString(3));
                customerDTO.setCustomerRRN(rs.getString(4));
                customerDTO.setCustomerGender(rs.getString(5));

                customerList.add(customerDTO);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {

        }
        return customerList;
    }

    // 3. 고객 검색 하기
    public CustomerDTO getcustomer(int customerNumber) {
        CustomerDTO customerDTO = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getInstance().getConnection();
            String sql = "select * from customer where customerPhone = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, customerNumber);
            rs = pstmt.executeQuery();
            //결과값 얻어오기 없는데이터를 검색했을때 에러나니까 조건문 사용
            if (rs.next()) {
                customerDTO = new CustomerDTO();
                customerDTO.setCustomerName(rs.getString(1));
                customerDTO.setCustomerPhone(rs.getInt(2));
                customerDTO.setCustomerAddress(rs.getString(3));
                customerDTO.setCustomerRRN(rs.getString(4));
                customerDTO.setCustomerGender(rs.getString(5));
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (pstmt != null)
                    pstmt.close();
//                if (conn != null)
//                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return customerDTO;
    }

    // 4. 고객 삭제
    public void deleteCustomer(CustomerDTO dto) {

        String sql = "delete from customer where customerPhone = ?";
        try (Connection conn = DBUtil.getInstance().getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setLong(1, dto.getCustomerPhone());
            int count = ps.executeUpdate();
            if (count > 0) {
                System.out.println(count + "입력됨");
            } else {
                System.out.println("입력 실패");
            }
        } catch (SQLException e) {
            System.out.println("관리자에게 계좌 정보 삭제를 먼저 요청하세요.");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public void deleteByAdmin(CustomerDTO dto) {

        String sql = "delete from customer where customerName = ?";
        try (Connection conn = DBUtil.getInstance().getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setString(1, dto.getCustomerName());
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
}