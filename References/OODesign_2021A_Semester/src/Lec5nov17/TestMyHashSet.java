package Lec5nov17;

public class TestMyHashSet
{ public static void main(String[] args)
  { Product[] ap = createArrayProducts();
    MySet<Product> set = new MyHashSet<Product>();
    for (Product p: ap)
     set.add(p);
    System.out.println("Elemets in HashSet\n" + set);
    System.out.println("Number of elements in HashSet: " 
          + set.size());
    System.out.println("Is \"10 Violin 1000.11f\" in HashSet? "
      + set.contains(new Product(10, "Violin", 1000.11f)));
    set.remove(new Product(22, "Piano", 7000.33f));
    System.out.println("Elemets in HashSet after remove " 
      + "\"22 Piano 7000.33f\"");
    for (Product p: set)
      System.out.println(p);
    System.out.println("Number of elements in HashSet: " 
            + set.size());
    set.clear();
    System.out.println("Elements in HashSet: " + set);
    System.out.println("Number of elements in HashSet: " 
            + set.size());
  }
  public static Product[] createArrayProducts() 
  {  Product[] ap = new Product[]
		 { new Product(10, "Violin", 1000.11f),
		   new Product(40, "Cello", 2000.22f),
		   new Product(35, "Cello", 2000.22f),
		   new Product(34, "Cello", 3000.22f),
		   new Product(33, "Cello", 2000.22f),
		   new Product(36, "Cello", 1800.22f),
	       new Product(22, "Piano", 7000.33f),
	       new Product(14, "Drums", 8000.44f),
	       new Product(99, "Java HW1", 1000.55f),
	       new Product(70, "Guitar", 5000.66f),
	       new Product(11, "Sababa Targil", 700.77f),
	       new Product(1, "Trombone", 100),
	       new Product(2, "Saxophone", 200),
	       new Product(3, "Bass Guitar", 300),
	       new Product(4, "Trumpet", 400),
	       new Product(13, "ContraBass", 300.88f)
		 };
     return ap;
  }
}
class Product implements Comparable<Product>
{  private int id;
   private String name;
   private float price;
   public Product(int id, String name, float price)
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
   /** Sets the product's price */
   public void setPrice(float price)
   {  this.price = price;
   }
   public String toString()
   {  return String.format("%3d %-13s %8.2f", id, name, price);
   }
   @Override
   public int compareTo(Product o)
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
   { if (this.compareTo((Product)o) == 0) return true;
     else return false;  
   }
   public int hashCode()
   { return this.name.hashCode();
   }
}  