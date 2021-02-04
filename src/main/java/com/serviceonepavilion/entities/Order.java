package com.serviceonepavilion.entities;


import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "CUSTOMER_ORDER")
public class Order {
	@Id
	@Column(name = "ORDER_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderId;

	@Column(name = "ORDER_STATUS")
	private OrderStatus orderStatus;

	@Column(name = "ORDER_DATE")
	@Temporal(value = TemporalType.TIMESTAMP)
	private java.util.Date orderDate;

	@Column(name = "ORDER_PRICE")
	private double orderPrice;
	@Column(name = "TRANSCATION_ID")
	private long transactionId;
	@Column(name = "TRANSACTION_STATUS")
	private TransactionStatus transactionStatus;
	@Column(name = "PAYMENT_TYPE")
	private PaymentType paymentType;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Resturant.class)
	@JoinColumn(name = "RESTURANT_ID", nullable = false)
	private Resturant resturant;

	/*
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "order_item",  joinColumns = {
			@JoinColumn(name = "ORDER_ID", nullable = false, updatable = false) },
			inverseJoinColumns = { @JoinColumn(name = "ITEM_ID",
					nullable = false, updatable = false) })
	Set<Item> itemSet;

	 */

	@ManyToOne(cascade = CascadeType.ALL, targetEntity = Customer.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER_ID",nullable = false)
	private Customer customer;


	
	
}
