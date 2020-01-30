package com.publicissapient.lloyds.orderservice.converter;

import org.springframework.stereotype.Component;

import com.publicissapient.lloyds.orderservice.api.dto.OrderDetailsTO;
import com.publicissapient.lloyds.orderservice.domainmodel.entity.OrderEntity;
import com.publicissapient.lloyds.orderservice.domainmodel.entity.ProductDetails;

@Component
public class OrderDetailsTOToOrderEntity {

	public OrderEntity convert(OrderDetailsTO orderdetailsTO) {
		OrderEntity orderEntity = new OrderEntity();
		ProductDetails productDetails = getProductDetails(orderdetailsTO);
		orderEntity.setCustomerId(orderdetailsTO.getCustomerId());
		orderEntity.setDate(orderdetailsTO.getDate());
		orderEntity.setProductCode(orderdetailsTO.getProductCode());
		orderEntity.setProductDetails(productDetails);
		return orderEntity;
		
	}

	private ProductDetails getProductDetails(OrderDetailsTO orderdetailsTO) {
		ProductDetails productDetails = new ProductDetails();
		productDetails.setCategory(orderdetailsTO.getProductDetails().getCategory());
		productDetails.setColor(orderdetailsTO.getProductDetails().getColor());
		productDetails.setPrice(orderdetailsTO.getProductDetails().getPrice());
		productDetails.setQuantity(orderdetailsTO.getProductDetails().getQuantity());
		productDetails.setSize(orderdetailsTO.getProductDetails().getSize());
		return productDetails;
	}
}
