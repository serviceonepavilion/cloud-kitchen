package com.serviceonepavilion.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ADDRESS")
public class Address {
	@Id
	@Column(name = "ADDRESS_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int addressId;
	@Column(name = "HOUSE_NO")
	private String houseNo;
	@Column(name = "STREET")
	private String street;
	@Column(name = "CITY_NAME")
	private String cityName;
	@Column(name = "STATE")
	private String state;
	@Column(name = "PINCODE")
	private Integer pincode;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Customer.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "CUSTOMER_ID", nullable = false)
	private Customer customer;

}
