import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
class test11
{
    public static void main(String args[])throws IOException
    {
        System.out.println("Test Open");
        String query="age=45 where roll_no=4";
        query+=" ";
        System.out.println("query = "+query);
        List<String> match_columns=new ArrayList<>();
        List<String> match_columns_data=new ArrayList<>();
        List<String> datatype=new ArrayList<>();
        List<String> columns=new ArrayList<>();
        List<String> columns_data=new ArrayList<>();
        String column_change="";
        String column_change_data="";
        String sub="";
        boolean check=false;
        
        File f1=new File("E:\\stu.txt");
        FileReader fr=new FileReader(f1);
        File f2=new File("tmp.txt");
        FileWriter fw=new FileWriter(f2);
        
        String line="";
        try( BufferedReader br=new BufferedReader(fr);BufferedWriter bw=new BufferedWriter(fw))
        {   
            for(int count=0;count<query.length();count++)
            {
                char ch=query.charAt(count);
                if(ch=='=')
                {
                    if(check==true)
                    {
                        match_columns.add(sub);
                    }
                    else
                    {
                        column_change=sub;
                    }
                    sub="";
                    count++;
                    while((ch=query.charAt(count))!=' ')
                    {
                        sub+=ch;
                        count++;
                    }
                    if(check==true)
                        match_columns_data.add(sub);
                    else
                        column_change_data=sub;
                    sub="";
                }
                else if(ch==' ')
                {
                    if("where".equals(sub))
                        check=true;
                    sub="";
                }
                else
                {
                    sub+=ch;
                }
            }
            if(check==false)
            {
                System.out.println("where can not be found in this query!!");
                return;
            }
            for(int count=0;(line=br.readLine())!=null;count++)
            {
                if(count==0)
                {
                    System.out.println(line);
                    bw.write(line);
                    bw.newLine();
                    sub="";
                    for(int count1=0;count1<line.length();count1++)
                    {
                        char ch=line.charAt(count1);
                        if(ch==';')
                        {
                            datatype.add(sub);
                            sub="";
                        }
                        else
                        {
                            sub+=ch;
                        }
                    }
                }
                else if(count==1)
                {
                    System.out.println(line);
                    bw.write(line);
                    bw.newLine();
                }
                else if(count==2)
                {
                    System.out.println(line);
                    bw.write(line);
                    bw.newLine();
                    sub="";
                    for(int count1=0;count1<line.length();count1++)
                    {
                        char ch=line.charAt(count1);
                        if(ch==';')
                        {
                            columns.add(sub);
                            sub="";
                        }
                        else
                        {
                            sub+=ch;
                        }
                    }
                }
                else if(count>2)
                {
                    sub="";
                    for(int count1=0;count1<line.length();count1++)
                    {
                        char ch=line.charAt(count1);
                        if(ch==';')
                        {
                            columns_data.add(sub);
                            sub="";
                        }
                        else
                        {
                            sub+=ch;
                        }
                    }
                    String write_line="";
                    int count2=0;
                    for(String s:match_columns)
                    {
                        int count1=-1;
                        check=false;
                        for(String s1:columns)
                        {
                            count1++;
                            if(s1.equals(s))
                            {
                                check=true;
                                if(columns_data.get(count1).equals(match_columns_data.get(count2)))
                                {
                                    count2++;

                                }
                            }
                        }
                        if(!check)
                        {
                            System.out.println("Column name "+s+" not found");
                            return;
                        }
                    }

                    if(count2==match_columns_data.size())
                    {
                        count2=-1;
                        for(String s2:columns)
                        {
                            count2++;
                            if(s2.equals(column_change))
                            {
                                write_line+=column_change_data+";";
                            }
                            else
                            {
                                write_line+=columns_data.get(count2)+";";
                            }
                        }
                        System.out.println(write_line);
                        bw.write(write_line);
                        bw.newLine();
                    }
                    else
                    {
                        System.out.println(line);
                        bw.write(line);
                        bw.newLine();
                    }

                    columns_data.removeAll(columns_data);
                }
            }
        }
        f1.delete();
        f2.renameTo(f1);
    }
}