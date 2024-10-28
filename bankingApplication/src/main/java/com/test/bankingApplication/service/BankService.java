package com.test.bankingApplication.service;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.bankingApplication.model.Account;
import com.test.bankingApplication.model.Customer;
import com.test.bankingApplication.model.Transaction;
import com.test.bankingApplication.repository.AccountRepository;
import com.test.bankingApplication.repository.CustomerRepository;
import com.test.bankingApplication.repository.TransactionRepository;

@Service
public class BankService {
	  @Autowired
	    private CustomerRepository customerRepository;

	    @Autowired
	    private AccountRepository accountRepository;

	    @Autowired
	    private TransactionRepository transactionRepository;

	    public Customer onboardCustomer(String name, String email) {
	        Customer customer = new Customer();
	        customer.setName(name);
	        customer.setEmail(email);

	        Account currentAccount = new Account();
	        currentAccount.setType("Current");
	        currentAccount.setBalance(0);
	        currentAccount.setCustomer(customer);

	        Account savingsAccount = new Account();
	        savingsAccount.setType("Savings");
	        savingsAccount.setBalance(500); // Joining bonus
	        savingsAccount.setCustomer(customer);

	        customer.getAccounts().addAll(Arrays.asList(currentAccount, savingsAccount));
	        return customerRepository.save(customer);
	    }

	    public void transferBetweenAccounts(Long customerId, String fromType, String toType, double amount) throws Exception {
	    	
	    	System.out.println(" I am trying to get the data of customer "+customerId);
	        Customer customer = customerRepository.findById(customerId).orElseThrow();
	        
	        System.out.println("This is the reponse of customer object "
	        		+ ""+customer.getEmail()
	        		+ ""+customer.getName()
	        		+ "");
	        Account fromAccount = customer.getAccounts().stream().filter(a -> a.getType().equals(fromType)).findFirst().orElseThrow();
	        Account toAccount = customer.getAccounts().stream().filter(a -> a.getType().equals(toType)).findFirst().orElseThrow();
	        
	        System.out.println(fromAccount.getBalance()+" this is the balance ");

	        if (fromAccount.getBalance() < amount) {
	            throw new Exception("Insufficient funds");
	        }

	        fromAccount.setBalance(fromAccount.getBalance() - amount);
	        toAccount.setBalance(toAccount.getBalance() + amount);

	        Transaction transaction = new Transaction();
	        transaction.setAmount(amount);
	        transaction.setTimestamp(LocalDateTime.now());
	        transaction.setTransactionType("Transfer");
	        transaction.setDescription("Transfer from " + fromType + " to " + toType);
	        transaction.setAccount(fromAccount);

	        transactionRepository.save(transaction);
	        accountRepository.saveAll(Arrays.asList(fromAccount, toAccount));
	    }

	    public void makePayment(Long customerId, double amount) throws Exception {
	        Customer customer = customerRepository.findById(customerId).orElseThrow();
	        Account currentAccount = customer.getAccounts().stream().filter(a -> a.getType().equals("Current")).findFirst().orElseThrow();

	        if (currentAccount.getBalance() < amount) {
	            throw new Exception("Insufficient funds");
	        }

	        double charge = amount * 0.0005;
	        currentAccount.setBalance(currentAccount.getBalance() - (amount + charge));

	        Transaction transaction = new Transaction();
	        transaction.setAmount(amount);
	        transaction.setTimestamp(LocalDateTime.now());
	        transaction.setTransactionType("Payment");
	        transaction.setDescription("Payment of R" + amount + " with charge R" + charge);
	        transaction.setAccount(currentAccount);

	        transactionRepository.save(transaction);
	        accountRepository.save(currentAccount);
	    }
	}

