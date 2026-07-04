package com.OrderService.OrderService.FeignClientServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.OrderService.OrderService.Dtos.ProductDto;
import com.OrderService.OrderService.FeignClient.ProductClient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class ProductClientService {

	@Autowired
	private ProductClient productClient;

	@Retry(name = "product-service", fallbackMethod = "productServiceFallBack")
	@CircuitBreaker(name = "product-service", fallbackMethod = "productServiceFallBack")
	public ProductDto getProduct(Long id) {
		System.out.println("Calling Product");
		return productClient.getProduct(id);
	}

	public ProductDto productServiceFallBack(Long id, Throwable t) {

		System.out.println("Fallback Executed");
		t.printStackTrace();

		ProductDto dto = new ProductDto();

		dto.setId(id);
		dto.setName("Product Unavailable");

		return dto;
	}

}
