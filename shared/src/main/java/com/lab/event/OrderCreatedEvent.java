package com.lab.event;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@SuppressWarnings("unused")
public class OrderCreatedEvent {
	private final String orderId;
	private final LocalDate orderDate;
	private final LocalDate requiredDate;
	private final LocalDate shippedDate;
	private final String orderType;
	private final String status;
}
