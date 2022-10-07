package com.test.account;

public class AccountDTO {
    private String accountNumber;
    private int customerPhone;
    private int accountPW;
    private long balance;
    public String getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    public int getCustomerPhone() {
        return customerPhone;
    }
    public void setCustomerPhone(int customerNumber) {
        this.customerPhone = customerNumber;
    }
    public int getAccountPW() {
        return accountPW;
    }
    public void setAccountPW(int accountPW) {
        this.accountPW = accountPW;
    }
    public long getBalance() {
        return balance;
    }
    public void setBalance(long balance) {
        this.balance = balance;
    }
    @Override
    public String toString() {
        return "고객님의 아이디" + accountNumber + "\n" +
                "계좌번호=" + customerPhone + "\n" +
                "accountPW=" + accountPW + "\n" +
                "계좌 잔액 :" + balance + "\n";
    }

}