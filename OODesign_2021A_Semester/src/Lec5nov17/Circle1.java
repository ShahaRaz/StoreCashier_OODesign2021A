package Lec5nov17;

public class Circle1 extends GeometricObjectWithComparable
{ private double radius;
  public Circle1()
  {
  }
  public Circle1(double radius)
  { this.radius = radius;
  }
  /** Return radius */
  public double getRadius()
  { return radius;
  }
  /** Set a new radius */
  public void setRadius(double radius)
  { this.radius = radius;
  }
  /** Return area */
  public double getArea()
  { return radius * radius * Math.PI;
  }
  /** Return diameter */
  public double getDiameter()
  { return 2 * radius;
  }
  /** Return perimeter */
  public double getPerimeter()
  { return 2 * radius * Math.PI;
  }
  /* Print the circle info */
  public void printCircle()
  { System.out.println("The circle is created " + 
    	getDateCreated() +  " and the radius is " + radius);
  }
  //public int compareTo(Object o)
  //{ Circle1 o1 = (Circle1)o; 
	//if (this.getArea() < o1.getArea())  return -1;
    //else if (this.getArea() == o1.getArea())  return 0;
    //else   return 1;
  //}

}