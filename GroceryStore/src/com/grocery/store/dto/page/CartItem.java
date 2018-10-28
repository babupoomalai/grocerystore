package com.grocery.store.dto.page;

/**
 * Class to represent item added in the cart
 * 
 * @author babu
 *
 */
public class CartItem {

	private int itemCode;
	private int quantity;
	private double price;
	private double discount;
	private double finalAmount;

	public int getItemCode() {
		return itemCode;
	}

	public void setItemCode(int itemCode) {
		this.itemCode = itemCode;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double amount) {
		this.price = amount;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getFinalAmount() {
		return finalAmount;
	}

	public void setFinalAmount(double finalAmount) {
		this.finalAmount = finalAmount;
	}

	@Override
	public String toString() {
		return "CartItem [itemCode=" + itemCode + ", quantity=" + quantity + ", price=" + price + ", discount="
				+ discount + ", finalAmount(incl tax)=" + finalAmount + "]";
	}

}
