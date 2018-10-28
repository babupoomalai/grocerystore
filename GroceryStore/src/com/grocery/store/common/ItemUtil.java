package com.grocery.store.common;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;

import com.grocery.store.dto.database.DiscountCategory;
import com.grocery.store.dto.database.DiscountItem;
import com.grocery.store.dto.database.Item;
import com.grocery.store.dto.page.Inventory;

public class ItemUtil {

	public static void displayItem(Item item, double discount, double taxPercent) {
		System.out
				.println(
						item.getName() + ", " + format(item.getWeight()) + item.getWeightMetricText() + ", Price: Rs."
								+ format(ItemUtil.getFinalPrice(item.getPrice(), taxPercent))
								+ (discount > 0
										? ", Discounted price: Rs."
												+ format(ItemUtil.getFinalPrice(item.getPrice(), discount, taxPercent))
										: ""));
	}

	/*
	 * Get the discounted value of item
	 */
	public static double getDiscountValue(Inventory inventory, Integer category, Integer productCode) {
		double categoryDiscount = 0;
		double itemDiscount = 0;

		HashMap<Integer, Item> itemMap = inventory.getItemMap();
		Item item = itemMap.get(productCode);
		HashMap<Integer, DiscountItem> itemDiscountMap = inventory.getItemDiscountMap();
		HashMap<Integer, DiscountCategory> categoryDiscountMap = inventory.getCategoryDiscountMap();

		if (category != null) {
			if (categoryDiscountMap.containsKey(category)) {
				DiscountCategory discountCategory = categoryDiscountMap.get(category);
				if (discountCategory.getValidity().after(new Date())) {
					categoryDiscount = item.getPrice() * (discountCategory.getPercent() / 100);
				}
			}
		}

		if (productCode != null) {
			if (itemDiscountMap.containsKey(productCode)) {
				DiscountItem discountItem = itemDiscountMap.get(productCode);
				if (discountItem.getValidity().after(new Date())) {
					if (discountItem.isPercent()) {
						itemDiscount = item.getPrice() * (discountItem.getVal() / 100);
					} else {
						itemDiscount = discountItem.getVal();
					}
				}
			}
		}
		return Math.max(categoryDiscount, itemDiscount);
	}

	public static double getFinalPrice(double price, Double discount, double taxPercent) {
		double val;
		if (discount == null) {
			val = price;
		} else {
			val = price - discount;
		}

		return Math.floor((val * (1 + taxPercent / 100)) * 100) / 100;
	}

	public static double getFinalPrice(double price, double taxPercent) {
		return getFinalPrice(price, null, taxPercent);
	}

	public static String format(double value) {
		DecimalFormat df = new DecimalFormat("##.###");
		return df.format(value);
	}

}
