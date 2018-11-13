/*
We’ll continue working with file IO and exception handling by building a simple text-based
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

import java.io.File;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

public class DirBrowser
{
  private String logFile;
  private String path;
  public PrintWriter outputToLog;
  private String[] retDir;
  private int numFile;
  private String[] retSubDir;
  private int numFol;
  String[] fName;
  private int numFName;
  String[] fExt;
  private int numFExt;
  //constructor
  DirBrowser(String path, String logFile) throws FileNotFoundException
  {
     this.outputToLog = new PrintWriter(new FileOutputStream(logFile));
     this.path = path;
     this.retDir = new String[1000];
     this.retSubDir = new String[1000];
     this.fName = new String[1000];
     this.fExt = new String[1000];
     this.numFile = 0;
     this.numFol = 0;
     this.numFName = 0;
     this.numFExt = 0;
  }
  //empty constructor
  DirBrowser()
  {
    this.logFile = "";
    this.path = "/.";
    this.retDir= new String[1000];
    this.retSubDir = new String[1000];
    this.fName = new String[1000];
    this.fExt = new String[1000];
    this.numFile = 0;
    this.numFol =0;
    this.numFName = 0;
    this.numFExt = 0;
    this.outputToLog = null;
  }

  //copy constructor

  DirBrowser(DirBrowser copy)
  {
    this.logFile = copy.getLogFile();
    this.path = copy.getPath();
    this.outputToLog = copy.getOutputToLog();
    this.retDir = new String[1000];
    this.retSubDir = new String[1000];
    this.fName = new String[1000];
    this.fExt = new String[1000];
    this.numFile=0;
    this.numFol = 0;
    this.numFName = 0;
    this.numFExt = 0;
  }
  public String[] getFName()
  {
    return this.fName;
  }
  public String[] getFExt()
  {
    return this.fExt;
  }

  public void setFName(String[] fName)
  {
    int i = 0;
    while(fName[i] != null)
    {
      this.fName[i] = fName[i];
      i++;
    }
  }
  public void setFExt(String[] fExt)
  {
    int i = 0;
    while(fExt[i] != null)
    {
      this.fExt[i] = fExt[i];
      i++;
    }
  }


  public String[] getRetDir()
  {
    return this.retDir;
  }
  public void setRetDir(String[] retDir)
  {
    int i = 0;
    while(retDir[i] != null)
    {
      this.retDir[i] = retDir[i];
      i++;
    }
  }

  public String[] getRetSubDir()
  {
    return this.retSubDir;
  }

  public void setRetSubDir(String[] retSubDir)
  {
    int i = 0;
    while(retSubDir[i] != null)
    {
      this.retSubDir[i] = retSubDir[i];
      i++;
    }
  }

  public String getLogFile()
  {
    return this.logFile;
  }

  public void setLogFile(String logFile)
  {
    this.logFile = logFile;
  }

  public String getPath()
  {
    return this.path;
  }

  public void setPath(String path)
  {
    this.path = path;
  }


  public PrintWriter getOutputToLog()
  {
      return this.outputToLog;
  }

  public void setOutputToLog(PrintWriter outputToLog)
  {
    this.outputToLog = outputToLog;
  }

  /* check this again
  public Dirbrowser clone()
  {
    return new Dirbrowser (this);
  }

  */
  //equals method

  //toString method



  public String[] listDir (String path, boolean hasSubDirs, boolean log, String key)
  {
    File a = new File(path);
    String[] arr = a.list();
    String[] dirList = new String[arr.length];
    File dList[] = new File[arr.length];
    int numDir = 0;

    for (int i = 0; i < arr.length; i++)
    {
      if(arr[i].indexOf(".") < 0) //if arr[i] is a folder
      {
        dirList[numDir]=arr[i];
        numDir++;
        if(key.equals(""))
        {
        retSubDir[numFol] = "Dir "+path+" has subdirectory "+arr[i];
        numFol++;
        }
        if (log && key.equals(""))
        {
          outputToLog.println("\r\nDir "+path+" has subdirectory: "+arr[i]);
        }
      }
      else  //if arr[i] is a file
      {
        if(!key.equals(""))
        {
          if(key.indexOf(".",0) < 0) //if searching for a file by name
          {
            if((arr[i].toLowerCase()).indexOf(key.toLowerCase()) >= 0) //if file name match
            {
              fName[this.numFName] = "Dir "+path+" has file: "+arr[i];
              this.numFName++;
              if (log)
              {
                outputToLog.println("\r\nDir "+path+" has file: "+arr[i]);
              }
            }
          }
          else //searching for file by extension arr[i].endsWith("."+key)
          {
            if(("."+arr[i].toLowerCase()).indexOf(key.toLowerCase())>=0) //if extension match
            {
              fExt[this.numFExt] = "Dir "+path+" has file: "+arr[i];
              this.numFExt++;
              if (log)
              {
                outputToLog.println("\r\nDir "+path+" has file: "+arr[i]);
              }
            }
          }
        }
        else
        {
          retDir[numFile] = "Dir "+path+" has file: "+arr[i];
          numFile++;
          if (log)
          {
            outputToLog.println("\r\nDir "+path+" has file: "+arr[i]);
          }
        }
      }
    }
    if(hasSubDirs == true && numDir > 0) //if additional directories exist
    {
      for(int n =0; n < numDir; n++)
      {
        dList[n] = new File(path+"/"+dirList[n]);

        if(dList[n].isDirectory())
        {
          listDir(path+"/"+dirList[n],true,log,key);
        }
      }
    }
    else if (key.equals("") && hasSubDirs == false) //if only listing current directory
    {
      int m = 0;
      System.out.println("print dir");
      while(retDir[m] != null)
      {
        retSubDir[numFol+m]=retDir[m];
        m++;
      }
      return this.retSubDir;
    }
    String[] empty = new String[1];
    return empty; //to keep compiler happy.
  }

  public String[] listSubDir(String path, boolean hasSubDirs, boolean log, String key)
  {
    listDir(path,hasSubDirs,log,key);
    System.out.println("printSubDir");
    //want to return retDir and retSubDir combined.. we know numFile corresponds to retDir and numFol corresponds to retSubDir
    int i = 0;
    while(retDir[i] != null)
    {
      retSubDir[numFol+i] = retDir[i];
      i++;
    }
    return retSubDir;
  }

  public String[] locate(String key, String path, boolean log)
  {
    listDir(path, true, log, key);
    if(key.indexOf(".") < 0)
    {
      return this.fName;
    }
    else
    {
      return this.fExt;
    }

  }

  public String concat(String path, String f1, String f2, String f3) throws Exception
  {
    PrintWriter ostream1 = new PrintWriter(new FileOutputStream(path+"\\"+f3+".txt"));
    BufferedReader istream1 = new BufferedReader(new FileReader(path+"\\"+f1+".txt"));
    BufferedReader istream2 = new BufferedReader(new FileReader(path+"\\"+f2+".txt"));
    String ftext = "";
    String check ="";
    while(check != null)
    {
      ftext +=check;
      check = istream1.readLine();
    }
    check="";
    System.out.println(ftext+ " has been written to "+f3);
    while(check != null)
    {
      ftext += check;
      check = istream2.readLine();

    }
    ostream1.println(ftext);
    ostream1.close();
    istream1.close();
    istream2.close();
    return "concat Success!";
  }
}
