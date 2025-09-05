package com.banking.app.service;

import com.banking.app.entity.AppUser;
import com.banking.app.entity.ChequeBookRequest;
import com.banking.app.entity.Transaction;
import com.banking.app.repository.AppUserRepository;
import com.banking.app.repository.ChequeBookRequestRepository;
import com.banking.app.repository.TransactionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final AppUserRepository appUserRepo;
    private final TransactionRepository transactionRepo;
    private final ChequeBookRequestRepository chequeBookRequestRepo;

    public AdminService(AppUserRepository appUserRepo, TransactionRepository transactionRepo, ChequeBookRequestRepository chequeBookRequestRepo) {
        this.appUserRepo = appUserRepo;
        this.transactionRepo = transactionRepo;
        this.chequeBookRequestRepo = chequeBookRequestRepo;
    }

    public ResponseEntity<?> createNewUserAccount(AppUser user) {
        AppUser appUser = appUserRepo.findByUsername(user.getUsername());
        if (appUser != null) {
            return new ResponseEntity<>("Customer account already created....", HttpStatus.OK);
        }
        appUserRepo.save(user);
        return new ResponseEntity<>("Customer account is created successfully....", HttpStatus.OK);
    }

    public ResponseEntity<List<Transaction>> getTransactionHistory(String username) {
        List<Transaction> transactionHistory = transactionRepo.findByTransactionPerformedBy(username);
        return new ResponseEntity<>(transactionHistory, HttpStatus.OK);
    }

    public ResponseEntity<String> getAccountBalance(String username) {
        AppUser user = appUserRepo.findByUsername(username);
        if (user == null) {
            return new ResponseEntity<>("Please enter correct username....", HttpStatus.OK);
        }
        String result = "Account Balance:" + user.getAccountBalance();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    public ResponseEntity<List<ChequeBookRequest>> getChequeBookRequests() {
        List<ChequeBookRequest> requests = chequeBookRequestRepo.findAll();
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }

    public ResponseEntity<String> approveChequeBookRequests(String username) {
        ChequeBookRequest request = chequeBookRequestRepo.findByUsername(username);
        request.setStatus("Approved");
        chequeBookRequestRepo.save(request);
        return new ResponseEntity<>("Approved Successfully....", HttpStatus.OK);
    }
}
