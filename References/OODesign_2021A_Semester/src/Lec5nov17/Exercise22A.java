package Lec5nov17;

import java.util.*;
public class Exercise22A
{ public static void main(String[] args)
  { HashSet<String> set1 = new HashSet<String>(Arrays.asList(
      new String[]{"George", "Jim", "John", "Blake", "Kevin", 
    		  "Michael"}));
    HashSet<String> set1Clone1 = (HashSet<String>)set1.clone();
    HashSet<String> set1Clone2 = (HashSet<String>)set1.clone();
    HashSet<String> set2 = new HashSet<String>(Arrays.asList(
      new String[] {"George", "Katie", "Kevin", "Michelle", "Ryan"}));
    set1.addAll(set2); // set1 is the union of set1 and set2 
    set1Clone1.removeAll(set2); // remain only elements is set1 
                                // which are not in set2
    set1Clone2.retainAll(set2); // removes from set1 all elements 
                                // that are not also  in set2.
    System.out.println("The union of the two sets is " + set1);
    System.out.println("The difference of the two sets is " + 
    		set1Clone1);
    System.out.println("The intersection of the two sets is " + 
    		set1Clone2);
  }
}