package com.OrderService.OrderService.Dtos;


import java.util.ArrayList;
import java.util.List;

public class OrderItemsDto {

	private Long id;
	private String name;
	private List<ProductDto> product = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ProductDto> getProduct() {
		return product;
	}

	public void setProduct(List<ProductDto> product) {
		this.product = product;
	}

}
