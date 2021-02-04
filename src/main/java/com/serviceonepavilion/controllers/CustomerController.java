package com.serviceonepavilion.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.serviceonepavilion.entities.Customer;
import com.serviceonepavilion.service.CustomerService;


@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping("/getCustomer/{customerId}")
	public Customer getCustomer(@PathVariable int customerId) {
		Customer customer = customerService.getCustomerById(customerId);
		return customer;
	}
	
	@GetMapping("/getAllCustomer")
	public List<Customer> getAllCustomer( ) {
		List<Customer> list = customerService.findAllCustomer();
		return list;
	}
	
	@PostMapping("/postCustomer")
	public Customer saveCustomer(@RequestBody Customer customer) {
		return customerService.saveorUpdateCustomer(customer);
	}
	
	@DeleteMapping("/removeCustomer/{customerId}")
	public int removeCustomer(@PathVariable int customerId) {
		return customerService.removeCustomer(customerId);
	}

	@PostMapping("/address")
	public void saveAddress(@RequestBody String address) {
		return ;
	}
}
