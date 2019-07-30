import java.io.*;
class display_table
{
    public String display_table(int i,String line,String len_tmp)throws IOException
    {
            String row="";
            String datatype="";
                    if(i==2){
                        row=line;
                        String sub_row="";
                        String data="";
                        for(int count=0;count<row.length();count++)
                        {
                            char ch=row.charAt(count);
                            if(ch!=';')
                                sub_row+=ch;
                            else
                            {
                                int len=Integer.parseInt(len_tmp.substring(0,len_tmp.indexOf(';')));
                                if(len>sub_row.length()){
                                for(int j=sub_row.length();j<=len;j++)
                                    sub_row+=" ";}
                                len_tmp=len_tmp.substring(len_tmp.indexOf(";")+1);
                                sub_row+=" ";
                                data+=sub_row;
                                sub_row="";
                            }
                        }
                        return data+"\n------------------------";
                    }
               if(i>2)
               {
                        String sub_line="";
                        String data="";
                        for(int count=0;count<line.length();count++)
                        {
                            char ch=line.charAt(count);
                            if(ch!=';')
                                sub_line+=ch;
                            else
                            { 
                                int len=Integer.parseInt(len_tmp.substring(0,len_tmp.indexOf(';')));
                                if(len>=sub_line.length()){
                                for(int j=sub_line.length();j<=len;j++){
                                    sub_line+=" ";}}
                                else
                                {
                                    sub_line=sub_line.substring(0,len+1);
                                }
                                len_tmp=len_tmp.substring(len_tmp.indexOf(";")+1);
                                sub_line+=" ";
                                data+=sub_line;
                                sub_line="";
                            }
                        }
                        return data;
                                       
            }
        return null;
    }
}