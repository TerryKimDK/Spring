package com.test.admin;

import com.test.account.AccountDAO;
import com.test.account.AccountDTO;
import com.test.customer.CustomerDAO;
import com.test.customer.CustomerDTO;

import java.io.FileReader;
import java.net.URLDecoder;
import java.util.*;

// 로그인 기능
public class AdminLogin {
    public static void loginAdmin() {
        Map<String, String> map = new Hashtable<>();
        Properties properties = getAdminInfo();
        getAdminInfo();
        String adminId = properties.getProperty("username");
        String adminPw = properties.getProperty("password");
        map.put(adminId, adminPw);
        CustomerDAO dao = new CustomerDAO();
        CustomerDTO dto = new CustomerDTO();
        AccountDAO acdao = new AccountDAO();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("아이디와 비밀번호를 입력해주세요");
            System.out.print("ID : ");
            String id = sc.next();
            System.out.print("PW : ");
            String pw = sc.next();

            if (map.containsKey(id)) {
                if (map.get(id).equals(pw)) {
                    System.out.println("로그인 되었습니다.");
                    System.out.println("안녕하세요 관리자님");
                    System.out.println("번호는 입력해 원하는 서비스를 선택해주세요.");
                    System.out.println("\n1. 고객 계좌보기");
                    System.out.println("2. 고객 계좌 삭제");
                    System.out.println("3. 회원 정보 보기");
                    System.out.println("4. 회원 정보 삭제");
                    int a = sc.nextInt();
                    if (a == 1) {
                        System.out.println("고객 계좌명단 출력");
                        List<AccountDTO> dtoList = acdao.getAccountList();
                        for (AccountDTO i : dtoList) {
                            System.out.println(i);
                        }
                    } else if (a == 2) {

                        System.out.println("삭제할 Id를 입력하시오");
                        String accountId = sc.next();
                        acdao.deleteAccount(accountId);


                    } else if (a == 3) {
                        List<CustomerDTO> dtoList = dao.getCustomerList();
                        for (CustomerDTO i : dtoList) {
                            System.out.println(i);
                        }
                    } else if (a == 4) {
                        System.out.print("삭제할 고객 이름 ");
                        String customerName = sc.next();
                        dto.setCustomerName(customerName);
                        dao.deleteByAdmin(dto);
                    }


                } else {

                    System.out.println("비밀번호를 다시 입력해주세요.");
                }
            } else
                System.out.println("존재하지 않는 아이디입니다.");
            break;
        }

    }

    public static Properties getAdminInfo() {
        Properties properties = null;
        try {
            properties = new Properties();
            String path = AdminLogin.class.getResource("database.properties").getPath();
            path = URLDecoder.decode(path, "UTF-8");
            properties.load(new FileReader(path));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties;
    }
}