package com.serviceonepavilion.service;

import java.util.List;

import com.serviceonepavilion.entities.Menu;

public interface MenuService {

	Menu saveorUpdateMenu(Menu menu);

	int deleteMenu(int id);

	Menu getMenuByResturantId(int id);

	List<Menu> findAllMenu();

}