package com.publicissapient.lloyds.orderservice.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.publicissapient.lloyds.orderservice.api.dto.OrderDetailsTO;
import com.publicissapient.lloyds.orderservice.api.dto.ProductDetailsTO;
import com.publicissapient.lloyds.orderservice.converter.OrderDetailsTOToOrderEntity;
import com.publicissapient.lloyds.orderservice.converter.OrderEntityToOrderDetailsTO;
import com.publicissapient.lloyds.orderservice.doaminmodel.repository.OrderRepository;
import com.publicissapient.lloyds.orderservice.domainmodel.entity.OrderEntity;
import com.publicissapient.lloyds.orderservice.domainmodel.entity.ProductDetails;
import com.publicissapient.lloyds.orderservice.exception.OrderServiceCustomException;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {

	@InjectMocks
	private OrderServiceImpl orderServiceImpl;

	@Mock
	private OrderRepository orderRepository;

	@Mock
	private OrderDetailsTOToOrderEntity orderDetailsTOToOrderEntity;

	@Mock
	private OrderEntityToOrderDetailsTO orderEntityToOrderDetailsTO;

	@Test
	public void testSaveOrder() {
		OrderDetailsTO orderDetailsTO = new OrderDetailsTO();
		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setOrderId(1L);
		Mockito.when(orderDetailsTOToOrderEntity.convert(orderDetailsTO)).thenReturn(orderEntity);
		Mockito.when(orderRepository.save(orderEntity)).thenReturn(orderEntity);
		OrderDetailsTO saveOrder = orderServiceImpl.saveOrder(orderDetailsTO);
		Mockito.verify(orderRepository, times(1)).save(orderEntity);
		assertEquals(orderDetailsTO, saveOrder);
	}

	@Test
	public void testUpdateOrder() {

		ProductDetailsTO UpdateProductDetailsTO = new ProductDetailsTO("M", "Cat2", "1000", 2, "Black");
		OrderDetailsTO updatedOrderDetailsTO = new OrderDetailsTO();
		updatedOrderDetailsTO.setOrderId(1L);
		updatedOrderDetailsTO.setDate(LocalDate.now());
		updatedOrderDetailsTO.setCustomerId("C001");
		updatedOrderDetailsTO.setProductCode("P001");
		updatedOrderDetailsTO.setProductDetails(UpdateProductDetailsTO);

		ProductDetails existingProductDetails = new ProductDetails("M", "Cat1", "100", 1, "Blue");
		ProductDetails productDetailsToUpdate = new ProductDetails("M", "Cat1", "100", 1, "Blue");
		OrderEntity existingOrderEntity = new OrderEntity(1l, LocalDate.now(), "C001", "P001", existingProductDetails);
		Mockito.when(orderRepository.getOrderByIdDateCustomerIdProductCode(1L, LocalDate.now(), "C001", "P001"))
				.thenReturn(existingOrderEntity);

		OrderEntity updatedOrderEntity = new OrderEntity(1l, LocalDate.now(), "C001", "P001", productDetailsToUpdate);
		OrderDetailsTO updatedOrder = orderServiceImpl.updateOrder(updatedOrderDetailsTO);
		Mockito.verify(orderRepository, times(1)).save(updatedOrderEntity);
		assertEquals(updatedOrderDetailsTO, updatedOrder);
	}

	@Test
	public void testUpdateOrderException() {

		ProductDetailsTO UpdateProductDetailsTO = new ProductDetailsTO("M", "Cat2", "1000", 2, "Black");
		OrderDetailsTO updatedOrderDetailsTO = new OrderDetailsTO();
		updatedOrderDetailsTO.setOrderId(1L);
		updatedOrderDetailsTO.setDate(LocalDate.now());
		updatedOrderDetailsTO.setCustomerId("C001");
		updatedOrderDetailsTO.setProductCode("P001");
		updatedOrderDetailsTO.setProductDetails(UpdateProductDetailsTO);

		ProductDetails productDetailsToUpdate = new ProductDetails("M", "Cat1", "100", 1, "Blue");
		OrderEntity existingOrderEntity = null;
		Mockito.when(orderRepository.getOrderByIdDateCustomerIdProductCode(1L, LocalDate.now(), "C001", "P001"))
				.thenReturn(existingOrderEntity);

		OrderEntity updatedOrderEntity = new OrderEntity(1l, LocalDate.now(), "C001", "P001", productDetailsToUpdate);

		assertThrows(OrderServiceCustomException.class, () -> orderServiceImpl.updateOrder(updatedOrderDetailsTO));
		Mockito.verify(orderRepository, times(0)).save(updatedOrderEntity);
	}

	@Test
	public void testSearchOrder() {
		List<OrderEntity> orderList = new ArrayList<>();
		OrderEntity orderEntity1 = new OrderEntity(1l, LocalDate.now(), "C001", "P001", null);
		OrderEntity orderEntity2 = new OrderEntity(2l, LocalDate.now(), "C002", "P002", null);
		orderList.add(orderEntity1);
		orderList.add(orderEntity2);
		Mockito.when(orderRepository.searchOrder(1L, "C001", LocalDate.now(), "P001", "date")).thenReturn(orderList);
		List<OrderDetailsTO> searchOrder = orderServiceImpl.searchOrder(1L, "C001", LocalDate.now(), "P001", "date");
		Mockito.verify(orderRepository, times(1)).searchOrder(1L, "C001", LocalDate.now(), "P001", "date");
		assertEquals(2, searchOrder.size());
	}

}
