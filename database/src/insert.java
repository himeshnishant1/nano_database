import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
class insert
{
    String path_table;
    
    public void write1(String data)throws IOException
    {
        try (FileWriter fw = new FileWriter(path_table,true)) {
            fw.write(data);
        }
    }
    public String insert_data(String path_table,String data)throws IOException
    {
        this.path_table=path_table;
        data+=",";
        String datatype="";
        String length="";
        
        try(FileReader fr = new FileReader(path_table);BufferedReader br=new BufferedReader(fr))
        {
            String line="";
            while((line=br.readLine())!=null)
            {
                line=line.replaceAll(";",",");
                if(line.equals(data))
                {
                    return "data already exists.";
                }
            }
        }
        try ( FileReader fr = new FileReader(path_table)) {
            BufferedReader br=new BufferedReader(fr);
            datatype=br.readLine();
            length=br.readLine();
            String sub_data="";//stores sub data
            String sub_type="";//stores sub datatype
            String sub_len="";//stores sub length
            //loop starts
            for(int count=0;count<data.length();count++)
            {
                char ch=data.charAt(count);
                if(ch!=',')
                {
                    sub_data+=ch;
                }
                else
                {
                    sub_type=datatype.substring(0,datatype.indexOf(';'));
                    datatype=datatype.substring(datatype.indexOf(';')+1);
                    sub_len=length.substring(0,length.indexOf(';'));
                    length=length.substring(length.indexOf(';')+1);
                    switch(sub_type)
                    {
                        case "int":
                            if(!(new check().checkInt(sub_data)))
                            {
                                return "\nInvalid format input";
                            }
                            break;
                        case "long":
                            if(!(new check().checkLong(sub_data)))
                            {
                                return "\nInvalid format input";
                            }
                            break;
                        case "float":
                            if(!(new check().checkFloat(sub_data)))
                            {
                                return "\nInvalid format input";
                            }
                            break;
                        case "double":
                            if(!(new check().checkDouble(sub_data)))
                            {
                                return "\nInvalid format input";
                            }
                            break;
                        case "char":
                            break;
                        case "string":
                            break;
                        default:
                            return "\ninvalid datatype re-check create_table()";
                    }
                    if(sub_data.length()<=Integer.parseInt(sub_len))
                    {
                        write1(sub_data+";");
                        sub_data="";
                    }
                    else
                    {
                        return "\nlength limit exceed.";
                    }
                }
            }//loop ends
            try(FileWriter fw=new FileWriter(path_table,true);BufferedWriter bw=new BufferedWriter(fw))
            {
                bw.newLine();
            }
        }//inner try ends
        return null;
    }
}//class ends