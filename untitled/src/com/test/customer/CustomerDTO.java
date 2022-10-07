package com.test.customer;

public class CustomerDTO {
    private String customerName;
    private int customerPhone;
    private String customerAddress;
    private String customerRRN;
    private String customerGender;
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public int getCustomerPhone() {
        return customerPhone;
    }
    public void setCustomerPhone(int customerPhone) {
        this.customerPhone = customerPhone;
    }
    public String getCustomerAddress() {
        return customerAddress;
    }
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }
    public String getCustomerRRN() {
        return customerRRN;
    }
    public void setCustomerRRN(String customerRRN) {
        this.customerRRN = customerRRN;
    }
    public String getCustomerGender() {
        return customerGender;
    }
    public void setCustomerGender(String customerGender) {
        this.customerGender = customerGender;
    }

    @Override
    public String toString() {
        return "고객님의 정보는" + "\n" +
                " 이름 : " + customerName + "\n" +
                " 계좌번호 : " + customerPhone + "\n" +
                " 주소지 : " + customerAddress + "\n" +
                " 생일 : " + customerRRN + "\n" +
                " 성별 : " + customerGender + "\n";
    }
}