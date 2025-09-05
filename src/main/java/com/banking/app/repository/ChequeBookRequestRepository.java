package com.banking.app.repository;

import com.banking.app.entity.ChequeBookRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChequeBookRequestRepository extends JpaRepository<ChequeBookRequest, String> {
    ChequeBookRequest findByUsername(String username);
}
