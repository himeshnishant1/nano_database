import java.io.File;

class delete_table
{
    public String delete(String t_path)
    {
        System.out.println(t_path);
        File file=new File(t_path);
        if(file.exists()){
            if(file.delete())
            {
                return "Deletion Successfull";
            }
            else
            {
                return "Deletion Unsuccessfull";
            }
        }
        else
        {
            return "table not found";
        }
    }
}