package com.lab.command.service.api;

import java.util.concurrent.CompletableFuture;

import com.lab.dto.OrderCreateDTO;

public interface OrderCommandService {
    public CompletableFuture<String> createOrder(OrderCreateDTO orderCreateDTO);
}
