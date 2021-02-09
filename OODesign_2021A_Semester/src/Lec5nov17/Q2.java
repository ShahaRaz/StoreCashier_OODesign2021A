package Lec5nov17;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class Q2 {
    // same as Q1, but this time insert student into the collection.
    //(print the collection with iterator only)
    // ( Duplicate students : when the name and the grade are the same )

    public static Scanner s = new Scanner(System.in);
    public Q2() {
        Set<Q2_Student> myHS= new HashSet<Q2_Student>();
        System.out.println("how many Students would you like to enter? ");
        int numCount = Integer.parseInt(s.nextLine());
        for (int i = 0; i < numCount; i++) {
            System.out.print("enter #" + (i+1) + " Student name: ");
            String name = s.nextLine();
            System.out.print("enter #" + (i+1) + " Student grade: ");
            int grade = (Integer.parseInt(s.nextLine()));
            myHS.add(new Q2_Student(grade,name));
        }

        // 2 iterator
        Iterator<Q2_Student> iterator1 = myHS.iterator();
        while (iterator1.hasNext())
            System.out.print(iterator1.next() + "  ");

        System.out.println(); // drop line
        // 3 simple syso (using the set's print method
        System.out.println(myHS);
    }


}
