package Lec5nov17;

import java.util.*;
public class TestHashSet {
  public TestHashSet() {
    // Create a hash set
    Set<String> set = new HashSet<String>();
    // Add strings to the set
    set.add("London");
    set.add("Paris");
    set.add("New York");
    set.add("San Francisco");
    set.add("Beijing");
    set.add("New York");
    System.out.println(set);
    // Obtain an iterator for the hash set
    Iterator<String> iterator1 = set.iterator();
    // Display the elements in the hash set:
    // with and without an iterator
    while (iterator1.hasNext())
      System.out.print(iterator1.next().toUpperCase() + " ");
    System.out.println();
    for (Object element: set)
      System.out.print(element.toString().toUpperCase() + " ");
    System.out.println();
    //Iterator<String> iterator2 = set.iterator();
    iterator1 = set.iterator();
    //while (iterator2.hasNext())
    //     System.out.print(iterator2.next().toLowerCase() + " ");
    while (iterator1.hasNext())
      System.out.print(iterator1.next().toLowerCase() + " ");
    System.out.println();
    for (Object element: set)
      System.out.print(element.toString().toLowerCase() + " ");

  }

}