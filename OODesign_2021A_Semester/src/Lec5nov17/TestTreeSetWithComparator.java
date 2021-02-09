package Lec5nov17;

import java.util.*;
public class TestTreeSetWithComparator
{ public static void main(String[] args)
  { // Create a tree set for geometric objects using a comparator
	// GeometricObject class does not implement Comparable
	// so it does not implements compareTo method. The sort order is 
	// according the comparator: GeometricObjectComparator.
    Set<GeometricObject> set =
      new TreeSet<GeometricObject>(
    	new GeometricObjectComparator());
    set.add(new Rectangle(4, 5));
    set.add(new Circle(40));
    set.add(new Circle(40));
    set.add(new Rectangle(4, 1));
    // Display geometric objects in the tree set
    System.out.println("A sorted ascending set of geometric objects");
    for (GeometricObject element: set)
      System.out.println("area = " + element.getArea());
    Set<GeometricObject> set1 =
        new TreeSet<GeometricObject>(
          new GeometricObjectComparator1());
    set1.add(new Rectangle(4, 5));
    set1.add(new Circle(40));
    set1.add(new Circle(40));
    set1.add(new Rectangle(4, 1));
    // Display geometric objects in the tree set
    System.out.println("A sorted dscending set of geometric objects");
    for (GeometricObject element: set1)
      System.out.println("area = " + element.getArea());
    // Create a tree set for GeometricObjectWithComparable objects 
    // using a comparator
    // GeometricObjectWithComparable implements Comparable,
    // but because we use here the new GeometricObjectComparator1
    // the sort is according compare method in GeometricObjectComparator1
    // class, and not compareTo method in GeometricObjectWithComparable
    // class.
    Set<GeometricObjectWithComparable> set2 =
      new TreeSet<GeometricObjectWithComparable>
         (new GeometricObjectComparator2());
    set2.add(new Rectangle1(4, 5));
    set2.add(new Circle1(40));
    set2.add(new Circle1(40));
    set2.add(new Rectangle1(4, 1));
    // Display geometric objects in the tree set
    System.out.println("A sorted set of GeometricObjectWithComparable");
    for (GeometricObjectWithComparable element: set2)
      System.out.println("area = " + element.getArea());
  }
}
