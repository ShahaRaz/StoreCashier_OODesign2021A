package Lec5nov17;

import java.util.*;
public class TestArrays
{ public static void main(String[] args)
  {   // Create an array of 10 int values
    int[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    System.out.print("Before filling, the array is:");
    System.out.println();
    for (int i = 0; i < 10; i++)  System.out.print(array[i] + " ");  
    System.out.println();
    // Fill array from index 6 to index 8 (exclusive) with 50
    Arrays.fill(array, 6, 8, 50);
    System.out.print("After filling, the array is:\n");
    for (int i = 0; i < 10; i++)  System.out.print(array[i] + " ");  
    System.out.println();
    // Sort the array
    Arrays.sort(array);
    System.out.print("After sorting, the array is:  ");
    for (int i = 0; i < 10; i++)   System.out.print(array[i] + " ");    
    System.out.println();
    // Search for 30 in the array
    System.out.print("Search 30 in the array : " +
      Arrays.binarySearch(array, 30));
    System.out.println();
    // Search for 3 in the array
    System.out.print("Search 3 in the array : " +
      Arrays.binarySearch(array, 3));
    System.out.println();
    // Search for -30 in the array
    System.out.print("Search -30 in the array : " +
      Arrays.binarySearch(array, -30));
    System.out.println();
    // Test if two arrays are the same
    int[] a = new int[10];
    System.out.print("Array a is:  ");
    System.out.println();
    for (int i = 0; i < 10; i++)  System.out.print(a[i] + " "); 
    System.out.println();
    System.out.print("Compare array with a : " + Arrays.equals(array, a));
  }
}