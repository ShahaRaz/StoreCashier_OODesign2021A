package main.interfaces;

/**
 * @author Gadi Engelsman.
 * @author Shahar Raz.
 */

public interface Messageable {
	void showMassage(String msg);

	String getString(String msg);

	void showErrMassage(String string);

	String dropLineChar();
}
