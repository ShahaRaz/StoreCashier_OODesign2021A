package Lec5nov17;

import java.util.*;

import javax.swing.JOptionPane;
public class TreemapIterator
{  public static void main(String[] args)
 {  TreeMap<Integer, Integer> treeMap = 
    	new TreeMap<Integer, Integer>();
    while (true)
    { String numberString = JOptionPane.showInputDialog(null,
        "Enter an integer:", "Numbers Input",
        JOptionPane.QUESTION_MESSAGE);
      int number = Integer.parseInt(numberString);
      if (number == 0) break;
      Integer key = new Integer(number);
      if (treeMap.get(key) != null)
      { int value = ((Integer)treeMap.get(key)).intValue();
        value++;
        treeMap.put(key, new Integer(value));
      }
      else
      { treeMap.put(key, new Integer(1));
      }
    }
    Integer max = (Integer)(Collections.max(treeMap.values()));
    Set<Integer> keys = treeMap.keySet();
    Iterator<Integer> iterator = keys.iterator();
    while (iterator.hasNext())
    { Object key = iterator.next();
      Integer value = (Integer)(treeMap.get(key));
      if (value.equals(max))
      { System.out.println("Number " + key + " occurred most: "
        		+ max + " times.");
      }
    }
  }
}

