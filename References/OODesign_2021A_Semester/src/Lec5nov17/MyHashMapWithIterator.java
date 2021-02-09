package Lec5nov17;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Set;
import java.util.NoSuchElementException;
public class MyHashMapWithIterator
{ public static void main(String[] args) 
		throws FileNotFoundException
  { ProductAA product;
    MyMap1A.Entry<Integer, ProductAA> e;
	File resultsFile1 = new File("results2.txt");
    PrintWriter printResultsFile1 = 
    		new PrintWriter(resultsFile1); 
	ProductAA[] ap = createArrayProductsA();
	quickSort(ap, new Product1Product1ComparatorAA());
	printResultsFile1.println(
	  "Elements Sorted By Product1Product1ComparatorAA"); 
	for (ProductAA p: ap)
	  printResultsFile1.println(p);
	printResultsFile1.println("Number of elements: " + ap.length);
    MyMap1A<Integer, ProductAA> MyLinkedHashMapA = 
      new MyLinkedHashMapA<Integer, ProductAA>();
    for (ProductAA p: ap)
      MyLinkedHashMapA.put(p.getId(), p);
    printResultsFile1.println("Elements in LinkedHashMap\n" 
    	+ MyLinkedHashMapA);
    printResultsFile1.println("Number of elements in LinkedHashMap: " 
        + MyLinkedHashMapA.size());
    ListIterator<MyMap1A.Entry<Integer, ProductAA>> iterator = 
    	(ListIterator<MyMap1A.Entry<Integer, ProductAA>>) 
    	   MyLinkedHashMapA.listIterator(0);
    printResultsFile1.println(
      "Elements in LinkedHashMap in the order "
        + " (Product1Comparator1) they enter the list (By Iterator)"); 
    while (iterator.hasNext())
    { printResultsFile1.println(iterator.next());
    }
    printResultsFile1.println(
      "Number of elements in LinkedHashMap: " 
        + MyLinkedHashMapA.size());
    iterator = (ListIterator<MyMap1A.Entry<Integer, ProductAA>>) 
      MyLinkedHashMapA.listIterator(MyLinkedHashMapA.size());
    printResultsFile1.println(
      "Elements in LinkedHashMap in BackWard order "
        + " (Product1Comparator1) they enter the list (By Iterator)"); 
    while (iterator.hasPrevious())
    { printResultsFile1.println(iterator.previous());
    }
    product = new ProductAA(10, "Violin", 1000);
    printResultsFile1.println(
      "Is \"10 Violin 1000\" in LinkedHashMap? "
      + MyLinkedHashMapA.containsValue(product));
    product = new ProductAA(10, "Violin", 1200);
    printResultsFile1.println(
      "Is \"10 Violin 1200\" in LinkedHashMap? "
      + MyLinkedHashMapA.containsValue(product));
    if (MyLinkedHashMapA.size() > 2)
    { iterator = (ListIterator<MyMap1A.Entry<Integer, ProductAA>>) 
    	MyLinkedHashMapA.listIterator(2);
      product = new ProductAA(850, "Drums", 7500);
      e = new MyMap1A.Entry<Integer, ProductAA> 
         (product.getId(), product);
      iterator.set(e);
      printResultsFile1.println(
    	"Elements in LinkedHashMap After Change by Iterator\n" 
    	+ MyLinkedHashMapA);
      printResultsFile1.println(
    	"Number of elements in LinkedHashMap: " 
        + MyLinkedHashMapA.size());
      iterator = (ListIterator<MyMap1A.Entry<Integer, ProductAA>>) 
    	MyLinkedHashMapA.listIterator(0);
      printResultsFile1.println("Print LinkedHashMap (By Iterator)");
      while (iterator.hasNext())
      {  printResultsFile1.println(iterator.next());
      }
      printResultsFile1.println(
    	"Number of elements in LinkedHashMap: " 
          + MyLinkedHashMapA.size());
    }
    product = new ProductAA(22, "Piano", 7000);
    MyLinkedHashMapA.remove(product.getId());
    product = new ProductAA(13, "ContraBass", 300);
    MyLinkedHashMapA.remove(product.getId());
    printResultsFile1.println(
      "Elemebts in LinkedHashMap after remove " 
        + "\"22 Piano 7000\"" + " and " + "\"13 ContraBass 300\"" 
           +"\n" + MyLinkedHashMapA);
    printResultsFile1.println(
      "Number of elements in LinkedHashMap: " 
        + MyLinkedHashMapA.size());
    iterator = (ListIterator<MyMap1A.Entry<Integer, ProductAA>>) 
      MyLinkedHashMapA.listIterator(0);
    printResultsFile1.println("Print LinkedHashMap (By Iterator)");
    while (iterator.hasNext())
    {  printResultsFile1.println(iterator.next());
    }
    printResultsFile1.println("Number of elements in LinkedHashMap: " 
      + MyLinkedHashMapA.size());
    MyLinkedHashMapA.clear();
    printResultsFile1.println(
      "Elements in LinkedHashMap after clearing: " 
      + MyLinkedHashMapA);
    printResultsFile1.println(
      "Number of elements in LinkedHashMap after clearing: " 
      + MyLinkedHashMapA.size());
    iterator = (ListIterator<MyMap1A.Entry<Integer, ProductAA>>) 
      MyLinkedHashMapA.listIterator(0);
    printResultsFile1.println("Print LinkedHashMap (By Iterator)");
    while (iterator.hasNext())
    {  printResultsFile1.println(iterator.next());
    }
    printResultsFile1.println("Number of elements in LinkedHashMap: " 
      + MyLinkedHashMapA.size());
    quickSort(ap, new Product1ComparatorBB());
    iterator = (ListIterator<MyMap1A.Entry<Integer, ProductAA>>) 
      MyLinkedHashMapA.listIterator(0);
    for (ProductAA p: ap)
    { e = new MyMap1A.Entry<Integer, ProductAA> (p.getId(), p);
      iterator.add(e);	
    }
    printResultsFile1.println(
      "Elements Sorted By Product1ComparatorBB"); 
	for (ProductAA p: ap)
	  printResultsFile1.println(p);
	printResultsFile1.println("Number of elements: " + ap.length);
    printResultsFile1.println(
      "Elements in LinkedHashMap Added by Iterator\n" 
	    + MyLinkedHashMapA);	
    printResultsFile1.println(
      "Number of elements in LinkedHashMap: " 
        + MyLinkedHashMapA.size());
    iterator = (ListIterator<MyMap1A.Entry<Integer, ProductAA>>) 
      MyLinkedHashMapA.listIterator(0);
    printResultsFile1.println(
      "Print LinkedHashMap (By Iterator)");
    while (iterator.hasNext())
    { printResultsFile1.println(iterator.next());
    }
    printResultsFile1.println(
      "Number of elements in LinkedHashMap: " 
        + MyLinkedHashMapA.size());
    iterator = (ListIterator<MyMap1A.Entry<Integer, ProductAA>>) 
      MyLinkedHashMapA.listIterator(MyLinkedHashMapA.size());
    try
    { while (iterator.hasPrevious())
      { iterator.remove();
      }
    }
    catch (NoSuchElementException ex)
    {  printResultsFile1.println(
         "NoSuchElementException: iterator try"
           + " to remove object with illegal index ");  
    }
    iterator = (ListIterator<MyMap1A.Entry<Integer, ProductAA>>) 
      MyLinkedHashMapA.listIterator(MyLinkedHashMapA.size());
    while (iterator.hasPrevious())
    { iterator.previous();  	
      iterator.remove();
    }  
    printResultsFile1.println(
      "Elements in LinkedHashMap after Delete By Iterator\n" 
        + MyLinkedHashMapA);	
    printResultsFile1.println(
      "Number of elements in LinkedHashMap: " 
        + MyLinkedHashMapA.size());
    iterator = (ListIterator<MyMap1A.Entry<Integer, ProductAA>>) 
      MyLinkedHashMapA.listIterator(0);
    printResultsFile1.println("Print LinkedHashMap (By Iterator)");
    while (iterator.hasNext())
    {  printResultsFile1.println(iterator.next());
    }
    printResultsFile1.println("Number of elements in LinkedHashMap: " 
      + MyLinkedHashMapA.size());
    product = new ProductAA(10, "Violin", 1000);
    MyLinkedHashMapA.put(product.getId(), product);
    printResultsFile1.println(
      "Elements in LinkedHashMap After add One Element\n" 
        + MyLinkedHashMapA);
    printResultsFile1.println("Number of elements in LinkedHashMap: " 
      + MyLinkedHashMapA.size());
    iterator = (ListIterator<MyMap1A.Entry<Integer, ProductAA>>) 
      MyLinkedHashMapA.listIterator(MyLinkedHashMapA.size());
    printResultsFile1.println(
      "Print LinkedHashMap BackWard (By Iterator)");
    while (iterator.hasPrevious())
    { printResultsFile1.println(iterator.previous());
    }
    printResultsFile1.println(
      "Number of elements in LinkedHashMap: " 
        + MyLinkedHashMapA.size());
    iterator = (ListIterator<MyMap1A.Entry<Integer, ProductAA>>) 
      MyLinkedHashMapA.listIterator(0);
    product = new ProductAA(100, "Viola", 8750);
    e = new MyMap1A.Entry<Integer, ProductAA> 
      (product.getId(), product);
    iterator.set(e);
    printResultsFile1.println(
      "Elements in LinkedHashMap After Change by Iterator\n" 
        + MyLinkedHashMapA);
    printResultsFile1.println(
      "Number of elements in LinkedHashMap: " 
        + MyLinkedHashMapA.size());
    printResultsFile1.close();
  }
  public static ProductAA[] createArrayProductsA() 
  {  ProductAA[] ap = new ProductAA[]
	 { new ProductAA(10, "Violin", 1000),
	   new ProductAA(10, "Violin", 1200),
	   new ProductAA(40, "Cello", 2000),
	   new ProductAA(35, "Cello", 2000),
	   new ProductAA(34, "Cello", 3000),
	   new ProductAA(33, "Cello", 2000),
	   new ProductAA(36, "Cello", 1800),
	   new ProductAA(36, "Cello", 2800),
       new ProductAA(22, "Piano", 7000),
       new ProductAA(14, "Drums", 8000),
       new ProductAA(99, "Java HW1", 1000),
       new ProductAA(70, "Guitar", 5000),
       new ProductAA(11, "Sababa Targil", 700),
       new ProductAA(1, "Trombone", 100),
       new ProductAA(2, "Saxophone", 200),
       new ProductAA(3, "Bass Guitar", 300),
       new ProductAA(4, "Trumpet", 400),
       new ProductAA(13, "ContraBass", 300),
	   new ProductAA(13, "ContraBass", 400)
	 };
     return ap;
  }
  private static<E>  void quickSort(E[] list,
	Comparator<E> comparator)
  { quickSort(list, 0, list.length - 1, comparator);
  }
  private static<E> void quickSort(E[] list, int first, int last,
	Comparator<E> comparator)
  { if (last > first)
    { int pivotIndex = partition(list, first, last, comparator);
	  quickSort(list, first, pivotIndex - 1, comparator);
	  quickSort(list, pivotIndex + 1, last, comparator);
	}
   }
   private static <E> 
     int partition(E[] list, int first, int last,
	   Comparator<? super E> comp)
   { E pivot = list[first]; 
	 int low = first + 1; 
	 int high = last; 
	 while (high > low)
	 { while (low<=high && comp.compare(list[low], pivot)<=0)
	   { low++;
	   }
	   while (low <= high && comp.compare(list[high], pivot)>0)
	   { high--;
	   }
	   if (high > low)
	   { E temp = list[high];
	     list[high] = list[low];
	     list[low] = temp;
	   }
	  }
	  while (high>first && comp.compare(list[high], pivot)>=0)
	  { high--;
	  }
	  if (comp.compare(pivot, list[high]) > 0)
	  { list[first] = list[high];
	    list[high] = pivot;
	    return high;
	  }
	  else
	  { return first;
	  }
    }
}
class Product1Product1ComparatorAA 
  implements Comparator<ProductAA> 
{ public int compare(ProductAA o1, ProductAA o2)
  { int b = o1.getName().compareTo(o2.getName());
	if (b == 0) b = (o1.getPrice() - o2.getPrice());
	return b; 
  }
}
class Product1ComparatorBB 
  implements Comparator<ProductAA> 
{ public int compare(ProductAA o1, ProductAA o2)
  { return o2.getPrice() - o1.getPrice();
  }
}
class ProductAA implements Comparable<ProductAA>
{  private int id;
   private String name;
   private int price;
   public ProductAA(int id, String name, int price)
   {  this.id = id;
      this.name = name;
      this.price = price;
   }
   public int getId()
   {  return id;
   }
   public String getName()
   {  return name;
   }
   public int getPrice()
   {  return price;
   }
   public void setPrice(int price)
   {  this.price = price;
   }
   public String toString()
   {  return String.format("%3d %-13s %8d", id, name, price);
   }
   public int hashCode()
   { return this.getName().hashCode();
   }
   @Override
   public boolean equals(Object o)
   { if (this.compareTo((ProductAA)o) == 0) return true;
     else    return false;
   }
   @Override
   public int compareTo(ProductAA o)
   {  if (this.getId() > o.getId()) return 1; 
      else
      if (this.getId() < o.getId()) return -1; 
      else 
      if (this.getName().compareTo(o.getName()) > 0) return 1;
      else
      if  (this.getName().compareTo(o.getName()) < 0) return -1;	  
      else 
      if (this.getPrice() > o.getPrice()) return 1;
      else 
      if (this.getPrice() < o.getPrice()) return -1; 	  
      else return 0;
   }
}
class MyLinkedHashMapA<K, V> implements MyMap1A<K, V> 
{ private static int DEFAULT_INITIAL_CAPACITY = 4;
  private static int MAXIMUM_CAPACITY = 1 << 30; 
  private int capacity;
  private static float DEFAULT_MAX_LOAD_FACTOR = 0.75f; 
  private float loadFactorThreshold; 
  private int size = 0; 
  private boolean rehash = false;
  private boolean setOperation = false;
  LinkedList<Entry<K,V>>[] table;
  ArrayList<Entry<K,V>> array;
  public MyLinkedHashMapA()
  { this(DEFAULT_INITIAL_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);    
  }
  public MyLinkedHashMapA(int initialCapacity)
  { this(initialCapacity, DEFAULT_MAX_LOAD_FACTOR);    
  }
  public MyLinkedHashMapA(int initialCapacity, 
	float loadFactorThreshold)
  { if (initialCapacity > MAXIMUM_CAPACITY)
      this.capacity = MAXIMUM_CAPACITY;
    else
      this.capacity = trimToPowerOf2(initialCapacity);
    this.loadFactorThreshold = loadFactorThreshold;    
    table = new LinkedList[capacity];
    array = new  ArrayList();
  }
  public void clear()
  { size = 0;
    removeEntries();
  }
  public boolean containsKey(K key)
  { return get(key) != null;
  }
  public boolean containsValue(V value)
  { for (int i = 0; i < capacity; i++)
    { if (table[i] != null)
      { LinkedList<Entry<K, V>> bucket = table[i];
        for (Entry<K, V> entry: bucket)
          if (entry.getValue().equals(value))
            return true;
      }
    }
    return false;
  }
  public Set<Entry<K,V>> entrySet()
  { Set<Entry<K, V>> set =
      new HashSet<Entry<K, V>>();
    for (int i = 0; i < capacity; i++)
    { if (table[i] != null) {
        LinkedList<Entry<K, V>> bucket = table[i];
        for (Entry<K, V> entry: bucket)
          set.add(entry); 
      }
    }
    return set;
  }
  public V get(K key)
  { int bucketIndex = hash(key.hashCode());
    if (table[bucketIndex] != null)
    { LinkedList<Entry<K, V>> bucket =
           table[bucketIndex]; 
      for (Entry<K, V> entry: bucket)
        if (entry.getKey().equals(key)) 
          return entry.getValue();
    }
    return null;
  }
  public boolean isEmpty()
  { return size == 0;
  }  
  public Set<K> keySet()
  { Set<K> set = new HashSet<K>();
    for (int i = 0; i < capacity; i++)
    { if (table[i] != null)
      { LinkedList<Entry<K, V>> bucket = table[i];
        for (Entry<K, V> entry: bucket)
          set.add(entry.getKey()); 
      }
    }
    return set;
  }
  public V put(K key, V value)
  { if (get(key) != null)
    { // The key is already in the map
      int bucketIndex = hash(key.hashCode());
      LinkedList<Entry<K, V>> bucket =
    	table[bucketIndex]; 
      for (Entry<K, V> entry: bucket)
      { if (entry.getKey().equals(key))
        { V oldValue = entry.getValue();
          // Replace old value with new value
          entry.value = value; 
          if (!rehash && !setOperation) 
          // update entry by iterator
          {	 int i=0;
             for (Entry<K, V> e: array)
             {	if (e.getKey().equals(key))
                {  array.set(i, entry);
                   break;
                }
                else i++;
             }
          }
          // Return the old value for the key
          return oldValue;
        }
      }
    }
    // Check load factor
    if (size >= capacity * loadFactorThreshold)
    { if (capacity == MAXIMUM_CAPACITY)
        throw new RuntimeException("Exceeding maximum capacity");
      rehash=true;
      rehash();
      rehash=false;
    }
    int bucketIndex = hash(key.hashCode());
    // Create a linked list for the bucket if it is not created
    if (table[bucketIndex] == null)
    {  table[bucketIndex] = new LinkedList<Entry<K, V>>();
    }
    // Add a new entry (key, value) to hashTable[index]
    Entry<K, V> e =
      new Entry<K, V>(key, value);
    table[bucketIndex].add(e);
    if (!rehash && !setOperation)
      array.add(0, e);
    size++; // Increase size
    return value;  
  } 
  @Override /** Remove the entries for the specified key */
  public void remove(K key)
  { int bucketIndex = hash(key.hashCode());
    // Remove the first entry that matches the key from a bucket
    if (table[bucketIndex] != null)
    { LinkedList<Entry<K, V>> bucket =
        table[bucketIndex]; 
      for (Entry<K, V> entry: bucket)
      { if (entry.getKey().equals(key))
        { bucket.remove(entry);
          int i=0;
          if (!setOperation)
          { for (Entry<K, V> e: array)
             {	if (e.getKey().equals(key))
                  {  array.remove(i);
                     break;
                  }
                else i++;
             }
          }
        }
      }
      size--; // Decrease size
    }
  }
  @Override /** Return the number of entries in this map */
  public int size()
  { return size;
  }
  public ListIterator<Entry<K,V>> listIterator(int current)
  { return new MyLinkedHashMapAIterator(this, current);
  }
  public ListIterator<Entry<K,V>> listIterator()
  { return listIterator(0);
  }
  public Iterator<Entry<K,V>> iterator()
  { return listIterator(0);
  }
  private class MyLinkedHashMapAIterator 
    implements ListIterator<Entry<K,V>>
  { private int current = 0; 
    private MyLinkedHashMapA<K, V> set;
    public MyLinkedHashMapAIterator(MyLinkedHashMapA<K, V> set, 
      int current)
    { this.set=set;
      this.current=current;
    }
    @Override
    public boolean hasNext()
    { if (current < array.size()) return true;
      else return false;
    }
    @Override
    public boolean hasPrevious()
    { if (current > 0) return true;
      else return false;
    }
    @Override
    public Entry<K,V> next()
    { return array.get(current++);
    }
    @Override
    public Entry<K,V> previous()
    { return array.get(--current);
    }
    @Override
    public void remove()
    { if (current >= array.size())
      	throw new NoSuchElementException();
      else
        ((MyMap1A<K, V>) set).remove(array.get(current).getKey()); 
    }
    @Override
    public void add(Entry<K,V> e)
    { ((MyMap1A<K, V>) set).put(e.getKey(), e.getValue()); 
    }
    @Override
	public int nextIndex()
	{ return current+1;
	}
    @Override
	public int previousIndex()
	{ return current-1;
	}
    @Override
	public void set(Entry<K,V> e)
	{ setOperation = true;
	  remove();
	  add(e);
	  array.set(current, e);
	  setOperation = false;
	}
  }  
  @Override /** Return a set consisting of the values in this map */
  public Set<V> values()
  { Set<V> set = new HashSet<V>();
    for (int i = 0; i < capacity; i++)
    { if (table[i] != null)
      { LinkedList<Entry<K, V>> bucket = table[i];
        for (Entry<K, V> entry: bucket)
          set.add(entry.getValue()); 
      }
    }
    return set;
  }
  /** Hash function */
  private int hash(int hashCode)
  { return supplementalHash(hashCode) & (capacity - 1);
  }
  /** Ensure the hashing is evenly distributed */
  private static int supplementalHash(int h)
  { h ^= (h >>> 20) ^ (h >>> 12);
    return h ^ (h >>> 7) ^ (h >>> 4);
  }
  /** Return a power of 2 for initialCapacity */
  private int trimToPowerOf2(int initialCapacity)
  { int capacity = 1;
    while (capacity < initialCapacity)
    { capacity <<= 1;
    }
    return capacity;
  }
  /** Remove all entries from each bucket */
  private void removeEntries()
  { array.clear();
	for (int i = 0; i < capacity; i++)
    { if (table[i] != null)
      { table[i].clear();
      }
    }
  }
  /** Rehash the map */
  private void rehash()
  { Set<Entry<K, V>> set = entrySet(); // Get entries
    capacity <<= 1; // Double capacity    
    table = new LinkedList[capacity]; // Create a new hash table
    size = 0; // Reset size to 0
    for (Entry<K, V> entry: set)
    {  put(entry.getKey(), entry.getValue()); // Store to new table
    }
  }
  @Override
  public String toString()
  { StringBuilder builder = new StringBuilder("[");
    for (int i = 0; i < capacity; i++)
    {  if (table[i] != null && table[i].size() > 0) 
        for (Entry<K, V> entry: table[i])
        { builder.append(entry);
          builder.append("\n ");
        }
    }
    builder.append("]");
    return builder.toString();
  }
 }
interface MyMap1A<K, V> 
{ /** Remove all of the entries from this map */ 
  public void clear();
  /** Return true if the specified key is in the map */
  public boolean containsKey(K key);
  /** Return true if this map contains the specified value */ 
  public boolean containsValue(V value);
  /** Return a set of entries in the map */
  public Set<Entry<K, V>> entrySet();
  /** Return the first value that matches the specified key */
  public V get(K key);
  /** Return true if this map contains no entries */
  public boolean isEmpty();
  /** Return a set consisting of the keys in this map */
  public Set<K> keySet();
  /** Add an entry (key, value) into the map */
  public V put(K key, V value);
  /** Remove the entries for the specified key */
  public void remove(K key);
  /** Return the number of mappings in this map */
  public int size();
  /** Return a set consisting of the values in this map */
  public Set<V> values();
  /** Iterator */
  public Iterator<Entry<K, V>> iterator();
  /** ListIterator */
  public ListIterator<Entry<K, V>>
        listIterator(int index);
  public ListIterator<Entry<K,V>> listIterator();
  /** Define inner class for Entry */
  public static class Entry<K, V>
  { K key;
    V value;
    public Entry(K key, V value)
    { this.key = key;
      this.value = value;
    }
    public K getKey()
    { return key;
    }
    public V getValue()
    { return value;
    }
    @Override
    public String toString()
    { return "[" + key + ", " + value + "]";
    }
  }
}    
