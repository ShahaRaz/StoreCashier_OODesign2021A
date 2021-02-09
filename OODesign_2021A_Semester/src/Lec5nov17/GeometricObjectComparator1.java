package Lec5nov17;

import java.util.Comparator;
public class GeometricObjectComparator1 
      implements Comparator<GeometricObject>, java.io.Serializable  
// java.io.Serializable is not essential here   
{ private static final long serialVersionUID = 1L;
  public int compare(GeometricObject o1,  GeometricObject o2)
  { double area1 = o1.getArea();
    double area2 = o2.getArea();
	// descending order
    if (area1 < area2)        return 1;
    else if (area1 == area2)  return 0;
    else   return -1;
  }
}
