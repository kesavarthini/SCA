package com.publicissapient.lloyds.orderservice.doaminmodel.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.publicissapient.lloyds.orderservice.domainmodel.entity.OrderEntity;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long>, CustomOrderRepository {
	
	@Query("SELECT order from OrderEntity order WHERE order.orderId = :orderId AND order.date = :date AND order.customerId = :customerId AND order.productCode = :productCode")
	public OrderEntity getOrderByIdDateCustomerIdProductCode(@Param("orderId") Long orderId, @Param("date") LocalDate date,@Param("customerId") String customerId, @Param("productCode") String productCode);

}
