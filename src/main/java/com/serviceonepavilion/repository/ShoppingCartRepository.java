package com.serviceonepavilion.repository;

import com.serviceonepavilion.entities.Customer;
import com.serviceonepavilion.entities.ShoppingCart;
import jdk.management.resource.internal.inst.SimpleAsynchronousFileChannelImplRMHooks;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository  extends CrudRepository<ShoppingCart, Integer> {
    Optional<ShoppingCart> findByCustomer(Integer customerId);
}
