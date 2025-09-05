package com.banking.app.dto;

public class FundsTransfer {
    private String payee;
    private Double amount;

    public FundsTransfer(String payee, Double amount) {
        this.payee = payee;
        this.amount = amount;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
