package com.serviceonepavilion.service.Impl;

import com.serviceonepavilion.dto.CartDto;
import com.serviceonepavilion.entities.CartItem;
import com.serviceonepavilion.entities.Item;
import com.serviceonepavilion.entities.ShoppingCart;
import com.serviceonepavilion.repository.CustomerRepository;
import com.serviceonepavilion.repository.ItemRepository;
import com.serviceonepavilion.repository.ShoppingCartRepository;
import com.serviceonepavilion.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    ShoppingCartRepository shoppingCartRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public ShoppingCart getShoppingCart(Integer customerId) {
        Optional<ShoppingCart> shoppingCartOptional = shoppingCartRepository.findByCustomer(customerId);
        return shoppingCartOptional.isPresent() ? shoppingCartOptional.get() : null;
    }

    @Override
    public void addToShoppingCart(CartDto cartDto) {

        Optional<Item> itemOptional = itemRepository.findById(cartDto.getItemId());
        itemOptional.orElseThrow(() -> new RuntimeException("Item does not exist"));
        Item item = itemOptional.get();
        // Add logic for quantity
        // Validation customer logic is not required as jwt will handle authentication and authorization
        CartItem cartItem = new CartItem();
        cartItem.setItem(item);
        cartItem.setQuantity(cartDto.getQuantity());
        Optional<ShoppingCart> shoppingCartOptional = shoppingCartRepository.findByCustomer(cartDto.getCustomerId());
        if (shoppingCartOptional.isPresent()) {
            ShoppingCart shoppingCart = shoppingCartOptional.get();
            shoppingCart.getCartItems().add(cartItem);
            cartItem.setShoppingCart(shoppingCart);
            shoppingCartRepository.save(shoppingCart);
        } else {
            ShoppingCart shoppingCart = new ShoppingCart();
            Set<CartItem> cartItemSet = new HashSet<>();
            cartItemSet.add(cartItem);
            cartItem.setShoppingCart(shoppingCart);
            shoppingCart.setCartItems(cartItemSet);
            shoppingCart.setCustomer(customerRepository.findById(cartDto.getCustomerId()).get());
            shoppingCartRepository.save(shoppingCart);
        }
    }
}
