package com.test.bankingApplication.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String type; // "Current" or "Savings"
	    private double balance;

	    @ManyToOne
	    @JoinColumn(name = "customer_id")
	    private Customer customer;

	    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
	    private List<Transaction> transactions = new ArrayList<>();
}
