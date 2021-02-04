package com.serviceonepavilion.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.serviceonepavilion.entities.Order;
import com.serviceonepavilion.service.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController {

	@Autowired
	OrderService orderService;
	
	@GetMapping("/order/{orderId}")
	public Order getOrder(@PathVariable int orderId) {
		Order order = orderService.getOrderById(orderId);
		return order;
	}
	
	@GetMapping("/orders")
	public List<Order> getAllOrder() {
		List<Order> list = orderService.findAllOrder();
		return list;
	}
	
	@PostMapping("/order")
	public Order placeOrder(@RequestBody Order order) {
		return orderService.saveorUpdateOrder(order);
	}
	
	@PutMapping("/order")
	public Order updateOrder(@RequestBody Order order) {
		return orderService.saveorUpdateOrder(order);
	}
	
	@DeleteMapping("/order/{orderId}")
	public int deleteOrder(@PathVariable int orderId) {
		return orderService.removeOrder(orderId);
	}
	
	
}
