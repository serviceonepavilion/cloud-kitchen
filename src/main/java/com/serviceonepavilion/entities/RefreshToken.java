package com.serviceonepavilion.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="REFRESH_TOKEN")
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="REFRESH_TOKEN_ID")
    private Integer refreshTokenId;

    @Column(name ="TOKEN")
    private String token;

    private Instant createdDate;
}
