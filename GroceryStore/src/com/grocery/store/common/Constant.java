package com.grocery.store.common;

public class Constant {

	public static int orderIdGenerator = 1;

	public enum CUSTOMER_DISCOUNT {
		SENIOR_CITIZEN(2.5), STORE_EMPLOYEE(5);

		private double percent;

		CUSTOMER_DISCOUNT(double discount) {
			this.percent = discount;
		}

		public double getPercent() {
			return percent;
		}

	}

	public static enum PRODUCT_SI_MASS {
		GRAM("g"), KILO_GRAM("kg");

		private String text;

		PRODUCT_SI_MASS(String text) {
			this.text = text;
		}

		public String getText() {
			return text;
		}
	}

	public static enum PRODUCT_SI_VOLUME {
		MILLILITRE("ml"), LITRE("l");

		private String text;

		PRODUCT_SI_VOLUME(String text) {
			this.text = text;
		}

		public String getText() {
			return text;
		}
	}

}
