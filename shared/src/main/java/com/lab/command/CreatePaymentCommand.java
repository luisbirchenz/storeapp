package com.lab.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class CreatePaymentCommand {
    @TargetAggregateIdentifier
    public final String paymentId;

    public final String orderId;

    public CreatePaymentCommand(String paymentId, String orderId) {
        this.paymentId = paymentId;
        this.orderId = orderId;
    }
}
