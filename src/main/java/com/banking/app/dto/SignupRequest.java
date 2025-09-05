package com.banking.app.dto;

public class SignupRequest {
    private String username;
    private String password;
    private String contactNumber;
    private String role;

    public SignupRequest(String username, String password, String contactNumber, String role) {
        this.username = username;
        this.password = password;
        this.contactNumber = contactNumber;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
