package Settings;


import Debug.DEBUGTYPE;
import Debug.Debug;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;


//To Add
//Username, Passwort, Url, Datenbankname, Tablename

public class Settings {

    //Local Settings
    public static String settingsPath = "settings.properties";
    public static String helpPath = "help.pdf";
    public static int macColumn;
    public static String csvFieldSeperator;

    //Database Settings
    public static String dbUsername;
    public static String dbPassword;
    public static String dbDatabasename;
    public static String dbTablename;

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

            //Local
            macColumn = Integer.parseInt(properties.getProperty("macColumn"));
            Debug.Log("macColumn = " + macColumn,1, DEBUGTYPE.DETAIL);

            csvFieldSeperator = properties.getProperty("csvFieldSeperator");
            Debug.Log("csvFieldSeperator = " + csvFieldSeperator,1, DEBUGTYPE.DETAIL);

            //Database
            dbUsername = properties.getProperty("dbUsername");
            Debug.Log("dbUsername = " + dbUsername,1, DEBUGTYPE.DETAIL);

            dbPassword = properties.getProperty("dbPassword");
            Debug.Log("dbPassword = " + dbPassword,1, DEBUGTYPE.DETAIL);

            dbDatabasename = properties.getProperty("dbDatabasename");
            Debug.Log("dbDatabasename = " + dbDatabasename,1, DEBUGTYPE.DETAIL);

            dbTablename = properties.getProperty("dbTablename");
            Debug.Log("dbTablename = " + dbTablename,1, DEBUGTYPE.DETAIL);
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
