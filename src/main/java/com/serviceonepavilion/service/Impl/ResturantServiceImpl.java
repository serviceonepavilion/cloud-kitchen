package com.serviceonepavilion.service.Impl;

import com.serviceonepavilion.dto.CartDto;
import com.serviceonepavilion.entities.Resturant;
import com.serviceonepavilion.repository.ResturantRepository;
import com.serviceonepavilion.service.ResturantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ResturantServiceImpl implements ResturantService {

    @Autowired
    private ResturantRepository resturantRepository;

    @Override
    @Transactional
    public void addRestaurant(Resturant resturant){
        System.out.println(resturant);
        resturantRepository.save(resturant);
    }
}
