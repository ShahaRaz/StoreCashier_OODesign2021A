//package Hw3Reference.mainPackage;
//
//import observer.Message;
//
///*
// * @author 	 Grigory Shaulov
// * @student: Gadi Engelsman 311217905
// * @student: Shachar Raz 	205936800
// */
//
//public class Main {
//
//	public static void main(String[] args) {
//
//		try {
//			// Q1
//			Mobile samsungS10 = new Mobile("SamsungS10", "0501111111");
//			samsungS10.setSimBalance(1.5);
//			System.out.println("Samsung10 balance = " + samsungS10.getSimBalance());
//
//			Mobile iphoneX = new Mobile("IPhoneX", "0501777777");
//			iphoneX.setSimBalance(2);
//			System.out.println("IPhoneX balance = " + iphoneX.getSimBalance());
//
//			Mobile note9 = new Mobile("Note 9", "0501555555");
//			note9.setSimBalance(5);
//			System.out.println("Note9 balance = " + note9.getSimBalance());
//
//			// Q2
//			samsungS10.sendMSG(iphoneX, new Message("Hi!"));
//			Thread.sleep(2000);
//			iphoneX.sendMSG(samsungS10, new Message("Hi :)"));
//			Thread.sleep(2000);
//			samsungS10.sendMSG(iphoneX, new Message("How are you?"));
//			Thread.sleep(2000);
//			iphoneX.sendMSG(samsungS10, new Message("I am fine, thanks. What about you?"));
//
//			// Q3
//			samsungS10.startVCall(new VCall("0501234567", 6.0));
//			iphoneX.startVCall(new VCall("0508795645", 8.0));
//			note9.startVCall(new VCall("0508888888", 10.0));
//			Thread.sleep(5000);
//			note9.stopVCall();
//
//		} catch (InterruptedException e) {
//		}
//	}
//
//}
