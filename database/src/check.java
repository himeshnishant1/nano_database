public class check{
    //integer
    public boolean checkInt(String str)
    {
        try{
            int num=Integer.parseInt(str);
        }
        catch(NumberFormatException e)
        {
            return false;
        }
        return true;
    }
    
    //long
    public boolean checkLong(String str)
    {
        try{
            long num=Long.parseLong(str);
        }
        catch(NumberFormatException e)
        {
            return false;
        }
        return true;
    }
    
    //float
    public boolean checkFloat(String str)
    {
        try{
            float num=Float.parseFloat(str);
        }
        catch(NumberFormatException e)
        {
            return false;
        }
        return true;
    }
    
    //double
    public boolean checkDouble(String str)
    {
        try{
            double num=Double.parseDouble(str);
        }
        catch(NumberFormatException e)
        {
            return false;
        }
        return true;
    }
    
}
