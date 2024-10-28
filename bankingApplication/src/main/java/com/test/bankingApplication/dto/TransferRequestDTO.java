package com.test.bankingApplication.dto;

import lombok.Data;

@Data
public class TransferRequestDTO {
	private Long customerId;
    private String fromType;
    private String toType;
    private double amount;
}
