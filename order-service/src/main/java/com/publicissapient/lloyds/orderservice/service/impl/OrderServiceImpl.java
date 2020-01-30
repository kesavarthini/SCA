package com.publicissapient.lloyds.orderservice.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.publicissapient.lloyds.orderservice.api.dto.OrderDetailsTO;
import com.publicissapient.lloyds.orderservice.converter.OrderDetailsTOToOrderEntity;
import com.publicissapient.lloyds.orderservice.converter.OrderEntityToOrderDetailsTO;
import com.publicissapient.lloyds.orderservice.doaminmodel.repository.OrderRepository;
import com.publicissapient.lloyds.orderservice.domainmodel.entity.OrderEntity;
import com.publicissapient.lloyds.orderservice.domainmodel.entity.ProductDetails;
import com.publicissapient.lloyds.orderservice.exception.OrderServiceCustomException;
import com.publicissapient.lloyds.orderservice.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderDetailsTOToOrderEntity orderDetailsTOToOrderEntity;

	@Autowired
	private OrderEntityToOrderDetailsTO orderEntityToOrderDetailsTO;

	@Override
	public OrderDetailsTO saveOrder(OrderDetailsTO orderDetailsTO) {
		OrderEntity orderEntity = orderDetailsTOToOrderEntity.convert(orderDetailsTO);
		OrderEntity entity = orderRepository.save(orderEntity);
		orderDetailsTO.setOrderId(entity.getOrderId());
		return orderDetailsTO;
	}

	@Override
	public OrderDetailsTO updateOrder(OrderDetailsTO orderDetailsTO) {
		OrderEntity orderEntity = orderRepository.getOrderByIdDateCustomerIdProductCode(orderDetailsTO.getOrderId(),
				orderDetailsTO.getDate(), orderDetailsTO.getCustomerId(), orderDetailsTO.getProductCode());

		if (null != orderEntity) {
			ProductDetails productDetails = new ProductDetails(orderDetailsTO.getProductDetails().getSize(),
					orderDetailsTO.getProductDetails().getCategory(), orderDetailsTO.getProductDetails().getPrice(),
					orderDetailsTO.getProductDetails().getQuantity(), orderDetailsTO.getProductDetails().getColor());
			orderEntity.setProductDetails(productDetails);
			orderRepository.save(orderEntity);
		} else {
			throw new OrderServiceCustomException("Not Found");
		}
		return orderDetailsTO;
	}

	@Override
	public List<OrderDetailsTO> searchOrder(Long orderId, String customerId, LocalDate date, String productCode,
			String sortBy) {
		List<OrderEntity> orderList = orderRepository.searchOrder(orderId, customerId, date, productCode, sortBy);
		if(CollectionUtils.isEmpty(orderList)) {
			throw new OrderServiceCustomException("Not Found");
		}
		List<OrderDetailsTO> orderDetailsTOsList = new ArrayList<OrderDetailsTO>();
		orderList.forEach(order -> orderDetailsTOsList.add(orderEntityToOrderDetailsTO.convert(order)));
		return orderDetailsTOsList;
	}

}
