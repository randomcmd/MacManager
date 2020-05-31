package MacImport;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;

public class MacImport {

    public Object[][] CSVToArray(String filename)
    {

        String[] localarrayStringUnformated;
        String[][] localarrayString;

        int count = 0;
        LinkedList<String[]> content = new LinkedList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(filename)))
        {
            String line = "";
            while ((line = br.readLine()) != null)
            {
                content.add(line.split(";"));
            }
        }
        catch (Exception e)
        {
            //Some error logging
        }

        localarrayString = new String[content.size()][5];
        for(int i = 0; i < content.size(); i++)
        {
            for(int j = 0; j < 5; j++)
            {
                localarrayString[i][j] = content.get(i)[j];
            }
        }

        return localarrayString;
    }

    public LinkedList<String> parseMACfromArray(String[][] localarrayString)
    {
        LinkedList<String> locallistString;
        locallistString = new LinkedList<String>();

        for(int i = 0; i < localarrayString.length; i++)
        {
            locallistString.add( localarrayString[i][3] );
        }

        return locallistString;
    }
}


