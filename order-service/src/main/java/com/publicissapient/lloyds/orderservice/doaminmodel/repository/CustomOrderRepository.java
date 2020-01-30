package com.publicissapient.lloyds.orderservice.doaminmodel.repository;

import java.time.LocalDate;
import java.util.List;

import com.publicissapient.lloyds.orderservice.domainmodel.entity.OrderEntity;

public interface CustomOrderRepository {

	List<OrderEntity> searchOrder(Long orderId, String customerId, LocalDate date, String productCode, String sortBy);

}
