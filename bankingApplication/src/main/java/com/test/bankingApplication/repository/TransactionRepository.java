package com.test.bankingApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.bankingApplication.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

}
