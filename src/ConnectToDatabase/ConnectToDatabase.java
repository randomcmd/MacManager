package ConnectToDatabase;

import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.LinkedList;
import Debug.*;

public class ConnectToDatabase {

    LinkedList<LinkedList<String>> finalList; //tHIS IS LIST TO UPLOAD
    private static String url = "jdbc:mysql://myadmin.ngr.bplaced.net:3306/ngr_macfilter?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static String user = "ngr";
    private static String pas = "ngrSecret";
    private static String dname = "Test1";



    public ConnectToDatabase() {


    }

    public void insert(LinkedList<LinkedList<String>> list){
        Connection con = null;
        PreparedStatement statement = null;
        String Kurs = null;
        String Nname = null;
        String Vname = null;
        String MAC = null;
        String Grund = null;
        String sql = null;


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pas);
            System.out.println("Verbunden");
        } catch (Exception e) {
            System.out.println(e);
        }

        //System.out.print(list);

       for(LinkedList<String> strings : list) {
            for(int i = 0; i <= (strings.size() +1); i++) {
                if (i == 0) {
                    Kurs = strings.get(i);
                }
                if (i == 1) {
                    Nname = strings.get(i);
                }
                if (i == 2) {
                    Vname = strings.get(i);
                }
                if (i == 3) {
                    MAC = strings.get(i);
                }
                if (i == 4) {
                    Grund = strings.get(i);
                }
                try {
                    if (i == (strings.size() +1)) {
                        sql = "INSERT INTO "+dname+"(Kurs, Name, Vorname, MAC, Grund) VALUES('"+Kurs+"','"+Nname+"','"+Vname+"','"+MAC+"','"+Grund+"')";
                        statement = con.prepareStatement(sql);
                        //statement.setString(1, dname);
                        /*statement.setString(1, Kurs);
                        statement.setString(2, Nname);
                        statement.setString(3, Vname);
                        statement.setString(4, MAC);
                        statement.setString(5, Grund);*/
                        statement.executeUpdate(sql);

                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }


       }

            


    }



    public void setListReference(LinkedList<LinkedList<String>> localList) {finalList = localList;}

}












