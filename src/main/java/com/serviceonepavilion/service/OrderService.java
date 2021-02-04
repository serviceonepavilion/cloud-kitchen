package com.serviceonepavilion.service;

import java.util.List;

import com.serviceonepavilion.entities.Order;

public interface OrderService {

	Order saveorUpdateOrder(Order order);

	int removeOrder(int id);

	Order getOrderById(int id);

	List<Order> findAllOrder();

}