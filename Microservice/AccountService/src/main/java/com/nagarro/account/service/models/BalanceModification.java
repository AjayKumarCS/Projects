package com.nagarro.account.service.models;

public class BalanceModification {

    private String accountId;
    private int amount;
    private String CustomerName;

    public BalanceModification(String accountId, int amount, String customerName) {
        this.accountId = accountId;
        this.amount = amount;
        CustomerName = customerName;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }
}
