package HW3;
/**
 * @author Shahar Raz
 *
 **/
import HW3.command.BalanceCommand;

public class VCall extends Thread {

	private String simNumber;
	private String callNumber;
	private BalanceCommand balanceCommand;
	private double pricePerMin;
	private int[] time;

	public VCall(String callNumber, double pricePerMin) {
		setCallNumber(callNumber);
		setPricePerMin(pricePerMin);
		time = new int[3];
	}

	public String getSimNumber() {
		return simNumber;
	}

	public void setSimNumber(String simNumber) {
		this.simNumber = simNumber;
	}

	public String getCallNumber() {
		return callNumber;
	}

	public void setCallNumber(String callNumber) {
		this.callNumber = callNumber;
	}

	public double getSimBalance() {
		return balanceCommand.getBalanceCommand();
	}

	public void setSimBalance(double simBalance) {
		this.balanceCommand.setBalanceCommand(simBalance);
	}

	public double getPricePerMin() {
		return pricePerMin;
	}

	public void setPricePerMin(double pricePerMin) {
		this.pricePerMin = pricePerMin;
	}

	@Override
	public void run() {
		try {
			System.out.printf("Voice Call Started [From: %s To: %s Balance: %.2f]\n", getSimNumber(), getCallNumber(),
					getSimBalance());
			double pricePerSecond = getPricePerMin()/60;
			resetTimer();
			while (this.getSimBalance() > pricePerSecond){
				this.setSimBalance(getSimBalance() - pricePerSecond);
				this.sleep(1000); // sleep
				nextSec(); // increment timer
			}
			endCall();
			return;
		} catch (InterruptedException e) {
			System.out.printf("Voice Call Stopped [From: %s To: %s Balance: %.2f, Duration: ", getSimNumber(), callNumber,
					getSimBalance());
			System.out.println(printTime() + " ]");

		}
	}




	private void endCall() {
		System.out.printf("Voice Call Stopped (No Enough Balance)  [From: %s To: %s Balance: %.2f, Duration: ", getSimNumber(), callNumber,
				getSimBalance());
		System.out.println(printTime() + " ]");
	}

	public void setBalanceCommand(BalanceCommand balanceCommand) {
		this.balanceCommand = balanceCommand;
		
	}

	public void resetTimer() {
		time = new int[3];
		time[0] = 0;//LocalTime.now().getHour();
		time[1] = 0;//LocalTime.now().getMinute();
		time[2] = 0;//LocalTime.now().getSecond();

	}

	private void nextSec() {
		time[2]++;

		time[1] += time[2] / 60;
		time[2] = time[2] % 60;

		time[0] += time[1] / 60;
		time[1] = time[1] % 60;

		time[0] = time[0] % 24;

	}
	private String printTime(){
		return String.format("%02d : %02d : %02d", time[0],time[1],time[2]);
	}

}
