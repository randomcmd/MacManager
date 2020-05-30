package MacExport;

import java.io.PrintWriter;

public class MacExport {

    public static void saveStringArrayToFile(Object[] localarrayString)
    {
        try
        {
            PrintWriter pr = new PrintWriter("file.txt");

            for (int i=0; i<localarrayString.length ; i++)
            {
                pr.println(localarrayString[i]);
            }
            pr.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("No such file exists.");
        }
    }
}
