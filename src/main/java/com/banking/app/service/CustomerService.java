package com.banking.app.service;

import com.banking.app.dto.FundsTransfer;
import com.banking.app.entity.AppUser;
import com.banking.app.entity.ChequeBookRequest;
import com.banking.app.entity.Transaction;
import com.banking.app.repository.AppUserRepository;
import com.banking.app.repository.ChequeBookRequestRepository;
import com.banking.app.repository.TransactionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    private final AppUserRepository appUserRep;
    private final TransactionRepository transactionRepo;
    private final ChequeBookRequestRepository chequeBookRequestRepo;

    public CustomerService(AppUserRepository appUserRep, TransactionRepository transactionRepo, ChequeBookRequestRepository chequeBookRequestRepo) {
        this.appUserRep = appUserRep;
        this.transactionRepo = transactionRepo;
        this.chequeBookRequestRepo = chequeBookRequestRepo;
    }

    public ResponseEntity<String> getAccountBalance(String username) {
        AppUser user = appUserRep.findByUsername(username);
        String result = "Account Balance:" + user.getAccountBalance();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    public ResponseEntity<?> transferFunds(String username, FundsTransfer fundsTransfer) {

        Double transferAmount = fundsTransfer.getAmount();
        LocalDateTime date = LocalDateTime.now();

        AppUser payee = appUserRep.findByUsername(fundsTransfer.getPayee());


        if (payee == null) {
            return new ResponseEntity<>("Payee doesn't exist", HttpStatus.NOT_FOUND);
        }
        AppUser payer = appUserRep.findByUsername(username);
        if (transferAmount <= 0) {
            return new ResponseEntity<>("Please enter valid amount....", HttpStatus.NOT_FOUND);
        }
        if (payer.getAccountBalance() <= transferAmount) {
            return new ResponseEntity<>("Insufficient Funds", HttpStatus.NOT_FOUND);
        }
        Double payerInitialBalance = payer.getAccountBalance();
        Double payeeInitialBalance = payee.getAccountBalance();
        Double payerBalance = payerInitialBalance - transferAmount;
        Double payeeBalance = payeeInitialBalance + transferAmount;
        payer.setAccountBalance(payerBalance);
        payee.setAccountBalance(payeeBalance);
        appUserRep.save(payer);
        appUserRep.save(payee);

        Transaction payerTransaction = new Transaction(date, transferAmount, "Debit", payee.getUsername(), payerInitialBalance, payerBalance, payer.getUsername());
        Transaction payeeTransaction = new Transaction(date, transferAmount, "Credit", payer.getUsername(), payeeInitialBalance, payeeBalance, payee.getUsername());
        transactionRepo.save(payerTransaction);
        transactionRepo.save(payeeTransaction);
        return new ResponseEntity<>("Funds Transferred Successfully", HttpStatus.OK);
    }

    public ResponseEntity<List<Transaction>> getTransactionHistory(String username) {
        List<Transaction> transactionHistory = new ArrayList<>();
        transactionHistory = transactionRepo.findByTransactionPerformedBy(username);
        return new ResponseEntity<>(transactionHistory, HttpStatus.OK);
    }

    public ResponseEntity<String> raiseChequeBookRequest(String username) {
        ChequeBookRequest chequeBookRequest = chequeBookRequestRepo.findByUsername(username);
        if (chequeBookRequest != null && chequeBookRequest.getStatus().equals("Pending")) {
            return new ResponseEntity<>("Request Already Raised and Pending for Approval", HttpStatus.OK);
        } else if (chequeBookRequest != null && chequeBookRequest.getStatus().equals("Approved")) {
            return new ResponseEntity<>("Request is Approved", HttpStatus.OK);
        }
        ChequeBookRequest request = new ChequeBookRequest(username, "Pending");
        chequeBookRequestRepo.save(request);
        return new ResponseEntity<>("Request Raised Successfully", HttpStatus.OK);
    }
}
