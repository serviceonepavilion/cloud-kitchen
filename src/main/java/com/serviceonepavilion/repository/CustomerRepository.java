package com.serviceonepavilion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.serviceonepavilion.entities.Customer;
import com.serviceonepavilion.entities.Item;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer>{

	Optional<Customer> findByUserName(Integer userName);

	@Query(value="select new Customer(c.customerId,c.userName,c.otp,c.enabled) from Customer c WHERE c.userName = ?1")
	Optional<Customer> findByUserNameForAccountVerification(Integer userName);

	@Modifying // @Modifying is for telling spring-data-jpa that this query is an update operation and it requires executeUpdate() not executeQuery().
	@Query("update Customer c set c.enabled = 1 where c.userName = :userName")
	void enableAccount(Integer userName);
}
