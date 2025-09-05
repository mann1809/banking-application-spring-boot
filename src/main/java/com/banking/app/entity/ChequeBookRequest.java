package com.banking.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ChequeBookRequest {
    @Id
    private String username;
    private String status;

    public ChequeBookRequest() {
    }

    public ChequeBookRequest(String username, String status) {
        this.username = username;
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
