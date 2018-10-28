package com.grocery.store.dto.database;

import java.util.Date;

/**
 * Class to represent discount of an item. if both discounted and non-discounted
 * items should present, this class can extend {@link Item}. I kept it as
 * separate class since discount can be applied at any time
 * 
 * @author babu
 *
 */
public class DiscountItem {

	private int productCode;
	private double val;
	// true if percentage discount false then flat discount
	private boolean percent;
	private Date validity;

	public DiscountItem() {
		super();
	}

	public DiscountItem(int productCode, double val, boolean isPercent, Date validity) {
		this();
		this.productCode = productCode;
		this.val = val;
		this.setPercent(isPercent);
		this.validity = validity;
	}

	public int getProductCode() {
		return productCode;
	}

	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}

	public double getVal() {
		return val;
	}

	public void setVal(double val) {
		this.val = val;
	}

	public Date getValidity() {
		return validity;
	}

	public void setValidity(Date validity) {
		this.validity = validity;
	}

	public boolean isPercent() {
		return percent;
	}

	public void setPercent(boolean percent) {
		this.percent = percent;
	}

}
