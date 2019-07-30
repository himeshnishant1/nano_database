import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
public class test11 {

    public static void main(String args[]) throws FileNotFoundException, IOException
    {
        String datatype="";
        String length="";
        String query="roll_no,name FROM stu";
        
        query=query.trim();
        File f1=new File("E:\\stu.txt");
        FileReader fw=new FileReader(f1);
        BufferedReader br=new BufferedReader(fw);
        String column_names=query.substring(0,query.indexOf("FROM"));
        column_names=column_names.replaceAll(" ","");
        column_names+=",";
        String table_name=query.substring(query.lastIndexOf(' ')+1);
        String line="";
        List<String> list_names=new ArrayList<>();
        List<String> list_no=new ArrayList<>();
        List<String> len1=new ArrayList<>();//len list
        
        
        String sub="";
        for(int i=0;i<column_names.length();i++)
        {
            char ch=column_names.charAt(i);
            if(ch==',')
            {
                list_names.add(sub);
                sub="";
            }
            else
            {
                sub+=ch;
            }
        }
        int size=0;
        for(int i=0;(line=br.readLine())!=null;i++)
        {
            switch(i) {
                case 0:
                    datatype=line;
                    break;
                case 1:
                    length=line;
                    break;   
                }
            if(i==2)
            {
                
                for(String s:list_names)
                {
                    if(!line.contains(s))
                    {
                        System.out.println("Column not found in the table!!");
                        return;
                    }
                }
                size=0;
                for(int j=0,k=0;j<line.length();j++)
                {
                    char ch=line.charAt(j);
                    if(ch==';')
                    {
                        try{
                        if(sub.equals(list_names.get(size)))
                        {
                            list_no.add(String.valueOf(k));
                            size++;
                        }
                        sub="";
                        k++;}
                        catch(IndexOutOfBoundsException e)
                        {
                            System.out.println(e);
                        }
                    }
                    else
                    {
                        sub+=ch;
                    }
                }
                size=0;
                sub="";
                for(int j=0,k=0;j<length.length();j++)
                {
                    char ch=length.charAt(j);
                    if(ch==';')
                    {
                        try{
                        if(Integer.parseInt(list_no.get(size))==k)
                        {
                            len1.add(String.valueOf(sub));
                            size++;
                        }
                        sub="";
                        k++;
                        }
                        catch(IndexOutOfBoundsException e)
                        {
                            System.out.println(e);
                        }
                    }
                    else
                    {
                        sub+=ch;
                    }
                }
                int k=0;
                String print="";
                for(String s:list_names)
                {
                    print=s;
                    if(Integer.parseInt(len1.get(k))>s.length()){
                        for(int tmp=s.length();tmp<=Integer.parseInt(len1.get(k));tmp++)
                            print+=" ";
                    }
                    print+=" ";
                    System.out.print(print);
                    k++;
                }
                System.out.println();
                for(int tmp=0;tmp<print.length();tmp++)
                    System.out.print("--");
                System.out.println();
            }
            else if(i>2)
            {
                sub="";
                for(int j=0,k=0,k2=0,l=0;j<line.length();j++)
                {
                    char ch=line.charAt(j);
                    if(ch==';')
                    {
                        try{
                        if(Integer.parseInt(list_no.get(k))==l)
                        {
                            k++;
                            String print=sub;
                            if(Integer.parseInt(len1.get(k2))>sub.length()){
                                for(int tmp=sub.length();tmp<=Integer.parseInt(len1.get(k2));tmp++){
                                    print+=" ";
                                }
                            }
                            else
                            {
                                print=print.substring(0,Integer.parseInt(len1.get(k2)));
                            }
                            print+="  ";
                            System.out.print(print);
                            k2++;
                        }
                        sub="";
                        l++;
                        }
                        catch(IndexOutOfBoundsException e)
                        {
                            System.out.println(e);
                        }
                    }
                    else
                    {
                        sub+=ch;
                    }
                    
                }
                System.out.println();
            }
        }
    }
}
