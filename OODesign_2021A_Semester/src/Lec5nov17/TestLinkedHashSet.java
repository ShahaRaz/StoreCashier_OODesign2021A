package Lec5nov17;

import java.util.*;
public class TestLinkedHashSet 
{

  public static void main(String[] args)
  { // Create a hash set
    Set<String> set = new LinkedHashSet<String>();
    // Add strings to the set
    set.add("London");
    set.add("Paris");
    set.add("London"); //only one "London" will be in the set
    set.add("Paris");  //only one "Paris" will be in the set
    set.add("New York");
    set.add("San Francisco");
    set.add("Beijing");
    set.add("New York");
    System.out.println(set);
    Iterator<String> iterator1 = set.iterator();
    // Display the elements in the hash set: 
    // with and without an iterator  
    while (iterator1.hasNext())                                 
      System.out.print(iterator1.next().toUpperCase() + " ");
    System.out.println();
    for (Object element: set)
      System.out.print(element.toString().toUpperCase() + " ");
     iterator1 = set.iterator();
    System.out.println();
    while (iterator1.hasNext())                                 
        System.out.print(iterator1.next().toLowerCase() + " ");
    System.out.println();
    for (Object element: set)
        System.out.print(element.toString().toLowerCase() + " ");
  }
} 
