package com.OrderService.OrderService.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OrderService.OrderService.Beans.Order;
//import com.OrderService.OrdSerService.Kafka.OrderEventProducer;
import com.OrderService.OrderService.Repository.OrderRepository;

import eventClasses.OrderCreateEvent;
import eventClasses.OrderItemsDto;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

//	@Autowired
//	private OrderEventProducer producer;

	public Order save(Order order) {

		orderRepository.save(order);

		List<OrderItemsDto> list = order.getOrderItems().stream().map(e -> {
			OrderItemsDto dto = new OrderItemsDto();
			dto.setProductId(e.getProductId());
			dto.setQuantity(e.getQuantity());
			return dto;
		}).toList();
//		producer.publish(new OrderCreateEvent(order.getId(), order.getUserId(), list));
		return order;
	}

//	public Order save(Order order) {
//
//		return orderRepository.save(order);
//	}

	public List<Order> getAll() {
		return orderRepository.findAll();
	}

	public Order getOrder(Long id) {
		return orderRepository.findById(id).get();
	}

	public void delete(Long id) {
		orderRepository.deleteById(id);
	}

	public List<Order> getByUserId(Integer id) {
		return orderRepository.getUserOrders(id);
	}

	public String getLatestOrderId() {

		Order topByOrderByIdDesc = orderRepository.findTopByOrderByIdDesc();

		// Case 1: No order exists in DB
		if (topByOrderByIdDesc == null) {
			return "ORD_0001";
		}

		// Extract numeric part → ORD_0007 → 0007
		int lastNumber = Integer.parseInt(topByOrderByIdDesc.getOrderId().replace("ORD_", ""));

		// Increment
		int newNumber = lastNumber + 1;

		// Format with leading zeros
		return String.format("ORD_%04d", newNumber);
	}

}
