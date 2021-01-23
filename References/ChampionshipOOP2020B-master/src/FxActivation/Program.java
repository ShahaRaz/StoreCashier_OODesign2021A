package FxActivation;

import java.util.Scanner;

import model.Model;

public class Program {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		Model gL = new Model(scn);
		System.out.println(gL.toString()); 
		scn.close();
	}
	//check


}