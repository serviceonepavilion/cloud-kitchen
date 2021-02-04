package com.serviceonepavilion.service.Impl;


import com.serviceonepavilion.dto.AutheticationResponse;
import com.serviceonepavilion.dto.LoginRequest;
import com.serviceonepavilion.dto.RefreshTokenRequest;
import com.serviceonepavilion.dto.RegisterRequest;
import com.serviceonepavilion.entities.*;
import com.serviceonepavilion.repository.CustomerRepository;
import com.serviceonepavilion.repository.VerificationTokenRepository;
import com.serviceonepavilion.security.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final CustomerRepository customerRepository;
    @Autowired
    private final VerificationTokenRepository verificationTokenRepository;
    @Autowired
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final JwtProvider jwtProvider;
  //  private final MailService mailService;
  @Autowired
    private final RefreshTokenService refreshTokenService;

  private final Integer OTP = 1234;


    @Transactional
    public void signup(RegisterRequest registerRequest) {
        Customer customer = new Customer();
        customer.setUserName(registerRequest.getUserName());
        customer.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        customer.setCreatedTime(Instant.now());
        customer.setEnabled(false);
        customerRepository.save(customer);
        String token = generateVerificationToken(customer);
       /* mailService.sendMail(new NotificationEmail("Please Activate your Account",
                user.getEmail(), "Thank you for signing up to Kitchen, " +
                "please click on the below url to activate your account : " +
                "http://localhost:8080/api/auth/accountVerification/" + token));*/
    }

    @Transactional
    public void resendToken(Integer userName) {
        Optional<Customer> customerOptional = customerRepository.findByUserName(userName);
        customerOptional.orElseThrow(()->new RuntimeException("Invalid User Name"));
        Customer customer = customerOptional.get();

        String token = generateVerificationToken(customer);
       /* mailService.sendMail(new NotificationEmail("Please Activate your Account",
                user.getEmail(), "Thank you for signing up to Spring Reddit, " +
                "please click on the below url to activate your account : " +
                "http://localhost:8080/api/auth/accountVerification/" + token));*/
    }

    @Transactional
    public void verifyAccount(String token) {
        Optional<VerificationToken> verificationTokenOptional = verificationTokenRepository.findByToken(token);
        verificationTokenOptional.orElseThrow(() -> new RuntimeException("Invalid Token"));
        VerificationToken verificationToken = verificationTokenOptional.get();
        Instant expiryDate = verificationToken.getExpiryDate();
        Instant currentTime=Instant.now();
        long duration = ChronoUnit.HOURS.between(expiryDate,currentTime);
        if(duration>10){
            throw new RuntimeException("Token Link Expired.");
        }
        fetchCustomerAndEnable(verificationToken);
    }

    @Transactional
    public void verifyAccount(Integer otp, Integer userName) {
        Customer customer = customerRepository.findByUserNameForAccountVerification(userName).orElseThrow(() -> new RuntimeException("User not found with mobile: " + userName));
        if(customer.getOtp().equals(OTP)){
            customer.setEnabled(true);
            customerRepository.enableAccount(userName);
        }else{
            throw new RuntimeException("Invalid OTP");
        }
    }


    public AutheticationResponse login(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtProvider.generateToken(authenticate);
        return AutheticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenService.generateRefreshToken().getToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .username(loginRequest.getUserName())
                .build();
    }

    @Transactional(readOnly = true)
    public Customer getCurrentUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        return customerRepository.findByUserName(Integer.valueOf(principal.getUsername()))
                .orElseThrow(() -> new UsernameNotFoundException("User name not found - " + principal.getUsername()));
    }


    private String generateVerificationToken(Customer customer) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setCustomer(customer);
        verificationToken.setExpiryDate(Instant.now().plus(10, ChronoUnit.HOURS));

        verificationTokenRepository.save(verificationToken);
        return token;
    }


    @Transactional
    public AutheticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
        String token=jwtProvider.generateTokenWithUserName(refreshTokenRequest.getUserName());
        return AutheticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenRequest.getRefreshToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .username(refreshTokenRequest.getUserName())
                .build();
    }

    @Transactional
    public boolean isLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
    }

    private void fetchCustomerAndEnable(VerificationToken verificationToken) {
        Integer userName = verificationToken.getCustomer().getUserName();
        Customer customer = customerRepository.findByUserName(userName).orElseThrow(() -> new RuntimeException("User not found with mobile: " + userName));
        customer.setEnabled(true);
        customerRepository.save(customer);
    }
}
