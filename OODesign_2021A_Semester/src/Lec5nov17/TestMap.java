package Lec5nov17;

import java.util.*;
public class TestMap
{  public static void main(String[] args)
  { // Create a HashMap
    Map<String, Integer> hashMap = new HashMap<String, Integer>();
    hashMap.put("Smith", 31);
    hashMap.put("Smith", 30);
    hashMap.put("Anderson", 31);
    hashMap.put("Lewis", 29);
    hashMap.put("Cook", 29);
    System.out.println("Display entries in HashMap");
    System.out.println(hashMap);
    // Create a TreeMap from the previous HashMap
    Map<String, Integer> treeMap = 
    	new TreeMap<String, Integer>(hashMap);
    System.out.println("\nDisplay entries in ascending order of key");
    System.out.println(treeMap);
    // Create a LinkedHashMap
    Map<String, Integer> linkedHashMap1 =
        new LinkedHashMap<String, Integer>();
    Map<String, Integer> linkedHashMap2 =
      new LinkedHashMap<String, Integer>(16, 0.75f, true);
    // 16 - capacity, 0.75f - loading factor, true - retrieve as the 
    // hashing access, false - retrieve as the insertion order
    linkedHashMap1.put("Smith", 30); 
    linkedHashMap2.put("Smith", 30);
    linkedHashMap1.put("Anderson", 31); 
    linkedHashMap2.put("Anderson", 31);
    linkedHashMap1.put("Lewis", 29); 
    linkedHashMap2.put("Lewis", 29);
    linkedHashMap1.put("Cook", 29); 
    linkedHashMap2.put("Cook", 29);
    // Display the age for Lewis
    System.out.println("The age for " + "Lewis is " +
      linkedHashMap1.get("Lewis").intValue());
    System.out.println("\nDisplay entries in LinkedHashMap1");
    System.out.println(linkedHashMap1);
    System.out.println("\nDisplay entries in LinkedHashMap2");
    System.out.println(linkedHashMap2);
    for (int i=0; i<500; i++)  
      linkedHashMap2.get("Anderson").intValue();
    System.out.println("\nDisplay entries in LinkedHashMap2");
    System.out.println(linkedHashMap2);
  }
}
