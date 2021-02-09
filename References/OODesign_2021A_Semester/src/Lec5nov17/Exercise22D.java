package Lec5nov17;

import java.util.*;
public class Exercise22D
{ // list permits duplicates (set does not)	
  public static void main(String[] args)
  { ArrayList<String> list1 = 
    	new ArrayList<String>(Arrays.asList(
          new String[]{"George", "Jim", "John", "Blake", 
    		           "Kevin", "Michael"}));
    ArrayList<String> list1Clone1 = (ArrayList<String>)list1.clone();
    ArrayList<String> list1Clone2 = (ArrayList<String>)list1.clone();
    ArrayList<String> list2 = new ArrayList<String>(Arrays.asList(
      new String[] {"George", "Katie", "Kevin", 
    		        "Michelle", "Ryan"}));
    list1.addAll(list2); // union 
    list1Clone1.removeAll(list2); // difference
    list1Clone2.retainAll(list2); // intersection
    System.out.println("The union of the two sets is " + list1);
    System.out.println("The difference of the two sets is " + 
    		list1Clone1);
    System.out.println("The intersection of the two sets is " + 
    		list1Clone2);
  }
}