package Lec5nov17;

import java.util.*;
public class TestTreeSetWithComparable
{ public static void main(String[] args)
  { // Create a tree set for geometric objects using a compareTo method.
    // GeometricObjectWithComparable class implements Comparable interface. 
    Set<GeometricObjectWithComparable> set =
      new TreeSet<GeometricObjectWithComparable>();
    set.add(new Rectangle1(4, 5));
    set.add(new Circle1(40));
    set.add(new Circle1(40));
    set.add(new Rectangle1(4, 1));
    // Display geometric objects in the tree set
    System.out.println("A sorted set of geometric objects");
    for (GeometricObjectWithComparable element: set)
      System.out.println("area = " + element.getArea());
  }
}
