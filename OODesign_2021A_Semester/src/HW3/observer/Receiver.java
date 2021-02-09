package HW3.observer;

/**
 * @author Shahar Raz
 *
 **/

//Observer
public interface Receiver {
	String getSimNumber();
	void receiveMSG(Sender s, Message msg);
}
