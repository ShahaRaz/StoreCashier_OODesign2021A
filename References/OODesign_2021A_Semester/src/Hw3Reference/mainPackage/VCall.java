//package Hw3Reference.mainPackage;
//
///*
// * @author 	 Grigory Shaulov
// * @student: Gadi Engelsman 311217905
// * @student: Shachar Raz 	205936800
// */
//
//import java.time.LocalTime;
//import java.time.format.DateTimeFormatter;
//import command.Balance;
//import command.BalanceCommand;
//import command.GetBalanceCommand;
//import command.SetBalanceCommand;
//
//public class VCall extends Thread {
//
//	private String simNumber;
//	private String callNumber;
//	private BalanceCommand balanceCommand;
//	private double pricePerMin;
//	private Balance balance;
//	public Boolean isRunning = true;
//
//	public VCall(String callNumber, double pricePerMin) {
//		this.callNumber = callNumber;
//		this.pricePerMin = pricePerMin;
//		balance = new Balance();
//		this.balanceCommand = new BalanceCommand(new GetBalanceCommand(balance), new SetBalanceCommand(balance));
//	}
//
//	public String getSimNumber() {
//		return simNumber;
//	}
//
//	public void setSimNumber(String simNumber) {
//		this.simNumber = simNumber;
//	}
//
//	public String getCallNumber() {
//		return callNumber;
//	}
//
//	public void setCallNumber(String callNumber) {
//		this.callNumber = callNumber;
//	}
//
//	public double getSimBalance() {
//		return balanceCommand.getBalance();
//	}
//
//	public void setSimBalance(double simBalance) {
//		this.balanceCommand.setBalance(simBalance);
//	}
//
//	public double getPricePerMin() {
//		return pricePerMin;
//	}
//
//	public void setPricePerMin(double pricePerMin) {
//		this.pricePerMin = pricePerMin;
//	}
//
//	@Override
//	public void run() {
//		System.out.printf("Voice Call Started [From: %s To: %s Balance: %.2f]\n", getSimNumber(), callNumber,
//				getSimBalance());
//		LocalTime duration = LocalTime.of(0, 0, 0);
//		// add code here //
//		DateTimeFormatter formatter = DateTimeFormatter.ISO_TIME;
//		LocalTime start = LocalTime.now();
//		double actualPrice = getPricePerMin() / 60;
//		while (getSimBalance() > actualPrice && isRunning) {
//			try {
//				sleep(1000);
//				setSimBalance(getSimBalance() - actualPrice);
//				if (getSimBalance() < 0)
//					setSimBalance(0);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//		LocalTime finish = LocalTime.now();
//		duration = LocalTime.of(Math.abs(finish.getHour() - start.getHour()),
//				Math.abs(finish.getMinute() - start.getMinute()), Math.abs(finish.getSecond() - start.getSecond()));
//
//		System.out.printf(
//				"\nVoice Call " + (getSimBalance() < actualPrice ? "Finished (No Enough Balance) " : "Stopped ")
//						+ "[From: %s To: %s Balance: %.2f Duration: %s]\n",
//				getSimNumber(), callNumber, getSimBalance(), duration.format(formatter));
//	}
//
//	public void setBalanceCommand(BalanceCommand balanceCommand) {
//		this.balanceCommand = balanceCommand;
//	}
//}
