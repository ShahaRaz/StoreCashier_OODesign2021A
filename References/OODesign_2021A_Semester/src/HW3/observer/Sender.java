package HW3.observer;

/**
 * @author Shahar Raz
 *
 **/

//Observable
public interface Sender {
	String getSimNumber();
	void sendMSG(Receiver r, Message msg);
}
