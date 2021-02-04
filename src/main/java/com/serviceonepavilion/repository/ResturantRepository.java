package com.serviceonepavilion.repository;

import com.serviceonepavilion.entities.Resturant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResturantRepository extends CrudRepository<Resturant,Integer> {

}
