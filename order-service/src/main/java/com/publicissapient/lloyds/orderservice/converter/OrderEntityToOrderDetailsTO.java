
package com.publicissapient.lloyds.orderservice.converter;

import org.springframework.stereotype.Component;

import com.publicissapient.lloyds.orderservice.api.dto.OrderDetailsTO;
import com.publicissapient.lloyds.orderservice.api.dto.ProductDetailsTO;
import com.publicissapient.lloyds.orderservice.domainmodel.entity.OrderEntity;

@Component
public class OrderEntityToOrderDetailsTO {

	private ProductDetailsTO getProductDetails(OrderEntity orderEntity) {
		ProductDetailsTO productDetails = new ProductDetailsTO();
		productDetails.setCategory(orderEntity.getProductDetails().getCategory());
		productDetails.setColor(orderEntity.getProductDetails().getColor());
		productDetails.setPrice(orderEntity.getProductDetails().getPrice());
		productDetails.setQuantity(orderEntity.getProductDetails().getQuantity());
		productDetails.setSize(orderEntity.getProductDetails().getSize());
		return productDetails;
	}

	public OrderDetailsTO convert(OrderEntity orderEntity) {
		OrderDetailsTO orderDetailsTO = new OrderDetailsTO();
		ProductDetailsTO productDetailsTO = getProductDetails(orderEntity);
		orderDetailsTO.setOrderId(orderEntity.getOrderId());
		orderDetailsTO.setCustomerId(orderEntity.getCustomerId());
		orderDetailsTO.setDate(orderEntity.getDate());
		orderDetailsTO.setProductCode(orderEntity.getProductCode());
		orderDetailsTO.setProductDetails(productDetailsTO);
		return orderDetailsTO;
	}
}
