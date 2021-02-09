package Lec5nov17;

public abstract class GeometricObjectWithComparable
       implements  Comparable<Object>
{ private String color = "white";
  private boolean filled;
  private java.util.Date dateCreated;
  /** Construct a default geometric object */
  protected GeometricObjectWithComparable()
  { dateCreated = new java.util.Date();
  }
  /** Construct a geometric object with color and filled value */
  protected GeometricObjectWithComparable(
	String color, boolean filled)
  { dateCreated = new java.util.Date();
    this.color = color;
    this.filled = filled;
  }
  /** Return color */
  public String getColor()
  { return color;
  }
  /** Set a new color */
  public void setColor(String color)
  { this.color = color;
  }
  /** Return filled. Since filled is boolean,
   *  the get method is named isFilled */
  public boolean isFilled()
  { return filled;
  }
  /** Set a new filled */
  public void setFilled(boolean filled)
  { this.filled = filled;
  }
  /** Get dateCreated */
  public java.util.Date getDateCreated()
  { return dateCreated;
  }
  /** Return a string representation of this object */
  public String toString()
  { return "created on " + dateCreated + "\ncolor: " + color +
      " and filled: " + filled;
  }
  /** implements compareTo method */
  public int compareTo(Object o)
  { if (this.getArea() < ((GeometricObjectWithComparable)o).getArea()) 
	  return -1;
    else if (this.getArea() == ((GeometricObjectWithComparable)o).getArea())  
    	   return 0;
         else   return 1;
  }
  /** Abstract method getArea */
  public abstract double getArea();
  /** Abstract method getPerimeter */
  public abstract double getPerimeter();
}
