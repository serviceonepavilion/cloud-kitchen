package com.serviceonepavilion.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "RESTURANT")
public class Resturant {
	@Id
	@Column(name = "RESTURANT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer resturantId;
	@Column(name = "RESTURANT_NAME")
	private String resturantName;
	@Column(name = "RESTURANT_CONTACT")
	private String resturantContact;
	@Column(name = "RESTURANT_ADDRESS")
	private String resturantAddress;
	@Column(name = "OPENING_TIME")
	private Time openingTime;
	@Column(name = "CLOSING_TIME")
	private Time closingTime;

	@OneToOne( mappedBy = "resturant",  cascade = CascadeType.ALL, targetEntity = Order.class, fetch = FetchType.LAZY)
	private Order order;

	@OneToOne( mappedBy = "resturant",  cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Menu.class)
	private Menu menu;
}
