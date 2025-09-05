package com.banking.app.controller;

import com.banking.app.dto.FundsTransfer;
import com.banking.app.entity.Transaction;
import com.banking.app.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping("/balance")
    public ResponseEntity<String> getAccountBalance(Principal principal) {
        return service.getAccountBalance(principal.getName());
    }

    @PostMapping("/transferFunds")
    public ResponseEntity<?> transferFunds(Principal principal, @RequestBody FundsTransfer fundsTransfer) {
        return service.transferFunds(principal.getName(), fundsTransfer);
    }

    @GetMapping("/transactionHistory")
    public ResponseEntity<List<Transaction>> getTransactionHistory(Principal principal) {
        System.out.println(principal.getName());
        return service.getTransactionHistory(principal.getName());
    }

    @PostMapping("/chequebook")
    public ResponseEntity<String> raiseChequeBookRequest(Principal principal) {
        return service.raiseChequeBookRequest(principal.getName());
    }

}
