package com.banking.app.controller;

import com.banking.app.dto.AuthRequest;
import com.banking.app.dto.AuthResponse;
import com.banking.app.dto.SignupRequest;
import com.banking.app.entity.AppUser;
import com.banking.app.repository.AppUserRepository;
import com.banking.app.service.JwtUtil;
import com.banking.app.service.UserDetailsServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AppUserRepository repo;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;

    public AuthController(AppUserRepository repo, PasswordEncoder encoder, AuthenticationManager authManager, JwtUtil jwtUtil, UserDetailsServiceImpl userDetailsService) {
        this.repo = repo;
        this.encoder = encoder;
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest req) {
        AppUser user = new AppUser();
        user.setUsername(req.getUsername());
        user.setPassword(encoder.encode(req.getPassword()));
        user.setContactNumber(req.getContactNumber());
        repo.save(user);
        return ResponseEntity.ok("User registered Successfully!!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest req) {
        Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
//        UserDetails ud = userDetailsService.loadUserByUsername(req.getUsername());
        UserDetails ud = (UserDetails) auth.getPrincipal();
        String token = jwtUtil.generateToken(ud);
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
