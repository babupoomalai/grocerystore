package com.grocery.store.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

import com.grocery.store.common.ItemUtil;
import com.grocery.store.dto.database.Category;
import com.grocery.store.dto.database.DiscountCategory;
import com.grocery.store.dto.database.DiscountItem;
import com.grocery.store.dto.database.Item;
import com.grocery.store.dto.page.CartItem;
import com.grocery.store.dto.page.Inventory;
import com.grocery.store.dto.page.Order;

/**
 * Service to add item, category to inventory and manage
 * 
 * @author babu
 *
 */
public class InventoryServiceImpl implements InventoryService {

	private final Inventory inventory;

	public InventoryServiceImpl() {
		super();
		this.inventory = Inventory.getInstance();
	}

	/*
	 * Add new item to inventory
	 */
	@Override
	public boolean addItem(Item product, int quantity) {
		// Check for error cases
		if (product == null) {
			return false;
		}

		if (product.isEmpty()) {
			return false;
		}

		if (!inventory.getCategoryMap().keySet().contains(product.getCategory_id())) {
			System.out.println("Category does not exist");
			return false;
		}
		if (inventory.getItemMap().keySet().contains(product.getProductCode())) {
			System.out.println("Item with same product code already exists");
			return false;
		}

		HashMap<Integer, Item> productMap = inventory.getItemMap();
		int productCode = product.getProductCode();
		if (productMap.keySet().contains(productCode)) {
			return false;
		}

		// Add product and quantity to inventory
		productMap.put(productCode, product);
		inventory.getItemQuantity().put(productCode, quantity);
		return true;
	}

	/*
	 * Register new category
	 */
	@Override
	public boolean addCategory(Category category) {
		// Pre check
		assert category != null;

		if (category.isEmpty()) {
			return false;
		}

		int categoryCode = category.getCategoryCode();
		HashMap<Integer, Category> categoryMap = inventory.getCategoryMap();
		if (categoryMap.containsKey(categoryCode)) {
			return false;
		}

		// add category into inventory
		categoryMap.put(categoryCode, category);

		return true;
	}

	/*
	 * Register category discount
	 */
	@Override
	public boolean addDiscountCategory(DiscountCategory discountCategory) {
		assert discountCategory != null;

		HashMap<Integer, DiscountCategory> categoryDiscountMap = inventory.getCategoryDiscountMap();
		int categoryId = discountCategory.getCategoryCode();
		if (categoryDiscountMap.containsKey(categoryId)) {
			DiscountCategory discount = categoryDiscountMap.get(categoryId);
			System.out.println("Discount already added for this category: Percent: " + discount.getPercent()
					+ "%, Validity: " + discount.getValidity());
			return false;
		}

		categoryDiscountMap.put(categoryId, discountCategory);
		return true;
	}

	/*
	 * Register item discount
	 */
	@Override
	public boolean addDiscountItem(DiscountItem discountItem) {
		assert discountItem != null;

		HashMap<Integer, DiscountItem> itemDiscountMap = inventory.getItemDiscountMap();
		int productCode = discountItem.getProductCode();
		if (itemDiscountMap.containsKey(productCode)) {
			DiscountItem discount = itemDiscountMap.get(productCode);
			System.out.println("Discount already added for this item: "
					+ (discount.isPercent() ? "Percent: " + discount.getVal() + "%" : discount.getVal())
					+ ", Validity: " + discount.getValidity());
			return false;

		}

		itemDiscountMap.put(productCode, discountItem);
		return true;
	}

	/*
	 * Print items available in inventory
	 */
	@Override
	public void printItems() {
		HashMap<Integer, Item> productMap = inventory.getItemMap();
		if (MapUtils.isEmpty(productMap)) {
			System.out.println("No item available");
			return;
		}

		for (Integer productCode : productMap.keySet()) {
			Item item = productMap.get(productCode);
			int category = item.getCategory_id();
			Integer quantity = inventory.getItemQuantity().get(productCode);
			if (quantity > 0) {
				double discount = ItemUtil.getDiscountValue(inventory, category, productCode);

				HashMap<Integer, Category> categoryMap = inventory.getCategoryMap();
				double taxPercent = categoryMap.get(category).getTaxPercent();
				ItemUtil.displayItem(item, discount, taxPercent);
			}
		}
	}

	/*
	 * Print items available for specific category in inventory
	 */
	@Override
	public void printItems(int category) {
		HashMap<Integer, Category> categoryMap = inventory.getCategoryMap();
		if (MapUtils.isEmpty(categoryMap)) {
			System.out.println("Category list not available");
			return;
		}
		if (!categoryMap.containsKey(category)) {
			System.out.println("Category not available");
			return;
		}

		HashMap<Integer, Item> itemtMap = inventory.getItemMap();
		List<Item> filteredList = itemtMap.values().stream().filter(item -> category == item.getCategory_id())
				.collect(Collectors.toList());
		if (CollectionUtils.isEmpty(filteredList)) {
			System.out.println("No item available");
			return;
		}

		for (Item item : filteredList) {
			int productCode = item.getProductCode();
			Integer quantity = inventory.getItemQuantity().get(productCode);
			if (quantity > 0) {
				double discount = ItemUtil.getDiscountValue(inventory, category, productCode);

				double taxPercent = categoryMap.get(category).getTaxPercent();
				ItemUtil.displayItem(item, discount, taxPercent);
			}
		}
	}

	/*
	 * Print sales happened in the day
	 */
	@Override
	public void printSales() {
		List<Order> orderList = inventory.getOrderList();
		if (CollectionUtils.isEmpty(orderList)) {
			System.out.println("No sales :(");
			return;
		}

		double totalSales = 0;
		double totalDiscount = 0;
		Map<Integer, Integer> orderedQuantityMap = new HashMap<>();

		for (Order order : orderList) {
			Map<Integer, CartItem> orderedItemMap = order.getItemMap();
			if (MapUtils.isNotEmpty(orderedItemMap)) {
				double finalAmount = order.getFinalAmount();
				double discount = order.getDiscountAmount();
				System.out.println(order.getCustomerName() + " ordered " + orderedItemMap.size() + " items worth "
						+ finalAmount + (discount > 0 ? " with discount applied: " + discount : ""));

				totalSales += finalAmount;
				totalDiscount += discount;
				for (Integer productCode : orderedItemMap.keySet()) {
					CartItem cartItem = orderedItemMap.get(productCode);
					int itemCode = cartItem.getItemCode();
					int quantity = 0;
					if (orderedQuantityMap.containsKey(itemCode)) {
						quantity = cartItem.getQuantity();
					}
					orderedQuantityMap.put(itemCode, cartItem.getQuantity() + quantity);
				}
			}
		}

		System.out.println("\nItems wise sales");
		for (Integer itemCode : orderedQuantityMap.keySet()) {
			Integer quantity = orderedQuantityMap.get(itemCode);
			Item item = inventory.getItemMap().get(itemCode);
			System.out.println(itemCode + " - " + item.getName() + " - " + quantity);
		}

		if (totalDiscount > 0) {
			System.out.println("Total Discount applied: " + totalDiscount);
		}
		System.out.println("Total Sales: " + totalSales);
	}

	public Inventory getInventory() {
		return inventory;
	}

}
