package com.serviceonepavilion.controllers;

import com.serviceonepavilion.entities.Customer;
import com.serviceonepavilion.entities.Resturant;
import com.serviceonepavilion.service.ResturantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/resturant")
@RestController
public class ResturantController {
    @Autowired
    private ResturantService resturantService;

    @PostMapping
    public ResponseEntity<String> addRestaurant(@RequestBody Resturant resturant) {
        System.out.println(resturant);
         resturantService.addRestaurant(resturant);
         return new ResponseEntity<String>("Restaurant added", HttpStatus.OK);
    }
}
