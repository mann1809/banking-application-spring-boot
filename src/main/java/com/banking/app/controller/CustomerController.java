package com.banking.app.controller;

import com.banking.app.repository.AppUserRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final AppUserRepository repo;

    public CustomerController(AppUserRepository repo) {
        this.repo = repo;
    }

}
