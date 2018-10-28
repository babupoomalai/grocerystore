package com.grocery.store.dto.database;

import java.util.Date;

/**
 * Class to represent a product and its attributes
 */
public class Item {

	private String name;
	private int productCode;
	private int category_id;
	private Date mfgDate;
	private Date expiryDate;
	private String batch;
	private double weight;
	private String weightMetricText;
	private double price;

	public Item() {
		super();
	}

	public Item(String name, int productCode, int category_id, Date mfgDate, Date expiryDate, String batch,
			double weight, String weightMetricText, double price) {
		this();
		this.name = name;
		this.productCode = productCode;
		this.category_id = category_id;
		this.mfgDate = mfgDate;
		this.expiryDate = expiryDate;
		this.batch = batch;
		this.weight = weight;
		this.weightMetricText = weightMetricText;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getProductCode() {
		return productCode;
	}

	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public Date getMfgDate() {
		return mfgDate;
	}

	public void setMfgDate(Date mfgDate) {
		this.mfgDate = mfgDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getWeightMetricText() {
		return weightMetricText;
	}

	public void setWeightMetricText(String weightMetricText) {
		this.weightMetricText = weightMetricText;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isEmpty() {
		return name == null || name.trim().isEmpty() || batch == null || batch.trim().isEmpty() || mfgDate == null
				|| expiryDate == null || weight <= 0 || weightMetricText == null || weightMetricText.trim().isEmpty()
				|| price < 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + productCode;
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
		Item other = (Item) obj;
		if (productCode != other.productCode)
			return false;
		return true;
	}

}
