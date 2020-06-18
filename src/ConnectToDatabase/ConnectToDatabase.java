package ConnectToDatabase;
import java.sql.*;
public class ConnectToDatabase {
    public ConnectToDatabase(){
    database();
    }
    public static void database() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://myadmin.ngr.bplaced.net:3306/ngr", "ngr", "ngrSecret");

        }
        catch (Exception e) {System.out.println(e);}
    }
}
