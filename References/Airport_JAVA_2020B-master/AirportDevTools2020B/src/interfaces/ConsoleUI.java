package interfaces;

import java.util.Scanner;

public class ConsoleUI implements Massageable{
	private Scanner scn = new Scanner(System.in); // open & close scn??? 
	@Override
	public void showMassage(String msg) {
		System.out.println(msg);		
	}

	@Override
	public String getString(String msg) {
		System.out.println(msg);
		return scn.nextLine();
	}
	
	public void showErrMassage(String msg) {
		System.err.println(msg);
	}

	@Override
	public String dropLineChar() {
		return "\n";
	}


}
