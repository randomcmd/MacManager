package Settings;


import Debug.DEBUGTYPE;
import Debug.Debug;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class Settings {

    public static String settingsPath = "settings.properties";
    public static String helpPath = "help.pdf";
    public static int macColumn;
    public static String csvFieldSeperator;

    Properties properties;

    public Settings() {

        try {
            properties = new Properties();
            properties.load(new FileInputStream(settingsPath));
            Debug.Log("Importing settings.properties" + settingsPath,0,DEBUGTYPE.SUCCESS);

            macColumn = Integer.parseInt(properties.getProperty("macColumn"));
            Debug.Log("macColumn = " + macColumn,1, DEBUGTYPE.DETAIL);
            csvFieldSeperator = properties.getProperty("csvFieldSeperator");
            Debug.Log("csvFieldSeperator = " + csvFieldSeperator,1, DEBUGTYPE.DETAIL);
        }
        catch (Exception ex) {
            Debug.Log("Error loading settings file",0,DEBUGTYPE.ERROR);
        }

    }

    public static void openFile(String filename) {
        try {
            if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                String cmd = "rundll32 url.dll,FileProtocolHandler " + filename;
                Runtime.getRuntime().exec(cmd);
            } else {
                Desktop.getDesktop().edit(new File(settingsPath));
            }
        }
        catch(Exception e){Debug.Log("Could not open " + filename,0,DEBUGTYPE.ERROR);}
    }
}
