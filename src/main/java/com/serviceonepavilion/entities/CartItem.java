package com.serviceonepavilion.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "CART")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartId;

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Item item;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SHOPPING_CART_ID", nullable = false)
    private ShoppingCart shoppingCart;

    @Column(name = "CREATED_TIME")
    private Instant createdTime;
}
