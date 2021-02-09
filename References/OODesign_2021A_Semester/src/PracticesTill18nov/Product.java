package PracticesTill18nov;



import java.io.Serializable;

public class Product implements Serializable {

	/**
	 * variables
	 */
	private static final long serialVersionUID = 1153391878182177027L; // class serialization
	private String name;
	private double price;
	private char currency;

	/**
	 * Constructor
	 * 
	 * @param name     - name of product
	 * @param price    - price of product
	 * @param currency - currency of product price
	 */
	public Product(String name, double price, char currency) {
		this.name = name;
		this.price = price;
		this.currency = currency;
	}

	/* getters and setters */
	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return price
	 */
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return currency
	 */
	public char getCurrency() {
		return currency;
	}

	public void setCurrency(char currency) {
		this.currency = currency;
	}

	/**
	 * toString() method
	 */
	@Override
	public String toString() {
		return "Product[" + name + " : " + price + currency + "]";
	}

}
