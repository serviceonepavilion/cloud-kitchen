package com.serviceonepavilion.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serviceonepavilion.entities.Item;
import com.serviceonepavilion.entities.Order;
import com.serviceonepavilion.repository.ItemRepository;
import com.serviceonepavilion.repository.OrderRepository;
import com.serviceonepavilion.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	OrderRepository orderRepository;

	public Order saveorUpdateOrder(Order order) {
		Order customerItem = null;
		if (!orderRepository.existsById(order.getOrderId())) {
			customerItem = orderRepository.save(order);
		} else {
			//customerItem = orderRepository.update();
			// TODO
			customerItem = orderRepository.save(order);
		}
		return customerItem;
	}

	public int removeOrder(int id) {
		orderRepository.deleteById(id);
		return id;
	}

	public Order getOrderById(int id) {
		return orderRepository.findById(id).get();
	}

	public List<Order> findAllOrder() {
		//List<Order> listItem = orderRepository.findAll();
		List<Order> listItem = new ArrayList<>();
		orderRepository.findAll().forEach(listItem::add);
		return listItem;
	}
	
}
