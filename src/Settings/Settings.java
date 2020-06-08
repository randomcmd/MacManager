package Settings;


import Debug.*;

import java.io.FileInputStream;
import java.util.Properties;

public class Settings {

    public static int macColumn;
    public static String csvFieldSeperator;
    public static Boolean autocorrection;

    Properties properties;

    public Settings() {

        try {
            properties = new Properties();
            properties.load(new FileInputStream("settings.properties"));
            Debug.Log(">> Importing settings.properties");

            macColumn = Integer.parseInt(properties.getProperty("macColumn"));
            Debug.Log("macColumn = " + macColumn,1, DEBUGTYPE.DETAIL);
            csvFieldSeperator = properties.getProperty("csvFieldSeperator");
            Debug.Log("csvFieldSeperator = " + csvFieldSeperator,1, DEBUGTYPE.DETAIL);
            autocorrection = Boolean.getBoolean(properties.getProperty("autocorrection"));
            Debug.Log("autocorrection = " + autocorrection,1, DEBUGTYPE.DETAIL);
        }
        catch (Exception ex) {
            System.out.println("!! Error loading settings file");
        }

    }
}
