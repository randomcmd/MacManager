package ConnectToDatabase;

import Settings.Settings;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.LinkedList;
import Debug.*;

public class ConnectToDatabase {

    LinkedList<LinkedList<String>> finalList; //tHIS IS LIST TO UPLOAD
    boolean hatgeklappt = false;
    private static String url = "jdbc:mysql://myadmin.ngr.bplaced.net:3306/ngr_macfilter?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";


    public ConnectToDatabase() {


    }

    public void insert(LinkedList<LinkedList<String>> list) {
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
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Settings");
            alert.showAndWait();

            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, Settings.dbUsername, Settings.dbPassword);
            System.out.println("Verbunden");
        } catch (Exception e) {
            System.out.println(e);
        }

        //System.out.print(list)a;

        for (LinkedList<String> strings : list) {
            for (int i = 0; i <= (strings.size() + 1); i++) {
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
                            sql = "INSERT INTO " + Settings.dbTablename + "(Kurs, Name, Vorname, MAC, Grund) VALUES('" + kurs + "','" + nName + "','" + vName + "','" + mac + "','" + grund + "')";
                            statement = con.prepareStatement(sql);

                            statement.executeUpdate(sql);
                            hatgeklappt = true;

                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();


                    }

                }
            }


        }
    }
}












