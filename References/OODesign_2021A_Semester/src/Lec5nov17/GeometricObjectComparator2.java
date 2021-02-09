package Lec5nov17;

import java.util.Comparator;
public class GeometricObjectComparator2 
    implements Comparator<GeometricObjectWithComparable>  

{ public int compare(GeometricObjectWithComparable o1, 
		      GeometricObjectWithComparable o2)
  { double area1 = o1.getArea();
    double area2 = o2.getArea();
	// descending order
    if (area1 < area2)        return 1;
    else if (area1 == area2)  return 0;
    else   return -1;
  }
}
