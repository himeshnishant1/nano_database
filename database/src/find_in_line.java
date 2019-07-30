class find_in_line
{
    public boolean find(String str,String match)
    {
        String sub_str="";
        //System.out.println(str);
        //System.out.println(match);
        for(int i=0;i<match.length();i++)
        {
            sub_str+=" ";
        }
        for(int i=0;i<str.length();i++)
        {
            sub_str=sub_str.substring(1)+str.charAt(i);
            //System.out.println(sub_str);
            if(sub_str.equals(match))
            {
                return true;
            }
        }
        return false;
    }
}