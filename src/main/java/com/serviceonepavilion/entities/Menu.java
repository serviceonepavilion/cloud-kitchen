package com.serviceonepavilion.entities;

import java.time.Instant;
import java.util.List;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "MENU")
public class Menu {
	@Id
	@Column(name = "MENU_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer menuId;

	@Column(name = "CATEGORY")
	private String category;
	
	@OneToMany( mappedBy = "menu",  cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Item.class)
	private List<Item> itemList;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Resturant.class )
	@PrimaryKeyJoinColumn
	private Resturant resturant;

	@Column(name = "CREATED_TIME")
	private Instant createdTime;

}
