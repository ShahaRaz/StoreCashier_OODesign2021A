package Lec5nov17;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
public class TestMyLinkedHashSet
{ @SuppressWarnings("unchecked")
  public static void main(String[] args) 
	throws FileNotFoundException
  { File resultsFile1 = new File("results1.txt");
    PrintWriter printResultsFile1 = 
      new PrintWriter(resultsFile1); 
	Product1[] ap = createArrayProducts();
	quickSort(ap, new Product1Comparator1());
    MySet1<Product1> myLinkedHashSet = 
      new MyLinkedHashSet<Product1>();
    ListIterator<Product1> iterator = 
      (ListIterator<Product1>) myLinkedHashSet.listIterator();
    for (Product1 p: ap)
      myLinkedHashSet.add(p);
    printResultsFile1.println("Elements in LinkedHashSet\n" 
      + myLinkedHashSet);
    printResultsFile1.println("Number of elements in LinkedHashSet: " 
      + myLinkedHashSet.size());
    printResultsFile1.println("Elements in LinkedHashSet in the order "
      + " (Product1Comparator1) they enter the list (By Iterator)"); 
    while (iterator.hasNext())
    { printResultsFile1.println(iterator.next());
    }
    printResultsFile1.println("Number of elements in LinkedHashSet: " 
      + myLinkedHashSet.size());
    printResultsFile1.println("Is \"10 Violin 1000.11f\" in LinkedHashSet? "
      + myLinkedHashSet.contains(new Product1(10, "Violin", 1000.11f)));
    iterator = (ListIterator<Product1>) myLinkedHashSet.listIterator(1);
    iterator.set(new Product1(41, "Cello", 2000.25f));
    printResultsFile1.println("Elements in LinkedHashSet"
      + " after the Iterator changed id and price of second element "
      + "that enter the list\n" +  myLinkedHashSet); 
    printResultsFile1.println("Number of elements in LinkedHashSet: " 
      + myLinkedHashSet.size());
    iterator = (ListIterator<Product1>) myLinkedHashSet.listIterator(0);
    printResultsFile1.println("Elements in LinkedHashSet"
      + " after the Iterator changed id and price of second element "
      + "that enter the list (By Iterator)"); 
    while (iterator.hasNext())
    {  printResultsFile1.println(iterator.next());
    }
    printResultsFile1.println("Number of elements in LinkedHashSet: " 
      + myLinkedHashSet.size());
    myLinkedHashSet.remove(new Product1(22, "Piano", 7000.33f));
    myLinkedHashSet.remove(new Product1(13, "ContraBass", 300.88f));
    printResultsFile1.println("Elemebts in HashSet after remove " 
      + "\"22 Piano 7000.33f\"" + " and " + "\"13 ContraBass 300.88f\n"
      + myLinkedHashSet);
    printResultsFile1.println("Number of elements in LinkedHashSet: " 
      + myLinkedHashSet.size());
    iterator = 
      (ListIterator<Product1>) myLinkedHashSet.listIterator(0);
    printResultsFile1.println("Print LinkedHashSet ForWard  (By Iterator)");
    while (iterator.hasNext())
    { printResultsFile1.println(iterator.next());
    }
    printResultsFile1.println("Number of elements in LinkedHashSet: " 
      + myLinkedHashSet.size());
    iterator = 
      (ListIterator<Product1>)myLinkedHashSet.listIterator(myLinkedHashSet.size());
    printResultsFile1.println("Print LinkedHashSet BackWard  (By Iterator)");
    while (iterator.hasPrevious())
    { printResultsFile1.println(iterator.previous());
    }
    printResultsFile1.println("Number of elements in LinkedHashSet: " 
      + myLinkedHashSet.size());
    myLinkedHashSet.clear();
    printResultsFile1.println("Elements in LinkedHashSet after clearing: " 
      + myLinkedHashSet);
    printResultsFile1.println("Number of elements in LinkedHashSet after clearing: " 
      + myLinkedHashSet.size());
    quickSort(ap, new Product1Comparator2());
    for (Product1 p: ap)
      iterator.add(p);
    printResultsFile1.println("Elements in LinkedHashSet Inserted By "
      + " Iterator\n" + myLinkedHashSet);	
    printResultsFile1.println("Number of elements in LinkedHashSet: " 
      + myLinkedHashSet.size());
    iterator = (ListIterator<Product1>) myLinkedHashSet.listIterator(0);
    printResultsFile1.println("Elements in LinkedHashSet in the order "
      + " (Product1Comparator2) they enter the list (By Iterator)"); 
    while (iterator.hasNext())
    { printResultsFile1.println(iterator.next());
    }
    printResultsFile1.println("Number of elements in LinkedHashSet after clearing: " 
      + myLinkedHashSet.size());
    iterator = (ListIterator<Product1>) myLinkedHashSet.listIterator(0);
    while (iterator.hasNext())
    { iterator.remove();
    }
    printResultsFile1.println("Elements in LinkedHashSet Deleted By "
      + " Iterator\n" + myLinkedHashSet);	
    printResultsFile1.println("Number of elements in LinkedHashSet: " 
      + myLinkedHashSet.size());
    myLinkedHashSet.add(new Product1(10, "Violin", 1000.11f));
    printResultsFile1.println("Elements in LinkedHashSet After add One Element\n" 
      + myLinkedHashSet);
    printResultsFile1.println("Number of elements in LinkedHashSet: " 
      + myLinkedHashSet.size());
    iterator = (ListIterator<Product1>) myLinkedHashSet.listIterator(0);
    iterator.set(new Product1(40, "Cello", 2000.22f));
    printResultsFile1.println("Elements in LinkedHashSet after "
      + "change by Iterator\n" + myLinkedHashSet);	
    printResultsFile1.println("Number of elements in LinkedHashSet: " 
      + myLinkedHashSet.size());
    printResultsFile1.close();
  }
  public static Product1[] createArrayProducts() 
  { Product1[] ap = new Product1[]
	{ new Product1(10, "Violin", 1000.11f),
	  new Product1(10, "Violin", 1000.11f),	  
	  new Product1(40, "Cello", 2000.22f),
	  new Product1(35, "Cello", 2000.22f),
	  new Product1(34, "Cello", 3000.22f),
	  new Product1(33, "Cello", 2000.22f),
	  new Product1(36, "Cello", 1800.22f),
	  new Product1(36, "Cello", 1800.22f),
      new Product1(22, "Piano", 7000.33f),
      new Product1(14, "Drums", 8000.44f),
      new Product1(99, "Java HW1", 1000.55f),
      new Product1(70, "Guitar", 5000.66f),
      new Product1(11, "Sababa Targil", 700.77f),
      new Product1(1, "Trombone", 100),
      new Product1(2, "Saxophone", 200),
      new Product1(3, "Bass Guitar", 300),
      new Product1(4, "Trumpet", 400),
      new Product1(13, "ContraBass", 300.88f),
      new Product1(13, "ContraBass", 300.88f)
	};
    return ap;
  }
  private static<E>void quickSort(E[] list,
	Comparator<? super E> comparator)
  { quickSort(list, 0, list.length - 1, comparator);
  }
  private static<E>void quickSort(E[] list, int first, int last,
	Comparator<? super E> comparator)
  { if (last > first)
    { int pivotIndex = partition(list, first, last, comparator);
	  quickSort(list, first, pivotIndex - 1, comparator);
	  quickSort(list, pivotIndex + 1, last, comparator);
	}
   }
   private static <E> int partition(E[] list, int first, int last,
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
class Product1Comparator1 implements Comparator<Product1> 
{ public int compare(Product1 o1, Product1 o2)
  { int b=o1.getName().compareTo(o2.getName());
	if (b==0)
	{ if (o1.getPrice() < o2.getPrice()) return -1;
	  else if (o1.getPrice() > o2.getPrice()) return 1;
	       else return 0;
	}
	else return b; 
  }
}
class Product1Comparator2 implements Comparator<Product1> 
{ public int compare(Product1 o1, Product1 o2)
  { float f = o1.getPrice() - o2.getPrice();
	if (f < 0.0f ) return 1;
	else if (f > 0.0f) return -1;
	     else return 0; 
  }
}
class Product1 implements Comparable<Product1>
{  private int id;
   private String name;
   private float price;
   public Product1(int id, String name, float price)
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
   public float getPrice()
   {  return price;
   }
   public void setPrice(float price)
   {  this.price = price;
   }
   public String toString()
   {  return String.format("%3d %-13s %8.2f", id, name, price);
   }
   @Override
   public int compareTo(Product1 o)
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
   @Override
   public boolean equals(Object o)
   { if (this.compareTo((Product1)o) == 0) return true;
     else return false;  
   }
   public int hashCode()
   { return this.name.hashCode();
   }
}  
class MyLinkedHashSet<E> implements MySet1<E>
{ private static int DEFAULT_INITIAL_CAPACITY = 4;
  private static int MAXIMUM_CAPACITY = 1 << 30; 
  private int capacity;
  private static float DEFAULT_MAX_LOAD_FACTOR = 0.75f; 
  private float loadFactorThreshold; 
  private int size = 0; 
  private boolean rehash = false;
  private LinkedList<E>[] table;
  private ArrayList<E> array;
  public MyLinkedHashSet()
  { this(DEFAULT_INITIAL_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);    
  }
  public MyLinkedHashSet(int initialCapacity)
  { this(initialCapacity, DEFAULT_MAX_LOAD_FACTOR);    
  }
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public MyLinkedHashSet(int initialCapacity, 
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
    removeElements();
  }
  public boolean contains(E e)
  { int bucketIndex = hash(e.hashCode());
    if (table[bucketIndex] != null)
    { LinkedList<E> bucket = table[bucketIndex]; 
      for (E element: bucket)
        if (element.equals(e)) 
          return true;
    }
    return false;
  }
  public boolean add(E e)
  { if (contains(e))  return false;
    if (size() + 1> capacity * loadFactorThreshold)
    { if (capacity == MAXIMUM_CAPACITY)
        throw new RuntimeException("Exceeding maximum capacity");
      else
      { rehash = true;
        rehash();
        rehash = false;
      }
    }
    int bucketIndex = hash(e.hashCode());
    if (table[bucketIndex] == null)
    { table[bucketIndex] = new LinkedList<E>();
    }
    table[bucketIndex].add(e);
    size++; 
    if (!rehash)
      array.add(e);
    return true;
  }
  public boolean remove(E e)
  { if (!contains(e)) return false;
    int bucketIndex = hash(e.hashCode());
    if (table[bucketIndex] != null)
    { LinkedList<E> bucket = table[bucketIndex]; 
      for (E element: bucket)
        if (e.equals(element))
        { bucket.remove(element);
          break;
        }
    }
    array.remove(e);
    size--; 
    return true;
  }
  public boolean isEmpty()
  { return size() == 0;
  }
  public int size()
  { return size; //array.size();
  }
  public ListIterator<E> listIterator(int current)
  { return new MyLinkedHashSetIterator(this, current);
  }
  public ListIterator<E> listIterator()
  { return listIterator(0);
  }
  public Iterator<E> iterator()
  { return listIterator(0);
  }
  private class MyLinkedHashSetIterator 
    implements ListIterator<E>
  { private int current = 0; 
    private MyLinkedHashSet<E> set;
    public MyLinkedHashSetIterator(MyLinkedHashSet<E> set,
      int current)
    { this.set=set;
      this.current=current;
    }
    public boolean hasNext()
    { if (current < array.size()) return true;
      else return false;
    }
    public boolean hasPrevious()
    { if (current > 0) return true;
      else return false;
    }
    public E next()
    { return array.get(current++);
    }
    public E previous()
    { return array.get(--current);
    }
    public void remove()
    { set.remove(array.get(current)); 
    }
    public void add(E e)
    { set.add(e); 
    }
	public int nextIndex()
	{ return current+1;
	}
	public int previousIndex()
	{ return current-1;
	}
	public void set(E e)
	{ remove();
	  add(e);
	}
  }  
  private int hash(int hashCode)
  { return supplementalHash(hashCode) & (capacity - 1);
  }
  private static int supplementalHash(int h)
  { h ^= (h >>> 20) ^ (h >>> 12);
    return h ^ (h >>> 7) ^ (h >>> 4);
  }
  private int trimToPowerOf2(int initialCapacity)
  { int capacity = 1;
    while (capacity < initialCapacity)
    { capacity <<= 1;
    }
    return capacity;
  }
  private void removeElements()
  { array.clear();
	for (int i = 0; i < capacity; i++)
    { if (table[i] != null)
      { table[i].clear();
      }
    }
  }
  @SuppressWarnings("unchecked")
  private void rehash()
  { ArrayList<E> list = setToList(); 
    capacity <<= 1; 
    table = new LinkedList[capacity]; 
    size = 0; 
    for (E element: list)
    { add(element); 
    }
  }
  private ArrayList<E> setToList()
  { ArrayList<E> list = new ArrayList<E>();
    for (int i = 0; i < capacity; i++)
    { if (table[i] != null)
      { for (E e: table[i])
        { list.add(e);
        }
      }
    }  
    return list;
  }
  public String toString()
  { int i;
    ArrayList<E> list = setToList();
    StringBuilder builder = new StringBuilder("[");
    for (i = 0; i < list.size() - 1; i++)
    { if (i!=0 && i%3 == 0) builder.append("\n ");
      builder.append(list.get(i) + ", ");
    }
    if (list.size() == 0)  builder.append("]");
    else
    { if (i!=0 && i%3 == 0) builder.append("\n ");
      builder.append(list.get(list.size() - 1) + "]");
    }
    return builder.toString();
  }
}
interface MySet1<E> extends Iterable<E>
{ /** Remove all elements from this set */
  public void clear();
  /** Return true if the element is in the set */
  public boolean contains(E e);
  /** Add an element to the set */
  public boolean add(E e);
  /** Remove the element from the set */
  public boolean remove(E e);
  /** Return true if the set contains no elements */
  public boolean isEmpty();
  /** Return the number of elements in the set */
  public int size();
  /** ListIterator  */
  public ListIterator<E> listIterator(int size);
  public ListIterator<E> listIterator();
}

