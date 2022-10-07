package com.test.customer;

import com.test.main.Main;

import java.util.List;
import java.util.Scanner;

public class CustomerService {
    CustomerDAO dao = new CustomerDAO();
    CustomerDTO dto = new CustomerDTO();
    Scanner scan = new Scanner(System.in);
    Main main = new Main();

    public void customerView() {
        while (true) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("안녕하세요 DK은행 입니다.");
            System.out.println("번호는 입력해 원하는 서비스를 선택해주세요.");
            System.out.println("\n1. 고객 생성");
            System.out.println("2. 내 정보 검색");
            System.out.println("3. 회원 정보 삭제");
            System.out.println("4. 뒤로 가기");
            System.out.println("");
            System.out.print("번호 : ");
            int num = scan.nextInt();
            if (num == 1) {
                System.out.println("고객생성");

                System.out.print("customerName : ");
                String customerName = scan.next();

                System.out.print("customerPhone : ");
                int customerPhone = scan.nextInt();

                System.out.print("customerAddress : ");
                String customerAddress = scan.next();

                System.out.print("customerRRN (생일): ");
                String customerRRN = scan.next();

                System.out.print("customerGender : ");
                String customerGender = scan.next();

                dto.setCustomerName(customerName);
                dto.setCustomerPhone(customerPhone);
                dto.setCustomerAddress(customerAddress);
                dto.setCustomerRRN(customerRRN);
                dto.setCustomerGender(customerGender);
                System.out.print(dto);
                dao.insert(dto);

                //계좌번호 출력 프로그램 만들기

            } else if (num == 2) {
                System.out.print("고객 연락처 : ");
                int customerNumber = scan.nextInt();
                dto = dao.getcustomer(customerNumber);
                System.out.println(dto);
                System.out.println("초기화면으로 가시겠습니까?");
                System.out.println("1. 예  2. 아니오(프로그램 종료)");
                int i = scan.nextInt();
                if(i == 1){
                    main.reMain();
                }else {
                    scan.close();
                    System.exit(0);
                }
            } else if (num == 3) {
                System.out.print("고객 연락처(삭제): ");
                int customerPhone = scan.nextInt();
                dto.setCustomerPhone(customerPhone);
                dao.deleteCustomer(dto);
            } else if (num == 4) {
                break;
            } else {
                System.out.println("오타");
            }

        }
    }
}