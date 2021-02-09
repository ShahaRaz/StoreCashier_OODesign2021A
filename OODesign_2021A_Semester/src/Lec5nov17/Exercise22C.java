package Lec5nov17;

import java.util.*;
import java.io.*;
public class Exercise22C
{ public static void main(String[] args)
  { if (args.length != 1)
    { System.out.println("Usage: java Exercise22C file.java");
      System.exit(0);
    } //arg[0] = Exercise22C.java (the whole path)
    // Array of all Java keywords + true + null
    String[] keywordString = {"abstract", "finally", "public",
    "boolean", "float", "return", "break", "for", "short", "byte",
    "goto", "static", "case", "if", "super", "catch", "implements",
    "switch", "char", "import", "synchronized", "class",
    "instanceof", "this", "const", "int", "throw", "continue",
    "interface", "throws", "default", "long", "transient", "do",
    "native", "try", "double", "new", "void", "else", "package",
    "volatile", "extends", "private", "while", "final",
    "protected", "true", "null"};
    Set<String> keywordSet = 
    	new HashSet<String>(Arrays.asList(keywordString));
    int count = 0;
    try
    { Scanner input = new Scanner(new File(args[0]));
      while (input.hasNext())
      { String token = input.next(); // every iteration a token
        if (keywordSet.contains(token))  count++;
      }
      System.out.println("The number of keywords in the program is "
        + count);
    }
    catch (Exception ex)
    { ex.printStackTrace();
    }
   }
 }