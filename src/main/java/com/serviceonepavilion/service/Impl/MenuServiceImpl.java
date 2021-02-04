package com.serviceonepavilion.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serviceonepavilion.entities.Item;
import com.serviceonepavilion.entities.Menu;
import com.serviceonepavilion.repository.ItemRepository;
import com.serviceonepavilion.repository.MenuRepository;
import com.serviceonepavilion.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService{

	@Autowired
	MenuRepository menuRepository;

	public Menu saveorUpdateMenu(Menu menu) {
		Menu m = null;
		if (!menuRepository.existsById(menu.getMenuId())) {
			m = menuRepository.save(menu);
		} else {
			//m = menuRepository.update();
			// TODO
			m = menuRepository.save(menu);
		}
		return m;
	}

	public int deleteMenu(int id) {
		menuRepository.deleteById(id);
		return id;
	}

	public Menu getMenuByResturantId(int id) {
		return menuRepository.findById(id).get();
	}

	public List<Menu> findAllMenu() {
		//List<Menu> listItem = menuRepository.findAll();
		List<Menu> listItem = new ArrayList<>();
		menuRepository.findAll().forEach(listItem::add);
		return listItem;
	}
	
}
