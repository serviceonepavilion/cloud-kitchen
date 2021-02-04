package com.serviceonepavilion.service;

import java.util.List;

import com.serviceonepavilion.entities.Customer;

public interface CustomerService {

	Customer saveorUpdateCustomer(Customer customer);

	int removeCustomer(int id);

	Customer getCustomerById(int id);

	List<Customer> findAllCustomer();

}