package com.publicissapient.lloyds.orderservice.service;

import java.time.LocalDate;
import java.util.List;

import com.publicissapient.lloyds.orderservice.api.dto.OrderDetailsTO;

public interface OrderService {

	public OrderDetailsTO saveOrder(OrderDetailsTO order);

	public OrderDetailsTO updateOrder(OrderDetailsTO order);

	public List<OrderDetailsTO> searchOrder(Long orderId, String customerId, LocalDate date, String productCode,
			String sortBy);

}
