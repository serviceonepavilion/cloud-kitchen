package com.serviceonepavilion.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.serviceonepavilion.entities.Menu;
import com.serviceonepavilion.service.MenuService;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

	@Autowired
	MenuService menuService;

	@GetMapping
	public Menu menu(@PathVariable int resturantId) {
		Menu menu = menuService.getMenuByResturantId(resturantId);
		return menu;
	}

	@PostMapping
	public Menu saveMenu(@RequestBody Menu menu) {
		return menuService.saveorUpdateMenu(menu);
	}
	
	@PutMapping
	public Menu updateMenu(@RequestBody Menu menu) {
		return menuService.saveorUpdateMenu(menu);
	}

	@DeleteMapping("/{menuId}")
	public int deleteMenu(@PathVariable int menuId) {
		return menuService.deleteMenu(menuId);
	}

}
