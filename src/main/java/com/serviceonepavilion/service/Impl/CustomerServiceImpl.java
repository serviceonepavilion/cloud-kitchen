package com.serviceonepavilion.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serviceonepavilion.entities.Customer;
import com.serviceonepavilion.entities.Item;
import com.serviceonepavilion.repository.CustomerRepository;
import com.serviceonepavilion.service.CustomerService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	public CustomerRepository customerRepository;

	@Override
	@Transactional
	public Customer saveorUpdateCustomer(Customer customer) {
		Customer cust = null;
		if (!customerRepository.existsById(customer.getCustomerId())) {
			cust = customerRepository.save(customer);
		} else {
			//cust = customerRepository.update();
			// TODO
			cust = customerRepository.save(customer);
		}
		return cust;
	}

	@Override
	@Transactional
	public int removeCustomer(int id) {
		customerRepository.deleteById(id);
		return id;
	}

	@Override
	@Transactional
	public Customer getCustomerById(int id) {
		return customerRepository.findById(id).get();
	}

	@Override
	@Transactional
	public List<Customer> findAllCustomer() {
		//List<Customer> listCustomers = customerRepository.findAll();
		List<Customer> listCustomers = new ArrayList<>();
		customerRepository.findAll().forEach(listCustomers::add);
		return listCustomers;
	}
}