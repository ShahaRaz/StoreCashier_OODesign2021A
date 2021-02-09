package HW3;

/**
 * @author Shahar Raz
 *
 **/
import java.time.LocalDateTime;

import HW3.command.Balance;
import HW3.command.BalanceCommand;
import HW3.observer.Message;
import HW3.observer.Receiver;
import HW3.observer.Sender;


public class Mobile implements Sender, Receiver, MobileFunc {
	private String name;
	private String simNumber;
	private BalanceCommand balanceCommand;
	private VCall currentVCall;

	public Mobile(String name, String simNumber, BalanceCommand balanceCommand, VCall currentVCall) {
		this.name = name;
		this.simNumber = simNumber;
		this.balanceCommand = balanceCommand;
		this.currentVCall = currentVCall;
	}

	public Mobile(String name, String simNumber) {
		this.name = name;
		this.simNumber = simNumber;
		this.balanceCommand = new BalanceCommand(0);
		this.currentVCall=null; // we don't have one.
	}

	@Override
	public void receiveMSG(Sender s, Message msg) {
		System.out.println("\n\n-------------" +this.getSimNumber() + "-------------");
		System.out.println("New Message Has Arrived");
		System.out.println("------------------------------------  ");
		System.out.println("from:" + s.getSimNumber() + "\nDate: " + msg.getTime() + "\nMessage: " + msg.getMsg());
		System.out.println("------------------------------------  ");
	}

	@Override
	public String getSimNumber() {
		return this.simNumber;
	}

	@Override
	public void sendMSG(Receiver r, Message msg) {
		r.receiveMSG(this ,msg);
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public double getSimBalance() {
		return this.balanceCommand.getBalance();
	}

	@Override
	public void setSimBalance(double simBalance) {
		this.balanceCommand.setBalance(simBalance);
	}

	@Override
	public VCall getCurrentVCall() {
		return null;
	}

	@Override
	public void startVCall(VCall vCall) {
		vCall.setBalanceCommand(this.balanceCommand);
		vCall.setSimNumber(this.simNumber);
		vCall.setSimBalance(this.balanceCommand.getBalance());
		this.currentVCall = vCall;
		vCall.start();
	}

	@Override
	public void stopVCall() {
		this.currentVCall.interrupt();

	}
}
