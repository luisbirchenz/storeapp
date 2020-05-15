package com.lab.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class OrderCreateDTO {
	private Long orderId;
	private LocalDate orderDate;
	private LocalDate requiredDate;
	private LocalDate shippedDate;
	private String orderStatus;
	private String orderType;
}
