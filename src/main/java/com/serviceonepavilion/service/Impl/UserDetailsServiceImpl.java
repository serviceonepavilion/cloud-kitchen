package com.serviceonepavilion.service.Impl;

import com.serviceonepavilion.entities.Customer;
import com.serviceonepavilion.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static java.util.Collections.singletonList;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private final CustomerRepository customerRepository;

    @Override
    @Transactional()
    public UserDetails loadUserByUsername(String username) {
        Optional<Customer> userOptional = customerRepository.findByUserName(Integer.valueOf(username));
        Customer customer = userOptional
                // Throw exception if user doen not exist. UsernameNotFoundException is provided by spring
                .orElseThrow(() -> new UsernameNotFoundException("No user " +
                        "Found with username : " + username));

        return new org.springframework.security
                .core.userdetails.User(Integer.toString(customer.getUserName()), customer.getPassword(),
                customer.isEnabled(), true, true,
                true, getAuthorities("USER"));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return singletonList(new SimpleGrantedAuthority(role));
    }
}
