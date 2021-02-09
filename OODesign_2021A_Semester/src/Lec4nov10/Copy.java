package Lec4nov10;

import java.io.*;
public class Copy
{ /** Main method
     @param args[0] for source file  (choose Copy.java)
      @param args[1] for target file (choose Copy1.java) */
  public static void main(String[] args) throws IOException
  { if (args.length != 2)
    { // Check command-line parameter usage
      System.out.println("Usage: java Copy sourceFile targetfile");
      System.exit(0);
    }
    File sourceFile = new File(args[0]);
    if (!sourceFile.exists())
    { // Check if source file exists
       System.out.println("Source file "+args[0]+" not exist");
       System.exit(0);
    }
    File targetFile = new File(args[1]);
    if (targetFile.exists())
    { // Check if target file exists
      System.out.println("Target file "+args[1]+" already exists");
      System.exit(0);
    }
    // Create an input stream
    BufferedInputStream input = 
        new BufferedInputStream(new FileInputStream(sourceFile));
    // here you can use:
    //FileInputStream input = (new FileInputStream(sourceFile));
    // Create an output stream
    BufferedOutputStream output = 
      new BufferedOutputStream(new FileOutputStream(targetFile));
    // here you can use:
    //FileOutputStream output = (new FileOutputStream(targetFile));
    // Continuously read a byte from input and write it to output
    int r; int numberOfBytesCopied = 0;
    while ((r = input.read()) != -1)
    { output.write(r);  // you can use output.write(r);  
      numberOfBytesCopied++;
    }
    // Close streams
    input.close();
    output.close();
    // Display the file size
    System.out.println(numberOfBytesCopied + " bytes copied");
  }
}
