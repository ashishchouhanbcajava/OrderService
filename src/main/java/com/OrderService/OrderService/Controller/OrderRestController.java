package com.OrderService.OrderService.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.OrderService.OrderService.Beans.Order;
import com.OrderService.OrderService.Dtos.OrderItemsDto;
import com.OrderService.OrderService.Dtos.OrdersDto;
import com.OrderService.OrderService.Dtos.ProductDto;
import com.OrderService.OrderService.FeignClient.ProductClient;
import com.OrderService.OrderService.Services.OrderService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.servlet.http.HttpServletRequest;

@RequestMapping("/order")
@RestController
public class OrderRestController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private ProductClient productClient;

	@Autowired
	private ApplicationContext context;

	@GetMapping("/getAll")
	public ResponseEntity<?> getALL() {
		List<Order> all = orderService.getAll();
		if (all == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ArrayList<>());
		}
		return ResponseEntity.ok(all);
	}

	@GetMapping("/beans")
	public String beans() {
		String[] beans = context.getBeanDefinitionNames();

		for (String bean : beans) {
			System.out.println("im in iteration beans : " + bean);
			if (bean.toLowerCase().contains("circuit")) {
				System.out.println(bean);
			}
		}

		return "Done";
	}

	@GetMapping("/test")
	public String test() {
		return orderService.test();
	}

	@CircuitBreaker(name = "product-service", fallbackMethod = "productServiceFallBack")
	@GetMapping("/getOrdersByUserId/{id}")
	public ResponseEntity<?> getOrders(@NotNull @PathVariable Integer id, HttpServletRequest request) {

		String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getRequestURI();

		if (request.getQueryString() != null) {
			url += "?" + request.getQueryString();
		}

		System.err.println("url : " + url);
		List<Order> byUserId = orderService.getByUserId(id);

		if (byUserId == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ArrayList<>());

		}

		List<OrdersDto> collect = byUserId.stream().map(e -> {
			OrdersDto dto = new OrdersDto();
			dto.setOrderId(e.getOrderId());

			List<OrderItemsDto> itemList = e.getOrderItems().stream().map(f -> {
				List<ProductDto> products = new ArrayList<>();
				OrderItemsDto item = new OrderItemsDto();
				item.setId(f.getId());
				item.setName(f.getName());
				ProductDto product = productClient.getProduct(f.getProductId());
				products.add(product);
				item.setProduct(products);

				return item;
			}).toList();
			dto.setItemsDtos(itemList);

			return dto;
		}).collect(Collectors.toList());

		return ResponseEntity.ok(collect);
	}

	public ResponseEntity<?> productServiceFallBack(Integer id, HttpServletRequest request, Exception e) {
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Product Service is unavailable");
	}

	@GetMapping("/getOrder/{id}")
	public ResponseEntity<?> getOrder(@NotNull @PathVariable Long id) {

		return ResponseEntity.ok(orderService.getOrder(id));
	}

	@PostMapping("/addOrder")
	public ResponseEntity<?> save(@NotNull @RequestBody Order order) {
		// TODO: process POST request

		String orderId = orderService.getLatestOrderId();
		order.setOrderId(orderId);
		order.getOrderItems().forEach(e -> {
			e.setOrder(order);
		});
		Order save = orderService.save(order);
		return ResponseEntity.ok(save);

	}

}
