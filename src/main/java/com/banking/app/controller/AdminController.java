package com.banking.app.controller;

import com.banking.app.entity.AppUser;
import com.banking.app.entity.ChequeBookRequest;
import com.banking.app.entity.Transaction;
import com.banking.app.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService service;

    public AdminController(AdminService service) {
        this.service = service;
    }

    @PostMapping("/addAccount")
    public ResponseEntity<?> createNewUserAccount(@RequestBody AppUser user) {
        return service.createNewUserAccount(user);
    }

    @GetMapping("/transactionHistory")
    public ResponseEntity<List<Transaction>> getTransactionHistory(@RequestParam String username) {
        return service.getTransactionHistory(username);
    }

    @GetMapping("/checkBalance")
    public ResponseEntity<String> getAccountBalance(@RequestParam String username) {
        return service.getAccountBalance(username);
    }

    @GetMapping("/pendingRequests")
    public ResponseEntity<List<ChequeBookRequest>> getChequeBookRequests() {
        return service.getChequeBookRequests();
    }

    @GetMapping("/approveRequests")
    public ResponseEntity<String> approveChequeBookRequests(@RequestParam String username) {
        return service.approveChequeBookRequests(username);
    }
}
