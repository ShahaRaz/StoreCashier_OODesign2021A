package Lec5nov17;

import java.util.*;
public class TestArrayAndLinkedList
{ public static void main(String[] args)
  { List<Integer> arrayList = new ArrayList<Integer>();
    // only integers
    arrayList.add(1); // 1 is auto boxed to new Integer(1)
    arrayList.add(2);
    arrayList.add(3);
    arrayList.add(1);
    arrayList.add(4);
    arrayList.add(0, 10);
    arrayList.add(3, 30);
    System.out.println("A list of integers in the array list:");
    System.out.println(arrayList);
    List<Object> arrayList1 = new ArrayList<Object>();
    // every object
    arrayList1.add(1); // 1 is auto boxed to new Integer(1)
    arrayList1.add(2);
    arrayList1.add(3);
    arrayList1.add(1);
    arrayList1.add(4);
    arrayList1.add(0, 10);
    arrayList1.add(0, "\"10\"");
    arrayList1.add(3, 30);
    System.out.println("A list of objects in the array list:");
    System.out.println(arrayList1);
    LinkedList<Object> linkedList=new LinkedList<Object>(arrayList);
    // every object 
    linkedList.add(1, "red"); // [10, red, 1, 2, 30, 3, 1, 4]
    linkedList.removeLast();  // [10, red, 1, 2, 30, 3, 1]
    linkedList.addFirst("green"); // [green, 10, red, 1, 2, 30, 3, 1]
    System.out.println("Display the linked list forward:");
    ListIterator<Object> listIterator = linkedList.listIterator(0);
    // same as: (0 is default index)
    //ListIterator<Object> listIterator = linkedList.listIterator();
    while (listIterator.hasNext())
    { System.out.print(listIterator.next() + " ");
    }
    System.out.println();
    System.out.println("Display the linked list backward:");
    listIterator = linkedList.listIterator(linkedList.size());
    while (listIterator.hasPrevious())
    { System.out.print(listIterator.previous() + " ");
    }
  }
}
