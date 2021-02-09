//package Hw3Reference.mainPackage;
///*
// * @student: Gadi Engelsman 311217905
// * @student: Shachar Raz 	205936800
// */
//import command.Balance;
//import command.BalanceCommand;
//import command.GetBalanceCommand;
//import command.SetBalanceCommand;
//import observer.Message;
//import observer.Receiver;
//import observer.Sender;
//
//public class Mobile implements Sender, Receiver, MobileFunc {
//
//	private String name;
//	private String simNumber;
//	private BalanceCommand balanceCommand;
//	private VCall currentVCall;
//	private Balance balance;
//
//	public Mobile(String name, String simNumber) {
//		super();
//		this.name = name;
//		this.simNumber = simNumber;
//		balance = new Balance();
//		this.balanceCommand = new BalanceCommand(new GetBalanceCommand(balance), new SetBalanceCommand(balance));
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
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
//	public BalanceCommand getBalanceCommand() {
//		return balanceCommand;
//	}
//
//	public void setBalanceCommand(BalanceCommand balanceCommand) {
//		this.balanceCommand = balanceCommand;
//	}
//
//	public VCall getCurrentVCall() {
//		return currentVCall;
//	}
//
//	public void setCurrentVCall(VCall currentVCall) {
//		this.currentVCall = currentVCall;
//	}
//
//	@Override
//	public double getSimBalance() {
//		return balanceCommand.getBalance();
//	}
//
//	@Override
//	public void setSimBalance(double simBalance) {
//		this.balanceCommand.setBalance(simBalance);
//	}
//
//	@Override
//	public void startVCall(VCall vCall) {
//		setCurrentVCall(vCall);
//		currentVCall.setSimNumber(getSimNumber());
//		currentVCall.setSimBalance(balance.get());
//
//		currentVCall.start();
//	}
//
//	@Override
//	public void stopVCall() {
//		if (currentVCall.isAlive()) {
//			currentVCall.isRunning = false;
//		}
//	}
//
//	@Override
//	public void receiveMSG(Sender s, Message msg) {
//		System.out.println("\n\n-------------" + getSimNumber() + "-------------\n" + "New Massage Has Arrived\n"
//				+ "------------------------------------\n" + "From " + s.getSimNumber() + "\nDate: " + msg.getTime()
//				+ "\nMassage: " + msg.getMsg() + "\n");
//	}
//
//	@Override
//	public void sendMSG(Receiver r, Message msg) {
//		r.receiveMSG(this, msg);
//	}
//
//}
