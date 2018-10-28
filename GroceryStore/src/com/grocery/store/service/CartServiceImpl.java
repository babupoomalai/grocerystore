package com.grocery.store.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;

import com.grocery.store.common.Constant;
import com.grocery.store.common.ItemUtil;
import com.grocery.store.common.Constant.CUSTOMER_DISCOUNT;
import com.grocery.store.dto.database.Category;
import com.grocery.store.dto.database.Item;
import com.grocery.store.dto.page.Cart;
import com.grocery.store.dto.page.CartItem;
import com.grocery.store.dto.page.Inventory;
import com.grocery.store.dto.page.Order;

/**
 * Service to add items to cart and place order
 * 
 * @author babu
 *
 */
public class CartServiceImpl implements CartService {

	private final Cart cart;

	public CartServiceImpl() {
		super();
		this.cart = new Cart();
	}

	/*
	 * Update cart if items already found ,update card else add item to the cart
	 * Provide discount
	 */
	@Override
	public boolean addToCart(int productCode, int quantity) {
		Inventory inventory = new InventoryServiceImpl().getInventory();
		HashMap<Integer, Item> itemMap = inventory.getItemMap();
		HashMap<Integer, Integer> itemQuantityMap = inventory.getItemQuantity();

		if (!itemMap.containsKey(productCode) || !itemQuantityMap.containsKey(productCode)) {
			System.out.println("Item not available");
			return false;
		}

		if (itemQuantityMap.get(productCode) < quantity) {
			System.out.println("Requested item is less in stock");
			return false;
		}

		Item item = itemMap.get(productCode);
		int categoryId = item.getCategory_id();
		Category category = inventory.getCategoryMap().get(categoryId);
		Map<Integer, CartItem> cartItemMap = cart.getItemMap();
		CartItem cartItem;
		int finalQuantity = quantity;
		double discount = ItemUtil.getDiscountValue(inventory, categoryId, productCode);

		if (cartItemMap.containsKey(productCode)) {
			cartItem = cartItemMap.get(productCode);
			finalQuantity += cartItem.getQuantity();
		} else {
			cartItem = new CartItem();
			cartItem.setItemCode(productCode);
			cartItem.setPrice(item.getPrice());
		}
		cartItem.setQuantity(finalQuantity);
		cartItem.setDiscount(discount * finalQuantity);
		double finalPrice = ItemUtil.getFinalPrice(item.getPrice(), discount, category.getTaxPercent());
		cartItem.setFinalAmount(finalQuantity * finalPrice);
		cartItemMap.put(productCode, cartItem);

		cart.setAmount(cart.getAmount() + (quantity * item.getPrice()));
		cart.setDiscountAmount(cart.getDiscountAmount() + (discount * quantity));
		cart.setFinalAmount(cart.getFinalAmount() + finalPrice * quantity);

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.grocery.store.service.CartService#checkoutCart(java.lang.String,
	 * int, boolean)
	 */
	@Override
	public boolean checkoutCart(String customerName, int age, boolean isStoreEmployee) {
		Map<Integer, CartItem> itemMap = cart.getItemMap();
		if (MapUtils.isEmpty(itemMap)) {
			System.out.println("Nothing to checkout");
			return false;
		}

		Order order = new Order(cart);
		order.setCustomerName(customerName);
		double discount = 0;

		double finalAmount = cart.getFinalAmount();

		if (age > 60) {
			discount = CUSTOMER_DISCOUNT.SENIOR_CITIZEN.getPercent() * finalAmount;
			order.setCustDiscount(CUSTOMER_DISCOUNT.SENIOR_CITIZEN);
		}
		if (isStoreEmployee) {
			discount = Math.max(discount, CUSTOMER_DISCOUNT.STORE_EMPLOYEE.getPercent()) * finalAmount;
			order.setCustDiscount(CUSTOMER_DISCOUNT.STORE_EMPLOYEE);
		}

		if (discount > 0) {
			double appliedDiscount = cart.getDiscountAmount();
			if (discount > finalAmount - appliedDiscount) {
				cart.setDiscountAmount(finalAmount);
				cart.setFinalAmount(0);
			} else {
				cart.setDiscountAmount(appliedDiscount + discount);
				cart.setFinalAmount(finalAmount - discount);
			}
		}
		order.setDate(new Date());
		order.setId(Constant.orderIdGenerator++);

		new InventoryServiceImpl().getInventory().getOrderList().add(order);

		System.out.println("\nOrder successfully generated:\n " + order);

		return true;

	}
}
