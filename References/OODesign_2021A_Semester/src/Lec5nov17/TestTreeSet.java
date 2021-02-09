package Lec5nov17;

import java.util.*;
public class TestTreeSet
{ public static void main(String[] args)
  { //Create a hash set and then tree set
	System.out.println("Create a hash set and then tree set");
    Set<String> set = new HashSet<String>();
	TreeSet<String> treeSet1 = new TreeSet<String>(set);
	//Add strings to the set
    treeSet1.add("London");
    treeSet1.add("Paris"); 
    treeSet1.add("New York");
    treeSet1.add("San Francisco"); 
    treeSet1.add("Beijing");  
    treeSet1.add("New York");
    System.out.println("set: " + set);
    System.out.println("treeSet1: " + treeSet1);
    // Use the methods in SortedSet interface
    System.out.println("first(): " + treeSet1.first());
    System.out.println("last(): " + treeSet1.last());
    System.out.println("headSet(): " + 
      treeSet1.headSet("New York"));
    System.out.println("tailSet(): " + 
      treeSet1.tailSet("New York"));
    // Use the methods in NavigableSet interface
    System.out.println("lower(\"P\"): " + treeSet1.lower("P"));
    System.out.println("higher(\"P\"): " + treeSet1.higher("P"));
    System.out.println("floor(\"P\"): " + treeSet1.floor("P"));
    System.out.println("ceiling(\"P\"): " + treeSet1.ceiling("P"));
    System.out.println("pollFirst(): " + treeSet1.pollFirst());
    System.out.println("pollLast(): " + treeSet1.pollLast());
    System.out.println("New tree set: " + treeSet1);
  //Create tree set from HashSet
    System.out.println("Create tree set from HashSet");
    TreeSet<String> treeSet2 = 
      new TreeSet<String>(new HashSet<String>());
    treeSet2.add("London");   treeSet2.add("Paris"); 
    treeSet2.add("New York"); treeSet2.add("San Francisco"); 
    treeSet2.add("Beijing");  treeSet2.add("New York");
    System.out.println("treeSet2: " + treeSet2);
    // Use the methods in SortedSet interface
    System.out.println("first(): " + treeSet2.first());
    System.out.println("last(): " + treeSet2.last());
    System.out.println("headSet(): " + treeSet2.headSet("New York"));
    System.out.println("tailSet(): " + treeSet2.tailSet("New York"));
    // Use the methods in NavigableSet interface
    System.out.println("lower(\"P\"): " + treeSet2.lower("P"));
    System.out.println("higher(\"P\"): " + treeSet2.higher("P"));
    System.out.println("floor(\"P\"): " + treeSet2.floor("P"));
    System.out.println("ceiling(\"P\"): " + treeSet2.ceiling("P"));
    System.out.println("pollFirst(): " + treeSet2.pollFirst());
    System.out.println("pollLast(): " + treeSet2.pollLast());
    System.out.println("New tree set: " + treeSet2);
    //Create not Hashed tree set 
    TreeSet<String> treeSet3 = new TreeSet<String>();
    System.out.println("Create not Hashed tree set");
    treeSet3.add("London");   treeSet3.add("Paris"); 
    treeSet3.add("New York"); treeSet3.add("San Francisco"); 
    treeSet3.add("Beijing");  treeSet3.add("New York");
    System.out.println("treeSet3: " + treeSet3);
    // Use the methods in SortedSet interface
    System.out.println("first(): " + treeSet3.first());
    System.out.println("last(): " + treeSet3.last());
    System.out.println("headSet(): " + treeSet3.headSet("New York"));
    System.out.println("tailSet(): " + treeSet3.tailSet("New York"));
    // Use the methods in NavigableSet interface
    System.out.println("lower(\"P\"): " + treeSet3.lower("P"));
    System.out.println("higher(\"P\"): " + treeSet3.higher("P"));
    System.out.println("floor(\"P\"): " + treeSet3.floor("P"));
    System.out.println("ceiling(\"P\"): " + treeSet3.ceiling("P"));
    System.out.println("pollFirst(): " + treeSet3.pollFirst());
    System.out.println("pollLast(): " + treeSet3.pollLast());
    System.out.println("New tree set: " + treeSet3);
   }
}
