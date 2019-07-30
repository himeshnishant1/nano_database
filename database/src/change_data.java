import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
class change_data
{
    public String change(String data_to_change,String path)throws IOException
    {
            data_to_change+="&&";
            File f1=new File(path);
            String tmp_path="tmp.txt";
            BufferedReader br;
            FileWriter fw;
            FileReader fr = new FileReader(f1);
            br = new BufferedReader(fr);
            fw = new FileWriter(tmp_path);
            String cond_colum_name[] = null;
            int cond_colum_data[] = null;
            int colum[]=null;
            String column_names="";
            int size=0;
            boolean found=false;
            for(int count=0;count<data_to_change.length();count++)
            {
                String line;
                int column=-1;
                for(int i=0;(line=br.readLine())!=null;i++)
                {
                    if(i==2)
                    {
                        column_names=line;
                        for(int j=0;j<line.lastIndexOf(";");)
                        {
                            column++;
                            if(data_to_change.substring(0,data_to_change.indexOf("(")).equals(line.substring(0,line.indexOf(";"))))
                            {
                                String condition=data_to_change.substring(data_to_change.indexOf("w"));
                                String find_where="";
                                int index_where=0;
                                while(!"where".equals(find_where))
                                {
                                    //System.out.println("find where ="+find_where);
                                    find_where+=condition.charAt(index_where++);
                                }
                                condition=condition.substring(index_where+1);
                                for(int l=0;l<condition.lastIndexOf("&&");l++)
                                {
                                    char ch1=condition.charAt(l);
                                    if(ch1=='&')
                                    {
                                        l++;
                                        size++;
                                    }
                                }
                                size++;
                                //System.out.println("\nsize ="+size);
                                cond_colum_name=new String[size];
                                cond_colum_data=new int[size];
                                colum=new int[size];
                                String sub_condition="";
                                //System.out.println("Condition ="+condition);
                                for(int l=0,a=0;l<condition.length();)
                                {
                                    //System.out.println("count "+count+"\ni "+i+"\nj "+j+"\nl "+l);
                                    char ch1=condition.charAt(l);
                                    switch (ch1) {
                                        case '=':
                                            cond_colum_name[a]=sub_condition;
                                            //System.out.println("cond_colum_name["+a+"] = "+cond_colum_name[a]);
                                            sub_condition="";
                                            break;
                                        case '&':
                                            cond_colum_data[a]=Integer.parseInt(sub_condition);
                                            //System.out.println("cond_colum_data["+a+"] = "+cond_colum_data[a]);
                                            a++;l++;
                                            sub_condition="";
                                            break;
                                        default:
                                            sub_condition+=ch1;
                                            //System.out.println("cond_sub = "+sub_condition);
                                            break;
                                    }
                                    l++;
                                }
                            }
                            j=line.indexOf(";");
                            line=line.substring(line.indexOf(";")+1);
                        }
                    }
                    /*for(int test=0;test<size;test++)
                    {
                    System.out.println(cond_colum_name[test]+cond_colum_data[test]);
                    }*/
                    if(i>2)
                    {
                        //System.out.println("i "+i);
                        //System.out.println("line = "+line);
                        String tmp_line=line;
                        //testing
                        int ct=0;
                        for(int j=0;j<tmp_line.lastIndexOf(";")+1;)
                        {
                            ct++;
                            //System.out.println(ct+"="+column);
                            if(ct==column)
                            {
                                //System.out.println("ct==column is true for ct = "+ct);
                                String condition=data_to_change.substring(data_to_change.indexOf("w"));
                                String find_where="";
                                int index_where=0;
                                while(!"where".equals(find_where))
                                {
                                    find_where+=condition.charAt(index_where++);
                                }
                                condition=condition.substring(index_where+1);
                                //System.out.println("condition = "+condition);
                                //System.out.println(data_to_change.substring(data_to_change.indexOf("(")+1,data_to_change.indexOf(","))+" = "+tmp_line.substring(0,tmp_line.indexOf(";")));
                                
                                if(data_to_change.substring(data_to_change.indexOf("(")+1,data_to_change.indexOf(",")).equals(tmp_line.substring(0,tmp_line.indexOf(";"))))
                                {
                                    //System.out.println("Entered");
                                    found=true;
                                    int a=0;
                                    int ctr=0;
                                    for(a=0;a<size;a++){
                                        //System.out.println(size+" > "+a);
                                        find_in_line fil=new find_in_line();
                                        if(fil.find(";"+column_names,";"+cond_colum_name[a]+";"))
                                        {
                                            //System.out.println("true");
                                            //System.out.println(cond_colum_data[a]);
                                            if(fil.find(";"+line,";"+String.valueOf(cond_colum_data[a])+";")){
                                                ctr++;
                                            }
                                        }
                                    }
                                    if(ctr==size)
                                    {
                                        String new_line=line.replace( data_to_change.substring(data_to_change.indexOf("(")+1 , data_to_change.indexOf(",")),data_to_change.substring(data_to_change.indexOf(",")+1,data_to_change.indexOf(")")) );
                                        try( BufferedWriter bw=new BufferedWriter(fw) ; FileReader fr1=new FileReader(f1) ; BufferedReader br1=new BufferedReader(fr1) )
                                        {
                                            String read_line;
                                            for(int l=0;(read_line=br1.readLine())!=null;l++)
                                            {
                                                if(l==column+1)
                                                {
                                                    bw.write(new_line);
                                                    bw.newLine();
                                                }
                                                else
                                                {
                                                    bw.write(read_line);
                                                    bw.newLine();
                                                }
                                            }
                                        }
                                    }
                                    else
                                    {
                                        return "Condition does not match";
                                    }
                                }
                            }
                            
                            j=line.indexOf(";");
                            tmp_line=tmp_line.substring(tmp_line.indexOf(";")+1);
                        }
                        if(found==false)
                        {
                            return "data not found!!";
                        }
                    }
                }
            }
            br.close();
            f1.delete();
            fw.close();
            File f2=new File(tmp_path);
            f2.renameTo(f1);
            f2.delete();
            return null;
    }
}