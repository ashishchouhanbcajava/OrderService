package com.OrderService.OrderService.Dtos;


import java.util.ArrayList;
import java.util.List;

public class OrdersDto {

	private String orderId;
	private List<OrderItemsDto> itemsDtos = new ArrayList<>();

	public OrdersDto(String orderId, List<OrderItemsDto> dtos) {
		this.orderId = orderId;
		this.itemsDtos = dtos;
	}

	public OrdersDto() {
		super();
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public List<OrderItemsDto> getItemsDtos() {
		return itemsDtos;
	}

	public void setItemsDtos(List<OrderItemsDto> itemsDtos) {
		this.itemsDtos = itemsDtos;
	}

}
