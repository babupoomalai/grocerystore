package com.grocery.store.dto.page;

import java.util.HashMap;
import java.util.Map;

public class Cart {

	private Map<Integer, CartItem> itemList = new HashMap<>();
	private double amount;
	private double discountAmount = 0;
	private double finalAmount;

	public Map<Integer, CartItem> getItemMap() {
		return itemList;
	}

	public void setItemMap(Map<Integer, CartItem> itemList) {
		this.itemList = itemList;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(double discount) {
		this.discountAmount = discount;
	}

	public double getFinalAmount() {
		return finalAmount;
	}

	public void setFinalAmount(double finalAmount) {
		this.finalAmount = finalAmount;
	}

}
