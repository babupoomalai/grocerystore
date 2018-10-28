package com.grocery.store.service;

import com.grocery.store.dto.database.Category;
import com.grocery.store.dto.database.DiscountCategory;
import com.grocery.store.dto.database.DiscountItem;
import com.grocery.store.dto.database.Item;

/**
 * 
 * @author babu
 *
 */
public interface InventoryService {

	public boolean addItem(Item product, int quantity);

	public boolean addCategory(Category category);

	public boolean addDiscountCategory(DiscountCategory discountCategory);

	public boolean addDiscountItem(DiscountItem discountItem);

	public void printItems();

	public void printItems(int category);

	public void printSales();

}
