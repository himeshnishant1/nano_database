import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
class create_table
{ 
    String path;
    int j=0;
    public void write1(String name,String datatype,String length)throws IOException
    {
        File f1=new File(path);
        if(f1.createNewFile()){
        FileWriter fw=new FileWriter(f1);
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                bw.write(datatype);
                bw.newLine();
                bw.write(length);
                bw.newLine();
                bw.write(name);
                bw.newLine();
            }
        }
    }
    @SuppressWarnings("empty-statement")
    public String initial(String path,String query)throws IOException
    {
        this.path=path;
        query+=",";
        int i=0;
        String sub="";
        String datatype="";
        String length="";
        
        System.out.println(query);
        int count=0;
        while(!"".equals(query))
        {
            char ch=query.charAt(count);
            if(ch==' '){
                sub+=";";
                query=query.substring(count+1);
                length+=query.substring(query.indexOf('(')+1,query.indexOf(')'))+';';
                String tmp=query.substring(0,query.indexOf('('))+';';
                if(("int;".equals(tmp))||("long;".equals(tmp))||("float;".equals(tmp))||("double;".equals(tmp))||("string;".equals(tmp))||("char;".equals(tmp)))
                {
                    datatype+=tmp;
                }
                else
                {
                    return "Unsupported datatype";
                }
                query=query.substring(query.indexOf(',')+1);
                i++;
                count=0;
            }
            else
            {
                count++;
                sub+=ch;
            }
        }
        write1(sub,datatype,length);
        return null;
    }
    
}