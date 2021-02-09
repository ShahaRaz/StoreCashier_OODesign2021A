package Lec5nov17;

import java.util.*;
import java.util.Map.Entry;
public class Exercise22F
{ @SuppressWarnings("unchecked")
public static void main(String[] args)
  { // Text in a string
    String text = "Have a good day. Have a good class. " +
      "Have a good visit. Have fun!";
    // Create a hash map to hold words and key and count as value
    HashMap<String, Integer> hashMap = 
    	new HashMap<String, Integer>();
    StringTokenizer st = new StringTokenizer(text, " .!?");
    while (st.hasMoreTokens())
    { String key = st.nextToken();
      if (hashMap.get(key) != null)
      { Integer value = ((Integer)hashMap.get(key)).intValue();
        value++;
        hashMap.put(key, new Integer(value));
      }
      else
      { hashMap.put(key, new Integer(1));
      }
    }
    System.out.println("hashMap : " + hashMap);
    System.out.println();
    // Create a tree map from the hash map
    TreeMap<String, Integer> treeMap =
      new TreeMap<String, Integer>(hashMap);
    System.out.println("treeMap : " + treeMap);
    System.out.println();
    // Get an entry set for the tree map
    Set<Entry<String, Integer>> entrySet = treeMap.entrySet();
    System.out.println("entrySet : " + entrySet);
    System.out.println();
    // Get an iterator for the entry set
    Iterator<Entry<String, Integer>> iterator = entrySet.iterator();
    ArrayList<WordOccurrence> list = new ArrayList<WordOccurrence>();
    while (iterator.hasNext())
    { StringTokenizer st1 =
        new StringTokenizer(iterator.next().toString(), "=");
      list.add(new WordOccurrence(st1.nextToken(),
        Integer.parseInt(st1.nextToken())));
    }
    System.out.println("list befor sort: " + list);
    System.out.println();
    Collections.sort(list); //sort according WordOccurrence.compareTo
    System.out.println("list after sort: " + list);
    System.out.println();
    // printing the sorted list - each element in new line 
    //for (int i = 0; i < list.size(); i++) {
    //  System.out.println(list.get(i));    }
  }
}
class WordOccurrence implements Comparable<Object>
{ String word;
  int count;
  public WordOccurrence(String word, int count)
  { this.word = word;
    this.count = count;
  }
  @Override
  public int compareTo(Object o)
  { return count - ((WordOccurrence)o).count;
  }
  @Override
  public boolean equals(Object o)
  { return word.equals(((WordOccurrence)o).word);
  }
  @Override
  public String toString()
  { return word + " " + count;
  }
}
