package com.lab.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import com.lab.command.CreatePaymentCommand;
import com.lab.event.PaymentCreatedEvent;
import com.lab.status.PaymentStatus;

@Aggregate
public class PaymentAggregate {
    @AggregateIdentifier
    private String paymentId;

    private String orderId;

    private PaymentStatus paymentStatus;

    public PaymentAggregate() {
    }

    @CommandHandler
    public PaymentAggregate(CreatePaymentCommand createPaymentCommand){
        AggregateLifecycle.apply(new PaymentCreatedEvent(createPaymentCommand.paymentId, createPaymentCommand.orderId));
    }

    @EventSourcingHandler
    protected void on(PaymentCreatedEvent paymentCreatedEvent){
        this.paymentId = paymentCreatedEvent.paymentId;
        this.orderId = paymentCreatedEvent.orderId;
        this.paymentStatus = PaymentStatus.PAID;
    }
}
