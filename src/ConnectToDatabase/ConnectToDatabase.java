package ConnectToDatabase;

import Settings.Settings;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.LinkedList;
import Debug.*;

public class ConnectToDatabase {

    LinkedList<LinkedList<String>> finalList; //tHIS IS LIST TO UPLOAD
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


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, Settings.dbUsername, Settings.dbPassword);
            System.out.println("Verbunden");
        } catch (Exception e) {
            System.out.println(e);
        }

        //System.out.print(list);

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

                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();


                    }

                }
            }


        }
    }
}












