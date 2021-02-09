package Lec5nov17;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class Q1 {

    //Ask the use how many integers, and get the integers.
    //insert them into a collection, fastest as possible, and without duplicates
    // finally, print the collection content in 3 different ways (short for, iterator, normal syso)

    public static Scanner s = new Scanner(System.in);
    public Q1() {
        System.out.println("how many integers would you like to enter? ");
        HashSet<Integer> myHS= new HashSet<Integer>();
        int numCount = Integer.parseInt(s.nextLine());
        for (int i = 0; i < numCount; i++) {
            System.out.print("enter #" + (i+1) + ": ");
            myHS.add(Integer.parseInt(s.nextLine()));
        }
        // 1 - for each
        for (Integer num: myHS) {
            System.out.print(num + "  ");
        }
        System.out.println(); // drop line
        // 2 iterator
        Iterator<Integer> iterator1 = myHS.iterator();
        while (iterator1.hasNext())
            System.out.print(iterator1.next() + "  ");

        System.out.println(); // drop line
        // 3 simple syso (using the set's print method
        System.out.println(myHS);
    }

}
