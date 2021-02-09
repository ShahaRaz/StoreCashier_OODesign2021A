package Lec5nov17;

import java.util.*;
import java.util.Map.Entry;
public class CountOccurrenceOfWords
{ public static void main(String[] args)
  {  // Set text in a string
    String text = "Good morning. Have a good class. " +
      "Have   a good visit. Have fun!";
    // Create a TreeMap to hold words as key and count as value
    TreeMap<String, Integer> map = new TreeMap<String, Integer>();
    String[] words = text.split("[ \n\t\r.,;:!?(){}]");
    for (int i = 0; i < words.length; i++)
    { if (words[i].length() > 0)
      {	String key = words[i].toLowerCase();
        if (map.get(key) == null)
        {  map.put(key, 1);
        }
        else
        { int value = map.get(key).intValue();
          value++;
          map.put(key, value);
        }
      }
    }
    System.out.println("Display entries in the Treemap map");
    System.out.println(map); // print map 
    // Get all entries into a set in order to use an iterator
    Set<Entry<String, Integer>> entryset = map.entrySet();
    // a Map can"t use an Iterator, so it transform to a Set
    // Get key and value from each entry
    for (Entry<String, Integer> entry: entryset)
      System.out.println(entry.getKey() + "\t" + entry.getValue());
  }
}
