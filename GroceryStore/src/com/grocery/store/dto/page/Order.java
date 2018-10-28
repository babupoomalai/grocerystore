package com.grocery.store.dto.page;

import java.util.Date;

import com.grocery.store.common.Constant.CUSTOMER_DISCOUNT;

public class Order extends Cart {

	private int id;
	private String customerName;
	private CUSTOMER_DISCOUNT custDiscount;
	private Date date;

	public Order() {
		super();
	}

	public Order(Cart cart) {
		this.setItemMap(cart.getItemMap());
		this.setAmount(cart.getAmount());
		this.setDiscountAmount(cart.getDiscountAmount());
		this.setFinalAmount(cart.getFinalAmount());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public CUSTOMER_DISCOUNT getCustDiscount() {
		return custDiscount;
	}

	public void setCustDiscount(CUSTOMER_DISCOUNT custDiscount) {
		this.custDiscount = custDiscount;
	}

	@Override
	public String toString() {
		return "Order [Id=" + getId() + ", Name=" + getCustomerName() + ", Date=" + getDate() + ", Customer Discount="
				+ getCustDiscount() + ", Amount=" + getAmount() + ", Discount Amount=" + getDiscountAmount()
				+ ", Final Amount(incl tax)=" + getFinalAmount() + ", ItemsList=" + getItemMap().values();
	}

}
