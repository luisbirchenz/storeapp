package com.lab.event;

public class PaymentCreatedEvent {
    public final String paymentId;

    public final String orderId;

    public PaymentCreatedEvent(String paymentId, String orderId) {
        this.paymentId = paymentId;
        this.orderId = orderId;
    }
}
