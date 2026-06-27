//package com.OrderService.OrderService.Kafka;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Component;
//
//import eventClasses.OrderCreateEvent;
//
//@Component
//public class OrderEventProducer {
//
//	@Autowired
//	private KafkaTemplate<String, OrderCreateEvent> template;
//
//	public void publish(OrderCreateEvent event) {
//		template.send("order-created-topic", event);
//	}
//
//}
