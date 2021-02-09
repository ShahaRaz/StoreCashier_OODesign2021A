package Lec5nov17;

import java.util.*;
import java.io.*;
public class Exercise22B
{ public static void main(String[] args)
  { if (args.length != 1)
    { System.out.println(
        "Usage: java Exercise22B fullfilename");
      System.exit(0);
    } // arg[0] = Exercise22B.java (the whole path)
    String filename = args[0];
    // Create a tree set to hold the words
    Set<String> treeSet = new TreeSet<String>();
    try
    { Scanner in = new Scanner(new File(filename));
      String line;
      while ((line = in.nextLine()) != null)
      { String[] tokens = line.split("[ |\n|\t|\r|.|,|)|(|-|\"]");
    	//String[] tokens = line.split("[ \"\n\t\r.,)(-]");
        for (int i = 0; i < tokens.length; i++)
          treeSet.add(tokens[i]);
      }
    } // end of file message exception 
    catch (Exception ex)
    { System.err.println(ex);
    }
    // Get an iterator for the set
    Iterator iterator = treeSet.iterator();
    // Display mappings
    System.out.println("\nDisplay words in ascending order ");
    while (iterator.hasNext())
    { System.out.println(iterator.next());
    }
  }
}