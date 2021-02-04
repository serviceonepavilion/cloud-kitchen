package com.serviceonepavilion.security;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.cert.CertificateException;


import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.security.*;
import java.time.Instant;
import java.util.Date;


@Service
public class JwtProvider {

    private KeyStore keyStore;

    // Expiration time for jwt. Inject from properties file
    @Value("${jwt.expiration.time}")
    private Long jwtExpirationInMillis;

    public String generateToken(Authentication authentication){
        User principal = (User)authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(principal.getUsername())
                .setIssuedAt(Date.from(Instant.now()))
                .signWith(getPrivateKey())
                .setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationInMillis)))
                .compact();
    }

    private Key getPrivateKey() {
        try {
            // Read private key from key store, by passing alias and keystore password
            return (PrivateKey) keyStore.getKey("springblog", "secret".toCharArray());
        } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
            throw new RuntimeException("Exception occured while retrieving private key from keystore", e);
        }
    }

    @PostConstruct
    public void init() {
        try {
            keyStore = KeyStore.getInstance("JKS");
            InputStream resourceAsStream = getClass().getResourceAsStream("/springblog.jks");
            keyStore.load(resourceAsStream, "secret".toCharArray());
        } catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e) {
            throw new RuntimeException("Exception occurred while loading keystore", e);
        }
    }

    public boolean validateToken(String token){
        Jwts.parser().setSigningKey(getPublicKey());
        return true;
    }

    public String getUsernameFromJwt(String token){
        Claims claims = Jwts.parser().setSigningKey(getPublicKey()).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }


    public String generateTokenWithUserName(Integer  username){

        return Jwts.builder()
                .setSubject(Integer.toString(username))
                .setIssuedAt(Date.from(Instant.now()))
                .signWith(getPrivateKey())
                .setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationInMillis)))
                .compact();
    }

    public Long getJwtExpirationInMillis(){
        return jwtExpirationInMillis;
    }

    private PublicKey getPublicKey() {
        try {
            return (PublicKey) keyStore.getCertificate("springblog").getPublicKey();
        } catch (KeyStoreException e) {
            throw new RuntimeException("Exception occured while retrieving public key from keystore", e);
        }
    }
}
