package com.serviceonepavilion.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "VERIFICATION_TOKEN")
public class VerificationToken {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "TOKEN_ID")
        private Integer tokenId;

        @Column(name = "TOKEN")
        private String token;

        @OneToOne(fetch = FetchType.LAZY)
        @PrimaryKeyJoinColumn
        private Customer customer;

        private Instant expiryDate;
}
