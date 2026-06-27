package com.OrderService.OrderService.Dtos;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.OrderService.OrderService.Enums.ProductStatus;



public class ProductDto {

	private Long id;

	private String name;
	private String description;
	private BigDecimal price; // 129999.00
	private BigDecimal discount; // 10% or fixed

	private Integer stockQuantity;

	private String brand;

	private ProductStatus status;
	private List<ProductImageDto> images = new ArrayList<>();

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public Integer getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(Integer stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public ProductStatus getStatus() {
		return status;
	}

	public void setStatus(ProductStatus status) {
		this.status = status;
	}

	public List<ProductImageDto> getImages() {
		return images;
	}

	public void setImages(List<ProductImageDto> images) {
		this.images = images;
	}

}
