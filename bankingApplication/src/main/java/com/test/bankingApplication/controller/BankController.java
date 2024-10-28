package com.test.bankingApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.bankingApplication.dto.CustomerRequestDTO;
import com.test.bankingApplication.dto.PaymentRequestDTO;
import com.test.bankingApplication.dto.TransferRequestDTO;
import com.test.bankingApplication.model.Customer;
import com.test.bankingApplication.service.BankService;
@RestController
@RequestMapping("/api/bank")
public class BankController {

    @Autowired
    private BankService bankService;

//    @PostMapping("/customer")
//    public ResponseEntity<Customer> onboardCustomer(@RequestParam String name, @RequestParam String email) {
//        Customer customer = bankService.onboardCustomer(name, email);
//        return ResponseEntity.ok(customer);
//    }
    
    @PostMapping("/customer")
    public ResponseEntity<Customer> onboardCustomer(@RequestBody CustomerRequestDTO customerRequest) {
        Customer customer = bankService.onboardCustomer(customerRequest.getName(), customerRequest.getEmail());
        return ResponseEntity.ok(customer);
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transferBetweenAccounts(@RequestBody TransferRequestDTO transferRequest) {
        try {
        	
            System.out.println(" Transfer object is set with these data for saving "
            		+ " "+transferRequest.getAmount()
            		+""+transferRequest.getCustomerId()
            		+ ""+transferRequest.getFromType()
            		+ ""
            		+ "");
            bankService.transferBetweenAccounts(
                transferRequest.getCustomerId(),
                transferRequest.getFromType(),
                transferRequest.getToType(),
                transferRequest.getAmount()
            );
            
     
            return ResponseEntity.ok("Transfer successful");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/payment")
    public ResponseEntity<String> makePayment(@RequestBody PaymentRequestDTO paymentRequest) {
        try {
            bankService.makePayment(paymentRequest.getCustomerId(), paymentRequest.getAmount());
            return ResponseEntity.ok("Payment successful");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
