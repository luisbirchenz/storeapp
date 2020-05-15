package com.lab.restapi;

import java.util.concurrent.CompletableFuture;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lab.command.service.api.OrderCommandService;
import com.lab.dto.OrderCreateDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/order")
@RequiredArgsConstructor
public class OrderCommandRestApi {
	
	private final OrderCommandService orderCommandService;
	
	@PostMapping
	public CompletableFuture<String> createOrder(@RequestBody OrderCreateDTO dto) {
		return orderCommandService.createOrder(dto);
	}

}
