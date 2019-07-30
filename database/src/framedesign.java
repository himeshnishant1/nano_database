import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
class framedesign extends Frame
{
    
    File f1;
    String query="";
    String path="";
    String table_path="";
    String b1="";
    int ex=0;
    TextField cmd_input;
    TextArea terminal;
    framedesign()throws IOException
    {
        terminal=new TextArea("Welcome to DataBase\nThis is still under development but still u can try some queries.\n",20,20,TextArea.SCROLLBARS_BOTH);
        terminal.setBounds(8,50,382,300);
        terminal.setEditable(false);
        add(terminal);
        cmd_input=new TextField();
        cmd_input.setBounds(8,356,375,30);
        add(cmd_input);
        
        cmd_input.addKeyListener(new KeyAdapter()
        {
           @Override
           public void keyPressed(KeyEvent ke)
           {
              if(ke.getKeyCode()==KeyEvent.VK_ENTER)
              {    
                   System.out.println(cmd_input.getText());
                   for(int i=0;i<cmd_input.getText().length();i++)
                   {
                       char ch=cmd_input.getText().charAt(i);
                       if(ch!=' ')
                       {
                           query+=ch;
                       }
                       //create datbase
                       if(query.equalsIgnoreCase("createdatabase"))
                       {
                            ex=1;
                            b1="create";
                            cmd_input.setText(null);
                            terminal("create database \n name?\n");
                            break;
                        }//create database end
                       
                       //create database name
                        else if("create".equals(b1))
                        {
                            path="C:\\Users\\Himesh Maurya\\Documents\\database\\"+cmd_input.getText();
                            if(new File(path).exists())
                            {
                                terminal(cmd_input.getText()+" already Exists.\n");
                                cmd_input.setText(null);
                            }
                            else{
                                new File(path).mkdir();
                                cmd_input.setText(null);
                                terminal(path+" is created.\n");}
                            b1="";
                            break;
                        }//create database name end
                        
                        //database list
                       else if(query.equalsIgnoreCase("listdatabases"))
                       {
                            ex=1;
                            terminal(" list databases\n");
                            terminal(Arrays.toString(new File("C:\\Users\\Himesh Maurya\\Documents\\database\\").list())+"\n");
                            cmd_input.setText(null);
                            break;
                       }//database list end
                       
                       //select database
                       else if(query.equalsIgnoreCase("use"))
                       {
                           ex=1;
                           path="C:\\Users\\Himesh Maurya\\Documents\\database\\"+cmd_input.getText().substring((i+2),cmd_input.getText().length())+"\\";
                           if(new File(path).exists())
                           {
                               terminal(cmd_input.getText().substring((i+2),cmd_input.getText().length())+" selected.\n");
                               cmd_input.setText(null); 
                               b1="database selected";
                           }
                           else
                           {
                               terminal(cmd_input.getText().substring((i+2),cmd_input.getText().length())+" does not exists.\n");
                               cmd_input.setText(null);
                           }
                           break;
                       }//select database end
                       
                       //create table
                       else if(query.equalsIgnoreCase("createtable"))
                       {
                            ex=1;
                            if(b1.equals("database selected"))
                            {
                                table_path=path+cmd_input.getText().substring((i+2),cmd_input.getText().indexOf('('))+".txt";
                                
                                try {
                                        String variables=cmd_input.getText().substring(cmd_input.getText().indexOf('(')+1,cmd_input.getText().lastIndexOf(')'));
                                        String return_value=new create_table().initial(table_path,variables);
                                        terminal(table_path+"\n");
                                        if(return_value==null)
                                            terminal("table created.\n");
                                        else
                                            terminal(return_value+"\n");
                                    }
                                 catch (IOException ex) {
                                    Logger.getLogger(framedesign.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                //b1="";
                                cmd_input.setText(null);
                                table_path=table_path.substring(0,table_path.lastIndexOf("\\")+1);
                            }
                            else
                            {
                                terminal("database not selected.\n");
                            }
                            break;
                        
                       }//table create end
                       
                       //insert data
                       else if(query.equals("insertINTO"))
                       {
                            ex=1;
                            if(b1.equals("database selected"))
                            {
                                    String table_data="";
                                    String table_name="";
                                    int count1;
                                    //loop starts
                                    for(count1=cmd_input.getText().lastIndexOf(' ')+1;;count1++)
                                    {
                                        char ch1=cmd_input.getText().charAt(count1);
                                        if(ch1=='(')
                                        {
                                            break;
                                        }
                                        else
                                        {
                                            table_name+=ch1;
                                        }
                                    }//loop ends
                                    table_name+=".txt";
                                    terminal(table_name+'\n');
                                    table_path=path+table_name;
                                    terminal(table_path+'\n');
                                    //loop starts
                                    for(count1=cmd_input.getText().indexOf('(')+1;count1<cmd_input.getText().indexOf(')');count1++)
                                    {
                                        table_data+=cmd_input.getText().charAt(count1);
                                    }//loop ends
                                    terminal(table_data+"\n");
                                    try{
                                        if(new File(table_path).exists())
                                        {
                                            String return_value=new insert().insert_data(table_path,table_data);
                                            if(return_value==null)
                                                terminal("data insertion completed\n.\n");
                                            else
                                                terminal(return_value+"\n");
                                        }
                                        else
                                            terminal("Table Does not exists\n");}
                                    catch (IOException ex) {
                                        Logger.getLogger(framedesign.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                cmd_input.setText(null);
                            }
                            else
                            {
                                terminal("database not selected\n");
                            }
                       }//insert ends
                       
                       //display a table
                       else if(query.equalsIgnoreCase("display"))
                       {
                           ex=1;
                           if(b1.equals("database selected"))
                           {
                               String table_name="";
                               table_name=path+cmd_input.getText().substring(cmd_input.getText().indexOf(" ")+1);
                               table_name+=".txt";
                               if(new File(table_name).exists())
                               {
                                  try(FileReader fr=new FileReader(table_name);BufferedReader br=new BufferedReader(fr))
                                  {
                                      String line;
                                      String datatype="";
                                      String length="";
                                      String len_tmp="";
                                      display_table dt=new display_table();
                                      for(int count=0;(line=br.readLine())!=null;count++)
                                      {
                                          switch (count) {
                                                case 0:
                                                    datatype=line;
                                                    break;
                                                case 1:
                                                    length=line;
                                                    len_tmp=length;
                                                    break;
                                                default:
                                                    String display=dt.display_table(count,line,len_tmp);
                                                    if(display!=null)
                                                    {
                                                        terminal(display+"\n");
                                                    }    
                                          } 
                                      }
                                  } catch (FileNotFoundException ex) {
                                       Logger.getLogger(framedesign.class.getName()).log(Level.SEVERE, null, ex);
                                   } catch (IOException ex) {
                                       Logger.getLogger(framedesign.class.getName()).log(Level.SEVERE, null, ex);
                                   }
                               }
                               else
                               {
                                   terminal("Table not found\n");
                               }
                           }
                           else
                            {
                                terminal("database not selected\n");
                            }
                           cmd_input.setText(null);
                       }//display ends
                       
                       //change data of table
                       else if(query.equals("change"))
                       {
                           ex=1;
                           if(b1.equals("database selected"))
                           {
                               String table_name="";
                               for(int count_query=0;count_query<cmd_input.getText().length();count_query++)
                               {
                                   char count_ch=cmd_input.getText().charAt(count_query);
                                   if(count_ch!=' '){
                                       table_name+=count_ch;
                                   }
                                   else
                                   {
                                        if(table_name.equals("in"))
                                        {
                                            table_name=path;
                                            count_query++;
                                            char ch_t='\0';
                                            while((ch_t=cmd_input.getText().charAt(count_query++))!=' ')
                                            {
                                                table_name+=ch_t;
                                            }
                                            table_name+=".txt";
                                            if(new File(table_name).exists())
                                            {
                                                change_data ct=new change_data();
                                                String get_results;
                                                try {
                                                    get_results=ct.change(cmd_input.getText().substring(count_query),table_name);
                                                    if(get_results!=null)
                                                    {
                                                        terminal(get_results+"\n");
                                                    }
                                                    else
                                                    {
                                                        terminal("data modification successfull\n");
                                                    }
                                                } catch (IOException ex) {
                                                    Logger.getLogger(framedesign.class.getName()).log(Level.SEVERE, null, ex);
                                                }
                                   
                                            }
                                            else
                                            {
                                                terminal("table not found\n");
                                            }
                                            break;
                                       }
                                       else
                                        {
                                            table_name="";
                                        }
                                   }
                               }
                           }
                           else
                           {
                               terminal("database not selected\n");
                           }
                           cmd_input.setText(null);
                       }
                       //end of change data of table
                       
                       //start of SELECT statement
                       else if(query.equals("SELECT"))
                       {
                           ex=1;
                           if(b1.equals("database selected"))
                           {
                                String table_name="";
                               for(int count_query=0;count_query<cmd_input.getText().length();count_query++)
                               {
                                   char count_ch=cmd_input.getText().charAt(count_query);
                                   if(count_ch!=' '){
                                       table_name+=count_ch;
                                   }
                                   else
                                   {
                                        if(table_name.equals("in"))
                                        {
                                            table_name=path;
                                            count_query++;
                                            char ch_t='\0';
                                            while((ch_t=cmd_input.getText().charAt(count_query++))!=' ')
                                            {
                                                table_name+=ch_t;
                                            }
                                            table_name+=".txt";
                                            if(new File(table_name).exists())
                                            {
                                                change_data ct=new change_data();
                                                String get_results;
                                                try {
                                                    get_results=ct.change(cmd_input.getText().substring(count_query),table_name);
                                                    if(get_results!=null)
                                                    {
                                                        terminal(get_results+"\n");
                                                    }
                                                    else
                                                    {
                                                        terminal("data modification successfull\n");
                                                    }
                                                } catch (IOException ex) {
                                                    Logger.getLogger(framedesign.class.getName()).log(Level.SEVERE, null, ex);
                                                }
                                   
                                            }
                                            else
                                            {
                                                terminal("table not found\n");
                                            }
                                            break;
                                       }
                                       else
                                        {
                                            table_name="";
                                        }
                                   }
                               }
                           }
                           else
                           {
                               terminal("database not selected\n");
                           }
                           cmd_input.setText(null);
                       }
                       //end of SELECT statement
                       
                       else if(query.equals("deletetable"))
                       {
                           ex=1;
                           if(b1.equals("database selected"))
                           {
                               String table_to_delete=path;
                               table_to_delete += cmd_input.getText().substring(i+2)+".txt";
                               delete_table dt=new delete_table();
                               terminal(dt.delete(table_to_delete)+"\n");
                           }
                           else
                           {
                               terminal("database not selected\n");
                           }
                           cmd_input.setText(null);
                       }//end of delete a table
                       
                       //Not a valid Query
                       if((i==cmd_input.getText().length()-1)&&(ex!=1)&&(cmd_input.getText()!=null))
                       {
                           terminal(cmd_input.getText()+" : Query is not framed yet.\n");
                       }
                   }
                   query="";
                   ex=0;
              }
           }
        });
        
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent we)
            {
                System.exit(0);
            }
        });
        setSize(400,400);
        setLayout(null);
        setVisible(true);
    } 
    
    void terminal(String s)
    {
        terminal.setText(terminal.getText()+s);
    }
}

class testframedesign
{
    public static void main(String args[])throws IOException
    {
        framedesign fd = new framedesign();
    }
}