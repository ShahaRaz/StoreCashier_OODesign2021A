import java.io.Serializable;
/**
 * 
 * @author Grigory Shaulov
 *
 */
public class Bookmark implements Serializable {


	/**
	 * Variables
	 */											  
	private static final long serialVersionUID = -6492635066339437347L;
	private String title;
	private String value;

	/**
	 * 
	 * @param title:	Bookmark title
	 * @param value:	Bookmark value		 
	 */
	public Bookmark(String title, String value) {
		this.title = title;
		this.value = value;
	}

	//geters&setters
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	//end geters&setters

	@Override
	public String toString() {
		return title + " : " + value;
	}

}
