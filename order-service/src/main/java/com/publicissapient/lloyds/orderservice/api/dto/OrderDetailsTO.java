package com.publicissapient.lloyds.orderservice.api.dto;


import java.io.Serializable;
import java.time.LocalDate;


public class OrderDetailsTO implements Serializable{


	/**
	 * serialVersionUID
	 */
	
	private static final long serialVersionUID = -1167649350483816566L;

	private Long orderId;
	
	private LocalDate date;
	
	private String customerId;
	
	private String productCode;
	
	private ProductDetailsTO productDetails;
	

	/**
	 * Default constructor.
	 */
	public OrderDetailsTO() {
		super();
	}


	/**
	 * @return the orderId
	 */
	public Long getOrderId() {
		return orderId;
	}


	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}


	/**
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}


	/**
	 * @param date the date to set
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}


	/**
	 * @return the customerId
	 */
	public String getCustomerId() {
		return customerId;
	}


	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}


	/**
	 * @return the productCode
	 */
	public String getProductCode() {
		return productCode;
	}


	/**
	 * @param productCode the productCode to set
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}


	/**
	 * @return the productDetails
	 */
	public ProductDetailsTO getProductDetails() {
		return productDetails;
	}


	/**
	 * @param productDetails the productDetails to set
	 */
	public void setProductDetails(ProductDetailsTO productDetails) {
		this.productDetails = productDetails;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (orderId ^ (orderId >>> 32));
		return result;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDetailsTO other = (OrderDetailsTO) obj;
		if (orderId != other.orderId)
			return false;
		return true;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderDetailsTO [orderId=").append(orderId).append(", date=").append(date)
				.append(", customerId=").append(customerId).append(", productCode=").append(productCode)
				.append(", productDetails=").append(productDetails).append("]");
		return builder.toString();
	}
	
	public OrderDetailsTO(long orderId, LocalDate date, String customerId, ProductDetailsTO product) {
		super();
		this.orderId = orderId;
		this.date = date;
		this.customerId = customerId;
		this.productDetails = product;
	}
	


}
