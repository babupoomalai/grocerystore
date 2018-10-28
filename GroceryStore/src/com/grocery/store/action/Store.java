package com.grocery.store.action;

import java.util.Calendar;

import com.grocery.store.common.Constant.PRODUCT_SI_MASS;
import com.grocery.store.dto.database.Category;
import com.grocery.store.dto.database.DiscountCategory;
import com.grocery.store.dto.database.Item;
import com.grocery.store.service.CartServiceImpl;
import com.grocery.store.service.InventoryService;
import com.grocery.store.service.InventoryServiceImpl;

/**
 * Main class
 * 
 * @author babu
 *
 */
public class Store {

	public static void main(String args[]) {
		InventoryService inventoryService = new InventoryServiceImpl();
		inventoryService.addCategory(new Category(1, "Dhal", 2.5));
		inventoryService.addCategory(new Category(2, "Personal care", 10));
		Calendar cal = Calendar.getInstance();
		cal.set(2018, 9, 01);
		Calendar expCal = Calendar.getInstance();
		expCal.setTime(cal.getTime());
		expCal.add(Calendar.MONTH, 2);
		inventoryService.addItem(new Item("Fresho toor dhal", 1, 1, cal.getTime(), expCal.getTime(), "TG401", 200,
				PRODUCT_SI_MASS.GRAM.getText(), 20), 10);
		inventoryService.addItem(new Item("Sanitary pad", 2, 2, cal.getTime(), expCal.getTime(), "T401", 500,
				PRODUCT_SI_MASS.GRAM.getText(), 100), 30);
		inventoryService.addDiscountCategory(new DiscountCategory(2, 10, expCal.getTime()));
		inventoryService.printItems();

		CartServiceImpl cartService = new CartServiceImpl();
		cartService.addToCart(2, 3);
		cartService.addToCart(1, 1);
		cartService.checkoutCart("Babu", 28, true);

		inventoryService.printSales();

	}

}
