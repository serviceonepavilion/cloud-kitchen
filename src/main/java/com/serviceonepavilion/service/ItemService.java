package com.serviceonepavilion.service;

import java.util.List;

import com.serviceonepavilion.entities.Item;

public interface ItemService {

	Item saveorUpdateItem(Item item);

	int removeItem(int id);

	Item getItemById(int id);

	List<Item> findAllItem();

}