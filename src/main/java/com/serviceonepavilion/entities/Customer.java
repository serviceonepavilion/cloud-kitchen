package com.serviceonepavilion.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

import javax.persistence.*;

@Data
@Entity
@Table(name = "CUSTOMER")
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
	@Id
	@Column(name = "CUSTOMER_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer customerId;

	@Column(name = "COUNTRY_CODE")
	private String countryCode;

	// Username is phone number
	@Column(name = "USER_NAME")
	private Integer userName;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "CUSTOMER_NAME")
	private String customerName;

	@Column(name = "CREATED_TIME")
	private Instant createdTime;

	//@OneToMany - non owning side here we use mappedBy
	@OneToMany( mappedBy = "customer",  targetEntity = Address.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Address> addressList;
	
	@OneToMany( mappedBy = "customer",  targetEntity = Order.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Order> orderList;

	@Column(name = "ENABLED")
	private Boolean enabled;

	@Column(name = "OTP")
	private Integer otp = 1234;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
	private ShoppingCart shoppingCart;

	//@OptionalInGson(exclude = )
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
	private VerificationToken verificationToken;

	public Customer(Integer customerId, Integer userName, Integer otp, Boolean enabled){
		this.userName=userName;
		this.customerId=customerId;
		this.otp=otp;
		this.enabled=enabled;
	}

	public Boolean isEnabled() {
		return enabled;
	}
}
