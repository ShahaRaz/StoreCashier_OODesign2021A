package Lec4nov10;

import java.io.*;
public class DetectEndOfFile
{ public static void main(String[] args) throws IOException
  { DataOutputStream output = 
      new DataOutputStream(new FileOutputStream("test.dat"));
    DataInputStream input1 = 
      new DataInputStream(new FileInputStream("test.dat"));
    DataInputStream input2 = 
      new DataInputStream(new FileInputStream("test.dat"));
	try
    { output.writeDouble(4.5);
      output.writeDouble(43.25);
      output.writeDouble(3.2);
      output.close();
      while (true)
      { System.out.println(input1.readDouble());
      }
    }
    catch (EOFException ex)
    { input1.close();
      System.out.println("All data read, first time.");
    }
    catch (IOException ex)
    { ex.printStackTrace();
    }
	 while (input2.available() != 0)
     { System.out.println(input2.readDouble());
     }
	 input2.close();
	 System.out.println("All data read, second time.");
  }
}
