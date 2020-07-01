package ConnectToDatabase;

import Settings.Settings;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.LinkedList;
import Debug.*;

public class ConnectToDatabase {


    public static boolean hatgeklappt = false;
    private final static String url = "jdbc:mysql://myadmin.ngr.bplaced.net:3306/ngr_macfilter?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";


    public void insert(LinkedList<LinkedList<String>> list) {
        Debug.Log("Connecting to database",0,DEBUGTYPE.SUCCESS);
        Connection con = null;
        PreparedStatement statement = null;
        String kurs = null;
        String nName = null;
        String vName = null;
        String mac = null;
        String grund = null;
        String sql = null;
        int size = list.size();



        if(Settings.dbUsername.isEmpty()|| Settings.dbPassword.isEmpty()|| Settings.dbDatabasename.isEmpty() || Settings.dbTablename.isEmpty())
        {
            Debug.Log("Invalid Settings",1,DEBUGTYPE.DETAIL);
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Settings");
            alert.showAndWait();

            return;
            //falls die Settings nicht richtig ausgefüllt wurden
        }

        if(list == null || list.isEmpty()) {Debug.Log("Data is empty",1,DEBUGTYPE.ERROR);return;}


        try {
            Debug.Log("Connecting...",1,DEBUGTYPE.DETAIL);
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, Settings.dbUsername, Settings.dbPassword);
            Debug.Log("Connected",1,DEBUGTYPE.DETAIL);
            //Verbindung zu Datenbank
        } catch (Exception e) {
            Debug.Log("Could not connect...",1,DEBUGTYPE.ERROR);
            System.out.println(e);
        }

        for (LinkedList<String> strings : list) { //Für jede äußere Liste
            for (int i = 0; i <= (strings.size() + 1); i++) { //Jedes Element der inneren Liste wird in String gespeichert
                switch (i) {
                    case 0 -> {
                        kurs = strings.get(i);
                    }
                    case 1 -> {
                        nName = strings.get(i);
                    }
                    case 2 -> {
                        vName = strings.get(i);
                    }
                    case 3 -> {
                        mac = strings.get(i);
                    }
                    case 4 -> {
                        grund = strings.get(i);
                    }
                }
                if (strings.size() + 1 == i) {
                    try {
                        if (i == (strings.size() + 1)) {
                            Debug.Log("Running SQL statement",1,DEBUGTYPE.DETAIL);
                            sql = "INSERT INTO " + Settings.dbTablename + "(Kurs, Name, Vorname, MAC, Grund) VALUES('" + kurs + "','" + nName + "','" + vName + "','" + mac + "','" + grund + "')"; //Sql statement für die Datenbank
                            statement = con.prepareStatement(sql);

                            statement.executeUpdate(sql); //asuführen des Staements
                            hatgeklappt = true;
                            Debug.Log("Inserted data into table",1,DEBUGTYPE.DETAIL);

                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                        Debug.Log("Could not insert data into table",1,DEBUGTYPE.ERROR);

                    }

                }
            }


        }
    }
}












