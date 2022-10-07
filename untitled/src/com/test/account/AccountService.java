package com.test.account;

import com.test.dBUtil.DBUtil;
import com.test.main.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class AccountService {
    AccountDAO dao = new AccountDAO();
    AccountDTO dto = new AccountDTO();

    Scanner sc = new Scanner(System.in);
    Main main = new Main();

    public void accountView() {
        while (true) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("안녕하세요 DK 은행 입니다.");
            System.out.println("------------------------------");
            System.out.println("저희 은행 회원이십니까?\n비회원이실 경우 이용이 제한됩니다.");
            System.out.println("번호는 입력해 원하는 서비스를 선택해주세요.");
            System.out.println("\n1. 입출금 통장 생성");
            System.out.println("2. 통장 정보 보기");
            System.out.println("3. 뒤로가기");
            System.out.println("");
            System.out.print("번호 : ");
            int num = sc.nextInt();
            ResultSet rs = null;
            if (num == 1) {
                System.out.println("추가");
                System.out.print("accountId : ");
                String accountNumber = sc.next();
                System.out.println("Id 중복검사 실행");
                System.out.println();
                dao.selectAllId(accountNumber);

                System.out.print("핸드폰 번호를 입력해주세요(계좌번호) : ");
                int customerNumber = sc.nextInt();

                System.out.print("accountPW 숫자로 입력해주세요: ");
                int accountPW = sc.nextInt();

                System.out.println("얼마를 넣으시겠습니까? : ");
                long balance = sc.nextLong();

                dto.setAccountNumber(accountNumber); // [1] 위에서 스캐너로 입력받은 값을 차례대로 dto클래스의 set메서드를 사용해 private한 변수에 넣고
                dto.setCustomerPhone(customerNumber);
                dto.setAccountPW(accountPW);
                dto.setBalance(balance);

                dao.insert(dto); // [2] set된 private 변수들을 dao의 insert메서드의 파라메터에 넣어준다.
            } else if (num == 2) {
                // 계좌검색
                System.out.print("회원 Id를 입력하시오 : ");
                String accountNumber = sc.next();
                // 로그인 시스템
                System.out.print("비밀번호를 입력해주세요: ");
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
                        }else {
                            System.out.println("비밀번호가 틀렸습니다.");
                            main.reMain();
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                dto = dao.getAccount(accountNumber);
                System.out.println(dto);
                System.out.println("초기화면으로 가시겠습니까?");
                System.out.println("1. 예  2. 아니오(프로그램 종료)");
                int i = sc.nextInt();
                if(i == 1){
                    main.reMain();
                }else {
                    sc.close();
                    System.exit(0);
                }
            } else if (num == 3) {
                break;
            } else {
                System.out.println("오타");
            }

        }
    }

}