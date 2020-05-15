package com.lab.manager;

import java.util.UUID;

import javax.inject.Inject;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;

import com.lab.command.CreatePaymentCommand;
import com.lab.command.CreateShippingCommand;
import com.lab.command.UpdateOrderStatusCommand;
import com.lab.domain.OrderStatus;
import com.lab.event.OrderCreatedEvent;
import com.lab.event.OrderShippedEvent;
import com.lab.event.OrderUpdatedEvent;
import com.lab.event.PaymentCreatedEvent;

@Saga
public class OrderManager {
    @Inject
    private transient CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderCreatedEvent orderCreatedEvent){
        String paymentId = UUID.randomUUID().toString();
        System.out.println("Saga invoked");

        //associate Saga
        SagaLifecycle.associateWith("paymentId", paymentId);

        System.out.println("order id" + orderCreatedEvent.getOrderId());

        //send the commands
        commandGateway.send(new CreatePaymentCommand(paymentId, orderCreatedEvent.getOrderId()));
    }

    @SagaEventHandler(associationProperty = "paymentId")
    public void handle(PaymentCreatedEvent paymentCreatedEvent){
        String shippingId = UUID.randomUUID().toString();

        System.out.println("Saga continued");

        //associate Saga with shipping
        SagaLifecycle.associateWith("shipping", shippingId);

        //send the create shipping command
        commandGateway.send(new CreateShippingCommand(shippingId, paymentCreatedEvent.orderId, paymentCreatedEvent.paymentId));
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderShippedEvent orderShippedEvent){
        commandGateway.send(new UpdateOrderStatusCommand(orderShippedEvent.orderId, String.valueOf(OrderStatus.SHIPPED)));
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderUpdatedEvent orderUpdatedEvent){
        SagaLifecycle.end();
    }
}
