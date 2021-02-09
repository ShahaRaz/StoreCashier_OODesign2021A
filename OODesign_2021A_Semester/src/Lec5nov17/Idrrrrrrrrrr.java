package Lec5nov17;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.ListIterator;
public class Idrrrrrrrrrr
{ public static void main(String[] args) 
   	throws FileNotFoundException
  { try
	{ MyCustomerP[] arrCustomers = new MyCustomerP[]
      { new MyCustomerP(1, "Elivs is alive"),
        new MyCustomerP(2, "Cleopatra"), 	
        new MyCustomerP(3, "Shilgiya"),
        new MyCustomerP(4, "007 James Bond"), 
        new MyCustomerP(4, "James Bond"), 
        new MyCustomerP(4, "Shon Konery"), 
        new MyCustomerP(5, "Gooliver") 	
      };
      File resultsFile = new File("resultsP1.txt");
      PrintWriter printResultsFile = 
        new PrintWriter(resultsFile); 
      long n = 50, startTime, endTime;
      int i, j, k;
      MySortArrayList<MyCustomerP> list1 = 
        new MySortArrayList<MyCustomerP>(
    	  new MyCustomer1ComparatorP1());
      startTime = System.currentTimeMillis();
      ListIterator<MyCustomerP> iterator;
      k=0;
      for (i = 0; i < n; i++)
      { iterator = list1.listIterator(i);
        iterator.add(arrCustomers[k++]);
        if (k >= arrCustomers.length) k=0;
      }
      printResultsFile.println(
        "list1 after iterator adds elements");
      printResultsFile.println(list1);
      printResultsFile.println("list1 size " + list1.size());
      Object[][] m1 = list1.toMatrix(2, 3);
      Object[][] m2 = 
        matrixSort1(m1, new MyCustomer1ComparatorP3());
      printResultsFile.println(
    	"list1 to matrix");
      for (i = 0; i < 2; i++)
      {	for (j = 0; j < 3; j++)
       	{ if (m1[i][j] == null) break; 
       	    printResultsFile.print(m1[i][j] + "  ");
       	}
        printResultsFile.println();
      }
      printResultsFile.println(
    	"list1 to sort matrix by MyCustomer1ComparatorP3");
      for (i = 0; i < 2; i++)
      {	for (j = 0; j < 3; j++)
      	{ if (m2[i][j] == null) break; 
    	  printResultsFile.print(m2[i][j] 
    			  + "  ");
    	}
        printResultsFile.println();
      }
      iterator = list1.listIterator(0);
      try
      { iterator.set(new MyCustomerP(0, "Set Element"));
        iterator = list1.listIterator(-1);
        iterator.set(new MyCustomerP(10, "wrong index"));
      }
      catch (IndexOutOfBoundsException ex)
      { printResultsFile.println("IndexOutOfBoundsException: "
          + " set operation with illegal index ");   
      }
      printResultsFile.println(
    	"list1 after iterator sets element");
      printResultsFile.println(list1);
      printResultsFile.println("list1 size " + list1.size());
      iterator = list1.listIterator(0);
      printResultsFile.println("list1 forward by listIterator");
      while (iterator.hasNext())
        printResultsFile.println(iterator.next());
      printResultsFile.println("list1 size " + list1.size());
      iterator = list1.listIterator(list1.size());
      printResultsFile.println("list1 backward by listIterator");
      while (iterator.hasPrevious())
        printResultsFile.println(iterator.previous());
      printResultsFile.println("list1 size " + list1.size());
      printResultsFile.println("max element in list1");
      printResultsFile.println(list1.max());
      printResultsFile.println("min element in list1");
      printResultsFile.println(list1.min());
      k=0; j=0; i=0;
      iterator = list1.listIterator(0);
      iterator.remove();
      printResultsFile.println(
        "list1 after iterator remove the first element");
      printResultsFile.println(list1);
      printResultsFile.println("list1 size " + list1.size());
      while (list1.size() > 0)
      { printResultsFile.println("Iteration Number " + i);
        printResultsFile.println("list1 contains " 
          +  arrCustomers[k] + " "
          + list1.contains(arrCustomers[k]));
        printResultsFile.println("list1 get element at index " 
          + j + " " + list1.get(j));
        printResultsFile.println("list1 indexOf " 
          + arrCustomers[k] 
          + " " + list1.indexOf(arrCustomers[k]));
        printResultsFile.println("list1 isEmpty " 
          + list1.isEmpty());
        printResultsFile.println(
          "list1 remove element at index " 
    	  + j + " " + list1.remove(j));
        printResultsFile.println("list1");
        printResultsFile.println(list1);
        printResultsFile.println("list1 size " + list1.size());
        k++;
        if (k >= arrCustomers.length) k=0;
        j++;
        if (j >= list1.size()) j=0;
        i++;
      }
      k=0;
      for (i = 0; i < n; i++)
      { list1.add(arrCustomers[k]);
        k++;
        if (k >= arrCustomers.length) k=0;
      }
      printResultsFile.println("list1");
      printResultsFile.println(list1);
      printResultsFile.println("list1 size " + list1.size());
      list1.setComparator(new MyCustomer1ComparatorP2());
      printResultsFile.println(
    	"list1 after sort by id descending");
      printResultsFile.println(list1);
      printResultsFile.println("list1 size " + list1.size());
      list1.setComparator(new MyCustomer1ComparatorP3());
      printResultsFile.println(
    	"list1 after sort by customerName ascending");
      printResultsFile.println(list1);
      printResultsFile.println("list1 size " + list1.size());
      list1.setComparator(new MyCustomer1ComparatorP4());
      printResultsFile.println(
    	"list1 after sort by customerName descending");
      printResultsFile.println(list1);
      printResultsFile.println("list1 size " + list1.size());
      list1.add(new MyCustomerP(8, "Rolling Stones"));
      list1.add(new MyCustomerP(8, "Rolling Stones"));
      list1.add(new MyCustomerP(7, "Eagles"));
      list1.add(new MyCustomerP(6, "Beetles"));
      list1.add(new MyCustomerP(5, "Deep Purple"));
      list1.remove(new MyCustomerP(5, "Deep Purple"));
      list1.set(new MyCustomerP(6, "Beetles"), 
        new MyCustomerP(6, "Beatles"));
      list1.add(new MyCustomerP(5, "Deep Purple"));
      printResultsFile.println(
        "list1 after adding, remove and set elements");
      printResultsFile.println(list1);
      printResultsFile.println("list1 size " + list1.size());
      MySortArrayList<MyCustomerP> list2 = 
        new MySortArrayList<MyCustomerP>(arrCustomers, 
    	  new MyCustomer1ComparatorP3());
      printResultsFile.println("list2");
      printResultsFile.println(list2);
      printResultsFile.println("list2 size " + list2.size());
      printResultsFile.println("list1 union list2");
      printResultsFile.println(list1.union(list2));
      printResultsFile.println("list1.union.list2 size " 
        + list1.union(list2).size());
      printResultsFile.println("list2 union list1");
      printResultsFile.println(list2.union(list1));
      printResultsFile.println("list2.union.list1 size " 
    	+ list2.union(list1).size());
      printResultsFile.println(
        "list1 union list2 is equlas list2 union list1?");
      printResultsFile.println(
        (list1.union(list2)).equals(list2.union(list1)));
      printResultsFile.println("list1 intersection list2");
      printResultsFile.println(list1.intersection(list2));
      printResultsFile.println("list1.intersecion.list2 size " 
        + list1.intersection(list2).size());
      printResultsFile.println(
        "list1 intersection list2 is equlas list2 intersection list1?");
      printResultsFile.println(
        list1.intersection(list2).equals(list2.intersection(list1)));
      printResultsFile.println("list1 difference list2");
      printResultsFile.println(list1.difference(list2));
      printResultsFile.println("list1.difference.list2 size " 
        + list1.difference(list2).size());
      printResultsFile.println("list2 difference list1");
      printResultsFile.println(list2.difference(list1));
      printResultsFile.println("list2.difference.list1 size " 
        + list2.difference(list1).size());
      printResultsFile.println(
        "list1 difference list2 is equlas list2 difference list1?");
      printResultsFile.println(
        list1.difference(list2).equals(list2.difference(list1)));
      printResultsFile.println("list1 xor list2");
      printResultsFile.println(list1.xor(list2));
      printResultsFile.println("list1.xor.list2 size " 
        + list1.xor(list2).size());
      printResultsFile.println("list2 xor list1");
      printResultsFile.println(list2.xor(list1));
      printResultsFile.println("list2.xor.list1 size " 
        + list2.xor(list1).size());
      printResultsFile.println(
        "list1 xor list2 is equlas list2 xor list1?");
      printResultsFile.println(
        list1.xor(list2).equals(list2.xor(list1)));
      printResultsFile.println("list1");
      printResultsFile.println(list1);
      printResultsFile.println("list1 size " + list1.size());
      printResultsFile.println("list2");
      printResultsFile.println(list2);
      printResultsFile.println("list2 size " + list2.size());
      printResultsFile.println("is list1 contain list2?");
      printResultsFile.println(list1.contain(list2));
      printResultsFile.println("is list2 contain list1?");
      printResultsFile.println(list2.contain(list1));
      printResultsFile.println("list2 toArray");
      for (i=0; i < list2.toArray().length; i++)
        printResultsFile.println(list2.toArray()[i]);
      printResultsFile.println("list2 size = " + list2.size()
        + " list2.toArray().length = "
        + list2.toArray().length);
      list1.clear();
      list2.clear();
      printResultsFile.println("list1 and list2 were cleared");
      printResultsFile.println("list1 union list2");
      printResultsFile.println(list1.union(list2));
      printResultsFile.println("list1.union(list2).size() = "
        + list1.union(list2).size());
      printResultsFile.println(
        "list1 union list2 is equlas list2 union list1?");
      printResultsFile.println(
        list1.union(list2).equals(list2.union(list1)));
      printResultsFile.println("list1 intersection list2");
      printResultsFile.println(list1.intersection(list2));
      printResultsFile.println(
        "list1.intersection(list2).size() = "
        + list1.intersection(list2).size());
      printResultsFile.println(
        "list1 intersection list2 is equlas list2 intersection list1?");
      printResultsFile.println(
        list1.intersection(list2).equals(list2.intersection(list1)));
      printResultsFile.println("list1 difference list2");
      printResultsFile.println(list1.difference(list2));
      printResultsFile.println("list1.difference(list2).size() = "
        + list1.difference(list2).size());
      printResultsFile.println("is list1 contain list2?");
      printResultsFile.println(list1.contain(list2));
      printResultsFile.println("is list2 contain list1?");
      printResultsFile.println(list2.contain(list1));
      printResultsFile.println("list1 toArray");
      for (i=0; i < list1.toArray().length; i++)
        printResultsFile.println(list1.toArray()[i]);
      printResultsFile.println("list1 size = " + list1.size()
        + " list1.toArray().length = "
        + list1.toArray().length);
      endTime = System.currentTimeMillis();
      printResultsFile.println("Time for MyArrayList: " 
        + (endTime-startTime) + " ms.");
      printResultsFile.close();
	}  
    catch (Exception ex)
    { System.out.println("Exception: "
        + "complete the program.\n" + ex );   
    }
  }
  @SuppressWarnings("unchecked")
  public  static Object[][] 
	matrixSort1(final Object[][] m, Comparator comp)
  { Object[][] p = new Object[m.length][m[0].length];
    int i, j, k;
    ArrayList<Object> a = 
      new ArrayList<Object>(m.length * m[0].length);
    for (i = 0; i < m.length; i++)
	  for (j = 0; j < m[0].length; j++)
	   	{ if (m[i][j] == null) break;	
	   	  a.add(m[i][j]);
	   	}
	Collections.sort(a, comp);
	k=0;
	for (i = 0; i < p.length; i++)
	  for (j = 0; j < p[0].length; j++)
	    { if ((i * p[0].length + j) >=  a.size()) break;
	      p[i][j] = a.get(k++);
	    }  
	return p;
   }
 }
 class MyCustomerP implements Comparable<Object>
 { private int id;
   private String customerName;
   public MyCustomerP(int id, String customerName)
   { this.id = id;
     this.customerName = customerName;
   }
   public int getId()
   { return id;
   }
   public String getCustomerName()
   { return customerName;
   }
   public String toString()
   { return String.format("%2d: %-16s ", id, customerName);
   }
   @Override
   public int compareTo(Object o)
   { return this.getId() - ((MyCustomerP) o).getId();
   }
   @Override
   public boolean equals(Object o)
   { return this.compareTo(o) == 0;
   }
 }
 class MyCustomer1ComparatorP1 implements Comparator<MyCustomerP> 
 { public int compare(MyCustomerP o1, MyCustomerP o2)
   { return o1.compareTo(o2);
   }
 }
 class MyCustomer1ComparatorP2 implements Comparator<MyCustomerP> 
 { public int compare(MyCustomerP o1, MyCustomerP o2)
   { return o2.compareTo(o1);
   }
 }
 class MyCustomer1ComparatorP3 implements Comparator<MyCustomerP> 
 { public int compare(MyCustomerP o1, MyCustomerP o2)
   { return o1.getCustomerName().compareTo(o2.getCustomerName());
   }
 }
 class MyCustomer1ComparatorP4 implements Comparator<MyCustomerP> 
 { public int compare(MyCustomerP o1, MyCustomerP o2)
   { return o2.getCustomerName().compareTo(o1.getCustomerName());
   }
 }
 interface MySortList<E> 
 { public boolean add(E e);
   public void clear();
   public boolean contains(E e);
   public E get(int index);
   public int indexOf(E e);
   public boolean isEmpty();
   public boolean remove(E e);
   public E remove(int index);
   public boolean set(E oldValue, E newValue);
   public int size();
   public E max();
   public E min();
   public void setComparator(Comparator<E> c);
   public Comparator<E> getComparator();
   public MySortArrayList<E> union(MySortArrayList<E> b);
   public MySortArrayList<E> difference(MySortArrayList<E> b);
   public MySortArrayList<E> intersection(MySortArrayList<E> b);
   public MySortArrayList<E> xor(MySortArrayList<E> b);
   public boolean contain(MySortArrayList<E> b);
   public Object[] toArray();
   public Object[][] toMatrix(
           int numberOfRows, int numberOfColumns);
   public boolean equals(MySortArrayList<E> b);
   public ListIterator<E> listIterator(int index);
   public boolean compareComparator(MySortArrayList<E> c);
  }
  class MySortArrayList<E> implements MySortList<E> 
  { public static final int INITIAL_CAPACITY = 2;
    private int size = 0;
    private Comparator<E> c = null;
    @SuppressWarnings("unchecked")
	private E[] data = (E[])new Object[INITIAL_CAPACITY];
    public MySortArrayList(Comparator<E> c)
    { setComparator(c);
    }
    public MySortArrayList(E[] objects, Comparator<E> c)
    { setComparator(c);
      for (int i=0; i < objects.length; i++)
    	add(objects[i]);  
    }
    @Override /** Set new Comparator */
    public  void setComparator(Comparator<E> c)
    { this.c = c;
      sort();    	
    }
    @Override /** Return Comparator */
    public Comparator<E> getComparator()
    { return c;
    }
    @Override /** Check if list is empty */
    public boolean isEmpty()
    { return size == 0;
    }
    @Override /** Return number of objects in the list */
    public int size()
    { return size;
    }
    @Override /** Remove object from the list */
    public boolean remove(E e)
    { if (indexOf(e) >= 0)
      { remove(indexOf(e));
        return true;
      }
      else return false;
    }
    private void add(int index, E e)
    { if (this.contains(e)) return;
      ensureCapacity();
      for (int i = size() - 1; i >= index; i--)
        data[i + 1] = data[i];
      data[index] = e;
      size++;
    }
    /** Create a new larger array, double the current size + 1 */
    private void ensureCapacity()
    { if (size() >= data.length)
      { @SuppressWarnings("unchecked")
	    E[] newData = (E[])(new Object[size() * 2 + 1]);
        System.arraycopy(data, 0, newData, 0, size());
        data = newData;
      }
    }
    @SuppressWarnings("unchecked")
	@Override /** Clear the list */
    public void clear()
    { data = (E[])new Object[INITIAL_CAPACITY];
      size = 0;
    }
    @Override 
    public boolean contains(E e)
    { if (indexOf(e) >= 0) return true;
      else return false;
    }
    @Override 
    public E get(int index)
    { checkIndex(index);
      return data[index];
    }
    private void checkIndex(int index)
    { if (index < 0 || index >= size)
        throw new IndexOutOfBoundsException();
    }
    @Override 
    public int indexOf(E e)
    { int low = 0;
      int high = size() - 1;
      while (high >= low)
      { int mid = (low + high) / 2;
        if (getComparator().compare(e, data[mid]) < 0) high = mid - 1;
        else
        if (getComparator().compare(e, data[mid]) == 0) return mid;
        else low = mid + 1;
      }
      return -low - 1; 
    }
    @Override 
    public E remove(int index)
    { checkIndex(index);
      E e = data[index];
      for (int j = index; j < size() - 1; j++)
        data[j] = data[j + 1];
      data[size() - 1] = null; 
      size--;
      return e;
    }
    @Override 
    public boolean set(E oldValue, E newValue)
    { if (!this.remove(oldValue))  return false;
      return add(newValue);
    }
    @Override
    public String toString()
    { StringBuilder result = new StringBuilder("[");
      for (int i = 0; i < size; i++)
      { result.append(data[i]);
        if (i < size - 1)
        { result.append(", ");
          result.append("\n ");
        }
      }
      return result.toString() + "]";
    }
    public void trimToSize()
    { if (size != data.length)
      { @SuppressWarnings("unchecked")
	    E[] newData = (E[])(new Object[size]);
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
      } 
    }
    private void sort()
    { trimToSize();
      Arrays.sort(data, c);
    }
    @Override
    public ListIterator<E> listIterator(int index)
    { return new ArrayListIteratorP(index);
    }
    private class ArrayListIteratorP implements ListIterator<E>
    { private int current = 0; 
      public ArrayListIteratorP(int index)
      { current = index;
      }
      @Override
      public boolean hasNext()
      { return (current < size());
      }
      @Override
      public E next()
      { //return data[current++];
    	return get(current++);  
      }
      @Override
      public void remove()
      { MySortArrayList.this.remove(current);
      }
	  @Override
	  public boolean hasPrevious()
	  { return (current > 0);
	  }
	  @Override
	  public E previous()
	  {	//return data[--current];
		return get(--current);
	  }
	  @Override
	  public int nextIndex()
	  { return current+1;
  	  }
	  @Override
	  public int previousIndex()
	  {	return current-1;
	  }
	  @Override
	  public void set(E e)
	  { E o = MySortArrayList.this.get(current);  
		MySortArrayList.this.set(o, e);
 	  }
	  @Override
	  public void add(E e)
	  { MySortArrayList.this.add(e);
 	  }
    }
	@Override
	public E max()
	{ if (size() == 0) return null;
	  else return get(size()-1); 
	}
	@Override
	public E min()
	{ if (size() == 0) return null;
	  else return get(0); 
	}
	@Override
	public boolean add(E e)
	{  int index = indexOf(e);
	   if (index >= 0) return false;
	   else
	   { index = Math.abs(index + 1);
	     add(index, e);
	     return true;
	   }  
	}
	@Override
	public  MySortArrayList<E> union(MySortArrayList<E> b)
	{ Comparator<E> comp = null;
	  if (!(compareComparator(b)))
	  { comp = b.getComparator();
		b.setComparator(getComparator());
	  }
      MySortArrayList<E> a = new  MySortArrayList<E>(getComparator());
	  int current1 = 0, current2 = 0, current3 = 0; 
	  while (current1 < this.size() && current2 < b.size())
      { if (getComparator().compare(this.get(current1), 
    		  b.get(current2)) < 0)
          a.add(current3++, this.get(current1++));
    	else 
        if (getComparator().compare(this.get(current1), 
        	  b.get(current2)) > 0)
          a.add(current3++, b.get(current2++));
        else
        if (getComparator().compare(this.get(current1),
        	  b.get(current2)) == 0)
        { a.add(current3++, b.get(current2++));
          current1++;
        }
      }
      while (current1 < this.size())
        a.add(current3++, this.get(current1++));
      while (current2 < b.size())
      	a.add(current3++, b.get(current2++));
      if (comp != null) 
        b.setComparator(comp);
      return a;
    }
    @Override
	public MySortArrayList<E> difference(MySortArrayList<E> b)
	{ Comparator<E> comp = null;
      if (!(compareComparator(b)))
	  { comp = b.getComparator();
		b.setComparator(getComparator());
	  }
      MySortArrayList<E> a = new  MySortArrayList<E>(getComparator());
	  int current1 = 0, current2 = 0, current3 = 0; 
      while (current1 < this.size() && current2 < b.size())
      {	if (getComparator().compare(this.get(current1), b.get(current2)) == 0)
          { current1++;
            current2++;         
          }
  	    else 
        if (getComparator().compare(this.get(current1), b.get(current2)) < 0) 
      	  a.add(current3++, this.get(current1++));
        else                                   
    	  current2++;
      }
      while (current1 < this.size())          
    	a.add(current3++, this.get(current1++));
      if (comp != null) 
        b.setComparator(comp);
      return a;
	}
	@Override
	public MySortArrayList<E> intersection(MySortArrayList<E> b)
	{ Comparator<E> comp = null;
      if (!(compareComparator(b)))
	  { comp = b.getComparator();
		b.setComparator(getComparator());
	  }
	  MySortArrayList<E> a = new  MySortArrayList<E>(getComparator());
	  int current1 = 0, current2 = 0, current3 = 0; 
      while (current1 < this.size() && current2 < b.size())
      {	if (getComparator().compare(this.get(current1), b.get(current2)) == 0)
          { a.add(current3++, this.get(current1++));
    	    current2++;         
          }
  	    else 
        if (getComparator().compare(this.get(current1), b.get(current2)) < 0) 
      	  current1++;
        else                                   
    	  current2++;
      }
      if (comp != null) 
        b.setComparator(comp);
      return a; 
	}
	@Override
	public boolean contain(MySortArrayList<E> b)
	{ Comparator<E> comp = null;
      if (!(compareComparator(b)))
	  { comp = b.getComparator();
		b.setComparator(getComparator());
	  }
	  int current1 = 0, current2 = 0, current3 = 0; 
      while (current1 < this.size() && current2 < b.size())
      {	if (getComparator().compare(this.get(current1), b.get(current2)) == 0)
          { current1++;
    	    current2++; 
    	    current3++;
          }
  	    else 
        if (getComparator().compare(this.get(current1), b.get(current2)) < 0) 
      	  current1++;
        else                                   
    	  current2++;
      }
      if (comp != null) 
        b.setComparator(comp);
	  if (current3 == b.size()) return true;
	  else return false;
	}
	@Override
	public boolean equals(MySortArrayList<E> b)
    { boolean notEquals = true;
	  Comparator<E> comp = null;
      if (!(compareComparator(b)))
	  { comp = b.getComparator();
		b.setComparator(getComparator());
	  }
	  if (this.size() != b.size) return false;
      for (int i=0; i < this.size(); i++) 
      {	if (!(this.get(i).equals(b.get(i))))
        { notEquals = false;
    	  break; 
        }
      }
      if (comp != null) 
        b.setComparator(comp);
      return notEquals;
    }
	@Override
	public MySortArrayList<E> xor(MySortArrayList<E> b)
	{ Comparator<E> comp = null;
      if (!(compareComparator(b)))
	  { comp = b.getComparator();
		b.setComparator(getComparator());
	  }
	  MySortArrayList<E> a = new  MySortArrayList<E>(getComparator());
	  int current1 = 0, current2 = 0, current3 = 0; 
      while (current1 < this.size() && current2 < b.size())
      {	if (getComparator().compare(this.get(current1), 
    		 b.get(current2)) == 0)
        { current1++;
  	      current2++;         
        }
	    else 
        if (getComparator().compare(this.get(current1), 
        	 b.get(current2)) < 0) 
          a.add(current3++, this.get(current1++));	
    	else
    	  a.add(current3++, b.get(current2++));	
      }
      while (current1 < this.size())
        a.add(current3++, this.get(current1++));
      while (current2 < b.size())
        a.add(current3++, b.get(current2++));
      if (comp != null) 
        b.setComparator(comp);
      return a; 
	}
	@Override /** Convert list to array */
    public Object[] toArray()
    { Object[] b = (Object[])new Object[size()];
      for (int i=0; i < size(); i++)
        b[i] = (Object)this.get(i);
      return b;
	}
	@SuppressWarnings({ })
	@Override /** Convert list to matrix */
    public  Object[][] toMatrix(
      int numberOfRows, int numberOfColumns)
    { if (numberOfRows < 0 || numberOfColumns < 0)
    	return null;
      Object[][] m = 
    	(Object[][])new Object[numberOfRows][numberOfColumns];
      int i, j, k=0;
      for (i=0; i < numberOfRows; i++)
    	for (j=0; j < numberOfColumns; j++)
    	{ m[i][j] = (Object)this.get(k++);
    	  if (k >= size()) return m;
    	}
      return m;
    }
	@Override /** Compare comparator's */
	public boolean compareComparator(MySortArrayList<E> c)
	{ //System.out.println(this.getComparator().getClass() 
	  //  + " " + c.getComparator().getClass());
	  return (this.getComparator().getClass().
		equals(c.getComparator().getClass())); 
	}
  }