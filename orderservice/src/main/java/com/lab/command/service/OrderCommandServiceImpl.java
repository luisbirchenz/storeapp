package com.lab.command.service;

import java.time.LocalDate;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import com.lab.command.CreateOrderCommand;
import com.lab.command.service.api.OrderCommandService;
import com.lab.dto.OrderCreateDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderCommandServiceImpl implements OrderCommandService{

    private final CommandGateway commandGateway;
	
	public CompletableFuture<String> createOrder(OrderCreateDTO orderCreateDTO) {
        return commandGateway.send(new CreateOrderCommand(
        			UUID.randomUUID().toString(),
        			orderCreateDTO.getOrderDate(),
        			orderCreateDTO.getRequiredDate(),
        			orderCreateDTO.getShippedDate(),
        			orderCreateDTO.getOrderType(),
        			orderCreateDTO.getOrderStatus()
        		));
	}

}
