package com.serviceonepavilion.controllers;


import com.serviceonepavilion.dto.AutheticationResponse;
import com.serviceonepavilion.dto.LoginRequest;
import com.serviceonepavilion.dto.RefreshTokenRequest;
import com.serviceonepavilion.dto.RegisterRequest;
import com.serviceonepavilion.entities.Customer;
import com.serviceonepavilion.service.Impl.AuthService;
import com.serviceonepavilion.service.Impl.RefreshTokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    private final RefreshTokenService refreshTokenService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest){
        authService.signup(registerRequest);
        return new ResponseEntity<>("Registration Successfull", HttpStatus.OK);
    }

    @PostMapping("/resendToken")
    public ResponseEntity<String> resendToken(@RequestBody RegisterRequest registerRequest){
        authService.signup(registerRequest);
        return new ResponseEntity<>("Verification Token sent", HttpStatus.OK);
    }

    @GetMapping("/accountVerification/{otp}/{userName}")
    public ResponseEntity<String> verifyAccount(@PathVariable("otp") Integer otp, @PathVariable("userName") Integer userName){
       // authService.verifyAccount(token);
        authService.verifyAccount(otp,userName);
        return new ResponseEntity<String>("Account activated successfully",HttpStatus.OK);
    }

    @PostMapping("/login")
    public AutheticationResponse login(@RequestBody LoginRequest loginRequest){
        return authService.login(loginRequest);
    }

    // Code for refresh token
    @PostMapping("/refresh/token")
    public AutheticationResponse refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest){
        return authService.refreshToken(refreshTokenRequest);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest){
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
        return  ResponseEntity.status(HttpStatus.OK).body("Refresh Token Deleted successfully");
    }
}
