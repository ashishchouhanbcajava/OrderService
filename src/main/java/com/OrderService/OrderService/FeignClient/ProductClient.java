package com.OrderService.OrderService.FeignClient;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.OrderService.OrderService.Dtos.ProductDto;


@FeignClient(name = "product-service")
public interface ProductClient {

	@GetMapping("product/getProductDto/{id}")
	ProductDto getProduct(@PathVariable Long id);
}
