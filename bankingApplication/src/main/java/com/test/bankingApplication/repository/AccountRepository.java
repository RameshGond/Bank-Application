package com.test.bankingApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.bankingApplication.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

}
