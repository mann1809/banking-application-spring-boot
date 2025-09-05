package com.banking.app.service;

import com.banking.app.entity.AppUser;
import com.banking.app.repository.AppUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AppUserRepository repo;

    public UserDetailsServiceImpl(AppUserRepository repo) {
        this.repo = repo;
    }

    public UserDetails loadUserByUsername(String username) {
        AppUser user = repo.findByUsername(username);
        if (user.getRole() == "user") {
            return new User(user.getUsername(), user.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_USER")));
        } else {
            return new User(user.getUsername(), user.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_ADMIN")));
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
