package PracticesTill18nov;



import java.io.Serializable;

public class Address implements Serializable {

	/**
	 * variables
	 */
	private static final long serialVersionUID = 4156260848966051620L; // class serialization
	private String streetAndHome;
	private String city;
	private String country;
	private int zip;

	/**
	 * Constructor
	 * 
	 * @param streetAndHome: address street and home number
	 * @param city: address city
	 * @param country: address country
	 * @param zip: address zip code
	 */
	public Address(String streetAndHome, String city, String country, int zip) {
		this.streetAndHome = streetAndHome;
		this.city = city;
		this.country = country;
		this.zip = zip;
	}

	/* getters and setters */
	/**
	 * 
	 * @return address street with home number
	 */
	public String getStreetAndHome() {
		return streetAndHome;
	}

	public void setStreetAndHome(String streetAndHome) {
		this.streetAndHome = streetAndHome;
	}

	/**
	 * 
	 * @return address city
	 */
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * 
	 * @return address country
	 */
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * 
	 * @return address zip code
	 */
	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	/**
	 * toString method
	 */
	@Override
	public String toString() {
		return streetAndHome + ", " + city + ", " + country + ", " + zip + "]";
	}

}
