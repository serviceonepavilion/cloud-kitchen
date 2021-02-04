package com.serviceonepavilion.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;

@Data
@Entity
@Table(name = "ITEM")
public class Item {
	@Id
	@Column(name = "ITEM_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer itemId;
	@Column(name = "ITEM_NAME")
	private String itemName;
	@Column(name = "ITEM_CATEGORY")
	private String itemCategory;
	@Column(name = "itemPrice")
	private double price;
	
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = Menu.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "MENU_ID", nullable = false)
	private Menu menu;

	@Column(name = "IS_AVAILABLE")
	private boolean isAvailable;

	/*@ManyToMany(fetch = FetchType.LAZY, mappedBy = "itemSet")
	private Set<Order> orderSet;*/

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "item", cascade = CascadeType.ALL)
	private CartItem cartItem;

	@Column(name = "CREATED_TIME")
	private Instant createdTime;

}
