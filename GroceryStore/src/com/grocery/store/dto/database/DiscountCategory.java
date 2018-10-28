package com.grocery.store.dto.database;

import java.util.Date;

/**
 * 
 * @author babu
 *
 */
public class DiscountCategory {

	private int categoryCode;
	private double percent;
	private Date validity;

	public DiscountCategory() {
		super();
	}

	public DiscountCategory(int category_id, double percent, Date validity) {
		this();
		this.categoryCode = category_id;
		this.percent = percent;
		this.validity = validity;
	}

	public int getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(int category_id) {
		this.categoryCode = category_id;
	}

	public double getPercent() {
		return percent;
	}

	public void setPercent(double percent) {
		this.percent = percent;
	}

	public Date getValidity() {
		return validity;
	}

	public void setValidity(Date validity) {
		this.validity = validity;
	}

}
