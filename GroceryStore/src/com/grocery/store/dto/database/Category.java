package com.grocery.store.dto.database;

/**
 * Class of a product category
 * 
 * @author babu
 *
 */
public class Category {

	private int categoryCode;
	private String name;
	private double taxPercent;

	public Category() {
		super();
	}

	public Category(int categoryCode, String name, double taxPercent) {
		this();
		this.categoryCode = categoryCode;
		this.name = name;
		this.taxPercent = taxPercent;
	}

	public int getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(int categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getTaxPercent() {
		return taxPercent;
	}

	public void setTaxPercent(double taxPercent) {
		this.taxPercent = taxPercent;
	}

	public boolean isEmpty() {
		return categoryCode <= 0 || name == null || name.trim().isEmpty() || taxPercent < 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + categoryCode;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (categoryCode != other.categoryCode)
			return false;
		return true;
	}

}
