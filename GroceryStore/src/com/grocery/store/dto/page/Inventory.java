package com.grocery.store.dto.page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.grocery.store.dto.database.Category;
import com.grocery.store.dto.database.DiscountCategory;
import com.grocery.store.dto.database.DiscountItem;
import com.grocery.store.dto.database.Item;

public class Inventory {

	private HashMap<Integer, Category> categoryMap = new HashMap<>();
	private final HashMap<Integer, Item> itemMap = new HashMap<>();
	private final HashMap<Integer, Integer> itemQuantity = new HashMap<>();
	private final HashMap<Integer, DiscountCategory> categoryDiscountMap = new HashMap<>();
	private final HashMap<Integer, DiscountItem> itemDiscountMap = new HashMap<>();

	private final List<Order> orderList = new ArrayList<>();

	private static Inventory instance;

	private Inventory() {
	}

	public static Inventory getInstance() {
		if (instance == null) {
			instance = new Inventory();
		}
		return instance;
	}

	public HashMap<Integer, Category> getCategoryMap() {
		return categoryMap;
	}

	public void setCategoryMap(HashMap<Integer, Category> categoryMap) {
		this.categoryMap = categoryMap;
	}

	public HashMap<Integer, Item> getItemMap() {
		return itemMap;
	}

	public HashMap<Integer, Integer> getItemQuantity() {
		return itemQuantity;
	}

	public HashMap<Integer, DiscountCategory> getCategoryDiscountMap() {
		return categoryDiscountMap;
	}

	public HashMap<Integer, DiscountItem> getItemDiscountMap() {
		return itemDiscountMap;
	}

	public List<Order> getOrderList() {
		return orderList;
	}

}
