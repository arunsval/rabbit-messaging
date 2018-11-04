package com.arun.springboot.rabbitmessaging.resource;

import java.io.Serializable;
import java.math.BigDecimal;

public class Product implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private Long quantity;
	private BigDecimal priceForEach;
	private BigDecimal totalCost;
	
	
	public Product() {
		super();
	}
	public Product(String name, Long quantity, BigDecimal priceForEach, BigDecimal totalCost) {
		super();
		this.name = name;
		this.quantity = quantity;
		this.priceForEach = priceForEach;
		this.totalCost = totalCost;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getPriceForEach() {
		return priceForEach;
	}
	public void setPriceForEach(BigDecimal priceForEach) {
		this.priceForEach = priceForEach;
	}
	public BigDecimal getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}
	
}
