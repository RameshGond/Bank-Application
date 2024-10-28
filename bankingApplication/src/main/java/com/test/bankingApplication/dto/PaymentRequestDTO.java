package com.test.bankingApplication.dto;

import lombok.Data;

@Data
public class PaymentRequestDTO {
	  private Long customerId;
	    private double amount;
}
