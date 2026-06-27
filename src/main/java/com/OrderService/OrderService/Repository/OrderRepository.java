package com.OrderService.OrderService.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.OrderService.OrderService.Beans.Order;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query("FROM Order o WHERE o.userId=:id")
	List<Order> getUserOrders(Integer id);

//	@Query("SELECT o.orderId FROM Order o ORDER BY o.id DESC")
//	String findLatestOrderId();
	
	Order findTopByOrderByIdDesc();


}
