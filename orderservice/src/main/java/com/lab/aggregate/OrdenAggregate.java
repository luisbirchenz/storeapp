package com.lab.aggregate;

import java.time.LocalDate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import com.lab.command.CreateOrderCommand;
import com.lab.domain.OrderStatus;
import com.lab.domain.OrderType;
import com.lab.event.OrderCreatedEvent;
import com.lab.event.OrderUpdatedEvent;

@Aggregate
public class OrdenAggregate {
	@AggregateIdentifier
	private String orderId;
	private LocalDate orderDate;
	private LocalDate requiredDate;
	private LocalDate shippedDate;
	private OrderType orderType;
	private OrderStatus status;
	
	public OrdenAggregate() {}
	
	@CommandHandler
	public OrdenAggregate(CreateOrderCommand createOrderCommand) {
		AggregateLifecycle.apply(new OrderCreatedEvent(
				createOrderCommand.getOrderId(),
				createOrderCommand.getOrderDate(),
				createOrderCommand.getRequiredDate(),
				createOrderCommand.getShippedDate(),
				createOrderCommand.getOrderType(),
				createOrderCommand.getStatus()
				));
	}

	
	@EventSourcingHandler
	protected void on(OrderCreatedEvent orderCreatedEvent) {
		this.orderId = orderCreatedEvent.getOrderId();
		this.orderDate = orderCreatedEvent.getOrderDate();
		this.requiredDate = orderCreatedEvent.getRequiredDate();
		this.shippedDate = orderCreatedEvent.getShippedDate();
		this.orderType = OrderType.valueOf(orderCreatedEvent.getOrderType());
		this.status = OrderStatus.valueOf(orderCreatedEvent.getStatus());
	}
	
    @EventSourcingHandler
    protected void on(OrderUpdatedEvent orderUpdatedEvent){
        this.orderId = orderUpdatedEvent.orderId;
        this.status = OrderStatus.valueOf(orderUpdatedEvent.orderStatus);
    }	
	
}
	
	

