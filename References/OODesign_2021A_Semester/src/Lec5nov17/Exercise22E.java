package Lec5nov17;

import java.util.*;
import java.io.*;
public class Exercise22E
{ public static void main(String[] args)
  { if (args.length != 1)
    { System.out.println("Usage: java Exercise22E fullfilename");
      System.exit(0);
    } // arg[0]=Exercise22E.java (the whole path).
    String filename = args[0];
    // Create a list to hold the words
    ArrayList<String> list = new ArrayList<String>();
    try
    { Scanner in = new Scanner(new File(filename));
      String line;
      while (in.hasNext())
      { line = in.nextLine();
        String[] words = line.split("[ \n\t\r.,:?)({}\\[\\]]");
        for (String word: words)
        { if (word.trim().length() > 0)
            list.add(word.trim());
        }
      }
    }
    catch (Exception ex)
    { System.err.println(ex);
    }
    // Get an iterator for the list
    Collections.sort(list);
    // Display mappings
    System.out.println("\nDisplay words in ascending order ");
    for (String word: list)
    { System.out.println(word);
    }
  }
}
