import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Color;

public class GUIdirBrowser extends JFrame implements ActionListener, Runnable
{
  public static final int WIDTH = 500;
  public static final int HEIGHT = 700;
  public static final int NUMCOLUMNS = 40;

  protected JTextField pathField;
  protected JTextField subDirField;
  protected JTextField locExtField;
  protected JTextField locNameField;
  protected JTextField concatFileField;
  protected JTextArea resultField;
  protected JButton clearBtn;
  protected DirBrowser test;


  protected String[] dir;
  //protected String[] fName;
  //protected String[] fExt;

  //protected boolean log;

  public GUIdirBrowser()
  {
    super("Directory Browser");

    getContentPane().setBackground(Color.BLACK);
    test = new DirBrowser();
    //log = this.log;
    setSize(WIDTH, HEIGHT);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new FlowLayout());


    clearBtn = new JButton("Clear");
    add(clearBtn);

    pathField = new JTextField("Enter Directory to list: ",NUMCOLUMNS);
    add(pathField);
    //pathField.setLayout(new FlowLayout());

    subDirField = new JTextField("Enter path for full directory+subdirectories: ",NUMCOLUMNS);
    add(subDirField);

    locNameField = new JTextField("Enter (SearchDirectory, FileName))",NUMCOLUMNS);
    add(locNameField);
//    locNameField.setLayout(new FlowLayout());

    locExtField = new JTextField("Enter (SearchDirectory,Extension)",NUMCOLUMNS);
    add(locExtField);

    concatFileField = new JTextField("Enter 2 readfiles, 1 writefile, and path: (f1,f2,f3,path)",NUMCOLUMNS);
    add(concatFileField);

    resultField = new JTextArea(30,43);

    JScrollPane scrolledText = new JScrollPane(resultField);
      scrolledText.setHorizontalScrollBarPolicy(
                  JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
      scrolledText.setVerticalScrollBarPolicy(
                  JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    add(scrolledText);

    ClearBtnListener clearBtnListener = new ClearBtnListener();
    clearBtn.addActionListener(clearBtnListener);

    PathListener pathListener = new PathListener();
    pathField.addActionListener(pathListener);

    SubDirListener subDirListener = new SubDirListener();
    subDirField.addActionListener(subDirListener);

    LocNameListener locNameListener = new LocNameListener();
    locNameField.addActionListener(locNameListener);

    LocExtListener locExtListener = new LocExtListener();
    locExtField.addActionListener(locExtListener);

    ConcatListener concatListener = new ConcatListener();
    concatFileField.addActionListener(concatListener);
  }

  private class PathListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      // e.getCommandAction() is path and list dir if subDir notchecked
      DirBrowser a = new DirBrowser();
      String[] dirArray = a.listDir(e.getActionCommand(),false,false,"");
      int i = 0;
      String conc ="";
      while(dirArray[i] != null)
      {
        resultField.append(dirArray[i]+"\n");
        //conc = cont + ", "+dirArray[i];
        System.out.println(dirArray[i]);
        i++;
      }


    }
  }

  private class SubDirListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      DirBrowser a = new DirBrowser();
      String[] dirArray = a.listSubDir(e.getActionCommand(),true,false,"");
      int i = 0;
      String conc ="";
      while(dirArray[i] != null)
      {
        resultField.append(dirArray[i]+"\n");
        //conc = cont + ", "+dirArray[i];
        System.out.println(dirArray[i]);
        i++;
      }

    }
  }

  private class LocNameListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      int i = 0;
      DirBrowser db = new DirBrowser();
      int a = e.getActionCommand().indexOf(",");
      String directory = e.getActionCommand().substring(0,a);
      String fileName = e.getActionCommand().substring(a+1);
      String[] fnames = db.locate(fileName,directory,false);
      while(fnames[i] != null)
      {
        resultField.append(fnames[i]+"\n");
        i++;
      }

    }
  }

  private class LocExtListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      int i = 0;
      DirBrowser db = new DirBrowser();
      int a = e.getActionCommand().indexOf(",");
      String directory = e.getActionCommand().substring(0,a);
      String ext = e.getActionCommand().substring(a+1);
      String[] fExt = db.locate(ext,directory,false);
      while(fExt[i] != null)
      {
        resultField.append(fExt[i]+"\n");
        i++;
      }
    }
  }

  private class ConcatListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      DirBrowser db = new DirBrowser();
      int a = e.getActionCommand().indexOf(",");
      String file1 = e.getActionCommand().substring(0,a);
      String temp = e.getActionCommand().substring(a+1);
      int b = temp.indexOf(",");
      String file2 = temp.substring(0,b);
      String temp2 = temp.substring(b+1);
      int c = temp2.indexOf(",");
      String file3 = temp2.substring(0,c);
      String path = temp2.substring(c+1);
      System.out.println(path);
      try{
        db.concat(path,file1,file2,file3);
      }
      catch(Exception oops)
      {
        oops.getMessage();
      }

    }
  }
  private class ClearBtnListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      resultField.setText(null);
      subDirField.setText("Enter path for full directory+subdirectories:");
      pathField.setText("Enter Directory to list: ");
      locNameField.setText("Enter (SearchDirectory, FileName))");
      locExtField.setText("Enter (SearchDirectory,Extension)");
      concatFileField.setText("Enter 2 readfiles, 1 writefile, and path: (f1,f2,f3,path)");
    }
  }






  public void actionPerformed(ActionEvent e)
  {
    startThread();
  }

  public void run()
  {
  }

  public void startThread()
  {
    Thread theThread = new Thread(this);
    theThread.start();
  }

}
