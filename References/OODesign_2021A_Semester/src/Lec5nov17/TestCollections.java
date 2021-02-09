package Lec5nov17;

import java.util.*;
public class TestCollections
{ @SuppressWarnings("unchecked") /* to avoid warnings */
  public static void main(String[] args) 
  { System.out.println("List");
	List<String> list1 = Collections.nCopies(3, "red"); //Create a list of 3 strings
    System.out.println("The initial list is " + list1);
    try
    { Collections.fill(list1, "yellow");  //Fill in "yellow" to the list
      System.out.println("After filling yellow, the list is " 
    		+ list1);
    }
    catch (Exception ex)
    { System.out.println("fill is Illegal operation "
    		+ "for List. ArrayList is needed. ");
      System.out.println(ex);
    }
    // Add three new elements to the list
    try
    { list1.add("white");
      list1.add("black");
      list1.add("orange");
      System.out.println("After adding new elements, the list is\n" 
    		+ list1);
    }
    catch (Exception ex)
    { System.out.println("add is Illegal operation "
    		+ "for List. ArrayList is needed. ");
      System.out.println(ex);
    }
    try
    { Collections.shuffle(list1);  // Shuffle the list
      System.out.println("After shuffling, the list is\n" 
    		+ list1);
    }
    catch (Exception ex)
    { System.out.println("shuffle is Illegal operation "
    		+ "for List. ArrayList is needed. ");
      System.out.println(ex);
    }
    // Find the minimum and maximum elements in the list
    try 
    { System.out.println("The minimum element in the list is " + 
    		Collections.min(list1));
      System.out.println("The maximum element in the list is " + 
    		Collections.max(list1));
    }
    catch (Exception ex)
    { System.out.println("min and max are Illegal operations "
    		+ "for List. ArrayList is needed. ");
      System.out.println(ex);
    }
    try
    { Collections.sort(list1);  // Sort the list
      System.out.println("The sorted list is\n" + list1);
    }
    catch (Exception ex)
    { System.out.println("sort is Illegal operation " + 
            "for List. ArrayList is needed. ");
      System.out.println(ex);
    }
    try
    { // Find an element in the list
      System.out.println("The search result for gray is " + 
    		Collections.binarySearch(list1, "gray"));
    }
    catch (Exception ex)
    { System.out.println("binarySrearch is Illegal operation "
            + "for List. ArrayList is needed. ");
            System.out.println(ex);
    }
    // Create a synchronized list
    List syncList1 = Collections.synchronizedList(list1);
    // Create a synchronized read-only list
    List unmodifiableList = Collections.unmodifiableList(syncList1);
    list1 = null; // Release arrayList
    syncList1 = null; // Release syncList
    try
    {  unmodifiableList.add("black");
    }
    catch (Exception ex)
    { System.out.println("add is Illegal opration "
    		+ "with unmodifiableList");
    	   System.out.println(ex);
    }
    System.out.println("\nArrayList");
    List list2 = Collections.nCopies(3, "red"); //Create a list of 3 strings
    ArrayList arrayList = new ArrayList(list2); //Create an array list
    System.out.println("The initial arrayList is " + arrayList);
    list2 = null; // Release list
    Collections.fill(arrayList, "yellow");  //Fill in "yellow" to the list
    System.out.println("After filling yellow, the list is " 
    		+ arrayList);
    // Add three new elements to the list
    arrayList.add("white");
    arrayList.add("black");
    arrayList.add("orange");
    System.out.println("After adding new elements, the list is\n" 
    		+ arrayList);
    Collections.shuffle(arrayList);  // Shuffle the list
    System.out.println("After shuffling, the list is\n" 
    		+ arrayList);
    // Find the minimum and maximum elements in the list
    System.out.println("The minimum element in the list is " + 
    		Collections.min(arrayList));
    System.out.println("The maximum element in the list is " + 
    		Collections.max(arrayList));
    Collections.sort(arrayList);  // Sort the list
    System.out.println("The sorted list is\n" + arrayList);
    // Find an element in the list
    System.out.println("The search result for gray is " + 
    		Collections.binarySearch(arrayList, "gray"));
    // Create a synchronized list
    List syncList2 = Collections.synchronizedList(arrayList);
    // Create a synchronized read-only list
    List unmodifiableList1 = Collections.unmodifiableList(syncList2);
    arrayList = null; // Release arrayList
    syncList2 = null; // Release syncList
    try
    { unmodifiableList.add("black");
    }
    catch (Exception ex)
    { System.out.println("add is Illegal opration "
    		+ "with unmodifiableList");
      System.out.println(ex);
    }
  }
}