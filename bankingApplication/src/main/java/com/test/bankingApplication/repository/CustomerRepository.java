package com.test.bankingApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.bankingApplication.model.Customer;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
}
