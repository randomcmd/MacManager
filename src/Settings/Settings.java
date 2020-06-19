package Settings;


import Debug.DEBUGTYPE;
import Debug.Debug;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class Settings {

    public static String settingsPath = "settings.properties";
    public static String helpPath = "help.pdf";
    public static int macColumn;
    public static String csvFieldSeperator;

    Properties properties;

    public Settings() {

        importSettings();

    }

    /**
     * importSettings() imports Settings from file to static values
     */
    public void importSettings()
    {
        try {
            properties = new Properties();
            properties.load(new FileInputStream(settingsPath));
            Debug.Log("Importing " + settingsPath,0,DEBUGTYPE.SUCCESS);

            macColumn = Integer.parseInt(properties.getProperty("macColumn"));
            Debug.Log("macColumn = " + macColumn,1, DEBUGTYPE.DETAIL);
            csvFieldSeperator = properties.getProperty("csvFieldSeperator");
            Debug.Log("csvFieldSeperator = " + csvFieldSeperator,1, DEBUGTYPE.DETAIL);
        }
        catch (Exception ex) {
            Debug.Log("Error loading " + Settings.settingsPath,0,DEBUGTYPE.ERROR);
        }
    }

    public void setProperty(String property, String value)
    {
        properties.setProperty(property,value);
    }

    public void saveSettings()
    {
        try {properties.store(new FileOutputStream(Settings.settingsPath), null);}
        catch(Exception e) {Debug.Log("Could not save " + Settings.settingsPath,0,DEBUGTYPE.ERROR);}
    }

    public static void openFile(String filename) {
        try {
            if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                String cmd = "rundll32 url.dll,FileProtocolHandler " + filename;
                Runtime.getRuntime().exec(cmd);
            } else {
                Desktop.getDesktop().edit(new File(filename));
            }
        }
        catch(Exception e){Debug.Log("Could not open " + filename,0,DEBUGTYPE.ERROR);}
    }
}
