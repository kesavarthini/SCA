package com.publicissapient.lloyds.orderservice.domainmodel.entity;

import javax.persistence.Embeddable;

@Embeddable
public class ProductDetails{
	

	private String size;
	private String category;
	private String price;
	private long quantity;
	private String color;
	
	public ProductDetails() {
		super();
	}
	public ProductDetails(String size, String category, String price, long quantity, String color) {
		super();
		this.size = size;
		this.category = category;
		this.price = price;
		this.quantity = quantity;
		this.color = color;
	}
	/**
	 * @return the size
	 */
	public String getSize() {
		return size;
	}
	/**
	 * @param size the size to set
	 */
	public void setSize(String size) {
		this.size = size;
	}
	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}
	/**
	 * @return the quantity
	 */
	public long getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}
	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}
	
	
	

}
