package com.test.bankingApplication.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
@Data
@Entity
public class Transaction {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private double amount;
	    private LocalDateTime timestamp;
	    private String transactionType; // "Deposit", "Withdrawal", etc.
	    private String description;

	    @ManyToOne
	    @JoinColumn(name = "account_id")
	    private Account account;

}
