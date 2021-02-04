package com.serviceonepavilion.controllers;


import com.serviceonepavilion.dto.CartDto;
import com.serviceonepavilion.entities.ShoppingCart;
import com.serviceonepavilion.service.ShoppingCartService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@AllArgsConstructor
public class CartController {

    private ShoppingCartService shoppingCartService;

    @PostMapping
    public ResponseEntity<String> addToShoppingCart(@RequestBody CartDto cartDto) {
        shoppingCartService.addToShoppingCart(cartDto);
        return new ResponseEntity<>("Item Added to cart", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ShoppingCart> getShoppingCart(@RequestBody Integer customerId) {
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCart(customerId);
        return new ResponseEntity<>(shoppingCart, HttpStatus.OK);
    }

}
