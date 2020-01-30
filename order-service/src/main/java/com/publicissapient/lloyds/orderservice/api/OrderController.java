package com.publicissapient.lloyds.orderservice.api;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.publicissapient.lloyds.orderservice.api.dto.OrderDetailsTO;
import com.publicissapient.lloyds.orderservice.service.OrderService;

@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping("/api/order")
	public ResponseEntity<OrderDetailsTO> saveOrder(@RequestBody OrderDetailsTO order) {
		OrderDetailsTO orderDetailsTO = orderService.saveOrder(order);
		return new ResponseEntity<>(orderDetailsTO, HttpStatus.CREATED);
	}

	@PutMapping("/api/order")
	public ResponseEntity<OrderDetailsTO>  updateOrder(@RequestBody OrderDetailsTO orderDetailsTO) {
		if(null == orderDetailsTO.getOrderId()) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		OrderDetailsTO updateOrder = orderService.updateOrder(orderDetailsTO);
		return new ResponseEntity<>(updateOrder, HttpStatus.OK);
	}

	@GetMapping("/api/order")
	public ResponseEntity<List<OrderDetailsTO>>  searchOrder(@RequestParam(value = "orderId", required = false) Long orderId,
			@RequestParam(value = "customerId", required = false) String customerId,
			@RequestParam(value = "date", required = false) LocalDate date,
			@RequestParam(value = "productCode", required = false) String productCode,
			@RequestParam(value = "sortBy", required = false) String sortBy) {
		return new ResponseEntity<>(orderService.searchOrder(orderId, customerId, date, productCode, sortBy), HttpStatus.OK);
	}

}
