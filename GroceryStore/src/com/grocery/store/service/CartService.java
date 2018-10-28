package com.grocery.store.service;

public interface CartService {

	public boolean addToCart(int productCode, int quantity);

	public boolean checkoutCart(String customerName, int age, boolean isStoreEmployee);

}
