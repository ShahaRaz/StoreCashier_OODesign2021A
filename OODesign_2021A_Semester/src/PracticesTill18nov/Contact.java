package PracticesTill18nov;



import java.io.Serializable;

public class Contact implements Serializable {

	/**
	 * variables
	 */
	private static final long serialVersionUID = 2018729470445057288L; // class serialization
	private String name;
	private String phone;
	private transient String info;
	private Address address;

	/**
	 * Constructor
	 * 
	 * @param name: contact name
	 * @param phone: contact phone
	 * @param info: contact transient info
	 * @param address: contact address
	 */
	public Contact(String name, String phone, String info, Address address) {
		this.name = name;
		this.phone = phone;
		this.info = info;
		this.address = address;
	}

	/* getters and setters */
	/**
	 * @return contact name
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return contact phone
	 */
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 
	 * @return contact transient info
	 */
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	/**
	 * 
	 * @return contact address
	 */
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * tostring() method
	 */
	@Override
	public String toString() {
		return "Contact[" + name + ", " + phone + ", " + address + "]";
	}

}
