/*
We’ll boole working with file IO and exception handling by building a simple text-based
interface for browsing the file system.
When your program starts it should ask the user if they would like to dump results to a log file
IN ADDITION to standard out. If so, your program should prompt for the name of the log file. If
the file already exists, the contents should be overwritten.

The program will then display (6) options to the user:
1) List the contents of a directory provided by the user
2) List the contents of a directory provided by the user as well as all of its sub directories. (hint:
use recursion)
3) Locate a file with a given name.
4) Locate files with a given file extension
5) Concatenate the contents of 2 files whose names are provided by the user and output the result
to a third file (name also provided by the user)
6) Exit

*/

import java.util.Scanner;
import javax.swing.JFrame;

public class DirBrowserDriver
{
  public static void main(String[] args)
  {
    try{

      boolean log = false;    //logfile prompt and creation should just be a gui run option.
      Scanner keyboard = new Scanner(System.in);
      boolean boole = true;
      String logFile;
      int option;
      DirBrowser test;
      System.out.println("Create a log file? (y/n)");
      logFile = keyboard.nextLine();
      if(logFile.toLowerCase().equals("y") || logFile.toLowerCase().equals("yes"))
      {
        log = true;
        System.out.println("Please enter the log file name: ");
        logFile = keyboard.nextLine();
        if(logFile.indexOf(".txt") < 0)
        {
          logFile +=".txt";
        }
        test = new DirBrowser("",logFile);
      }
      else
      {
        test = new DirBrowser();
      }

      GUIdirBrowser gui = new GUIdirBrowser();
      gui.setVisible(true);


/* test area
    int i =0;
    //String[] a = test.listDir("C:\\Users\\David\\Documents\\CPSC_Courses\\CS231\\Jung_Assignment6",true,false,"udon");
    //String[] a = test.locate(".java","C:\\Users\\David\\Documents\\CPSC_Courses\\CS231\\Jung_Assignment6",false);
    while(a[i] != null)
    {
      System.out.println(a[i]);
      i++;
    }
*/

  }


    catch(Exception e)
    {
      System.out.println(e.getMessage());
    }

}
}
