package HomeWork2;

import java.io.Serializable;
import java.util.Comparator;

public interface Lab extends Iterable<Mobile>, Serializable {

	/**
	 * Appends Mobile to the MobileList
	 * 
	 * @param m - mobile
	 */
	public void addMobile(Mobile m);

	/**
	 * 
	 * put Mobile and status to statusMap
	 * 
	 * @param mobileNumber - mobile number
	 * @param Status       - mobile status
	 * @return true if mobile exists, otherwise false
	 */
	public boolean setStatus(String mobileNumber, String status);

	/**
	 * get Mobile status by mobile number
	 * 
	 * @param mobileNumber - mobile number
	 * @return status
	 */
	public String getStatus(String mobileNumber);

	/**
	 * Returns the Mobile at the specified position in MobileList
	 * 
	 * @param index - index of the Mobile to return
	 * @return the Mobile at the specified position in MobileList
	 */
	public Mobile getMobile(int index);

	/**
	 * Removes the Mobile from MobileList,if it is present. If MobileList does not
	 * contain the Mobile, it is unchanged.
	 * 
	 * @param m - Mobile to be removed from MobileList, if present
	 * @return true if MobileList contained the specified element
	 */
	public boolean removeMobile(Mobile m);

	/**
	 * Removes the Mobile from MobileList by mobile number,if it is present. If MobileList does not
	 * contain the Mobile, it is unchanged.
	 * 
	 * @param mobileNumber - Mobile number
	 * @return true if MobileList contained the specified element
	 */
	public boolean removeMobile(String mobileNumber);

	
	/**
	 * Removes the Mobile at the specified position in MobileList.
	 * 
	 * @param index - the index of the element to be removed
	 * @return the element that was removed from MobileList
	 */
	public Mobile removeMobile(int index);

	/**
	 * Returns the number of elements in MobileList.
	 * 
	 * @return the number of elements in MobileList.
	 */
	public int size();

	/**
	 * Returns the comparator of MobileList.
	 * 
	 * @return the comparator of MobileList.
	 */
	public Comparator<Mobile> getComparator();

	/**
	 * set default comparator in MobileList for default sorting
	 * 
	 * @param c - the Comparator used to compare elements in MobileLIst
	 */
	public void setComparator(Comparator<Mobile> c);

	/**
	 * Sorts MobileList according to the order induced by Comparator.
	 */
	public void sort();

	/**
	 * Find and return Mobile in mobileList by mobile number.
	 */
	public Mobile findMobileByNumber(String mobileNumber);

	/**
	 * print repaired mobiles
	 */
	public void printRepaired();

	/**
	 * print mobiles in repair
	 */
	public void printInRepair();

	/**
	 * print all mobiles in lab
	 */
	public void printAllMobiles();

	/**
	 * print all clients in Lab
	 */
	public void printClientsSet();
}
