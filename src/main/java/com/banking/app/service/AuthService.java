package com.banking.app.service;

import com.banking.app.dto.AuthRequest;
import com.banking.app.dto.AuthResponse;
import com.banking.app.dto.SignupRequest;
import com.banking.app.entity.AppUser;
import com.banking.app.repository.AppUserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AppUserRepository repo;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;

    public AuthService(AppUserRepository repo, PasswordEncoder encoder, AuthenticationManager authManager, JwtUtil jwtUtil, UserDetailsServiceImpl userDetailsService) {
        this.repo = repo;
        this.encoder = encoder;
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    public ResponseEntity<?> signup(SignupRequest req) {
        AppUser user = new AppUser();
        user.setUsername(req.getUsername());
        user.setPassword(encoder.encode(req.getPassword()));
        user.setContactNumber(req.getContactNumber());
        user.setRole("user");
        user.setAccountBalance(500.00);
        repo.save(user);
        return ResponseEntity.ok("User registered Successfully!!");
    }

    public ResponseEntity<?> login(AuthRequest req) {
        Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
//        UserDetails ud = userDetailsService.loadUserByUsername(req.getUsername());
        UserDetails ud = (UserDetails) auth.getPrincipal();
        String token = jwtUtil.generateToken(ud);
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
