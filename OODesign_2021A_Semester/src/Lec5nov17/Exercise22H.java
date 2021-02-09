package Lec5nov17;

import java.util.*;
import java.io.*;
public class Exercise22H
{ public static void main(String[] args)
  { if (args.length != 1)
    { System.out.println("Usage: java Exercise22H fullfilename");
      System.exit(0);
    } // arg[0] = Exercise22H.java (the whole path)
    String filename = args[0];
    // Create a tree map to hold words as key and count as value
     TreeMap<String, Integer> treeMap = 
    	 new TreeMap<String, Integer>();
     try
     { Scanner input = new Scanner(new File(filename));
       String line;
       while ((line = input.nextLine()) != null)
       { String[] words = line.split("[ \n\t\r.,;?'\")(]");
         for (int i = 0; i < words.length; i++)
         { if (words[i].trim().length() >0 )
           { String key = words[i].toLowerCase();
           if (treeMap.get(key) != null)
           { int value = ((Integer)treeMap.get(key)).intValue();
             value++;
             treeMap.put(key, new Integer(value));
           }
           else  treeMap.put(key, new Integer(1)); 
         }
       }
     }
    }
    catch (Exception ex)
    { System.err.println("End Of File " + ex);
    }
    // Get an entry set for the tree map
    Set<Map.Entry<String, Integer>> entrySet = treeMap.entrySet();
    // Display words in alphabetical order
    System.out.println("\nDisplay words and their count in " +
      " ascending order of the words");
    for (Map.Entry<String, Integer> entry: entrySet)
      System.out.println(entry.getKey() + "\t" + entry.getValue());
  }
}

