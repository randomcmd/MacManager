package ConnectToDatabase;

import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.LinkedList;
import Debug.*;

public class ConnectToDatabase {

    public LinkedList<LinkedList<String>> list;
    private static String url = "localhost";
    //private static String url = "jdbc:mysql://myadmin.ngr.bplaced.net:3306/ngr_macfilter?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static String user = "ngr";
    private static String pas = "ngrSecret";
    public Connection con;


    public ConnectToDatabase() {
    run();


    }

    public void run(){
        checkdatabase();
        //insert(checkdatabase(), list);
        Debug.Log("mixed race couple? cringe");
    }
    public Connection checkdatabase() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pas);
            /*String sql = "INSERT INTO Test1 (Name) VALUES (8)";
                PreparedStatement statement = con.prepareStatement(sql);
                statement.executeUpdate();*/

        } catch (Exception e) {
            System.out.println(e);
        }


        return con;

    }

    public static void insert(Connection con, LinkedList<LinkedList<String>> list) {
        Debug.Log("ayoborororooro?",0,DEBUGTYPE.DETAIL);
        if (!list.isEmpty()) {

            try {
                for (LinkedList<String> sublist : list) {
                    for (String s : sublist){
                     System.out.println(s);

                    }
                }
                /*String sql = "INSERT INTO Test1 (Name) VALUES (8)";
                PreparedStatement statement = con.prepareStatement(sql);
                statement.executeUpdate();*/
            } catch (Exception e) {
                System.out.println(e);
            }
        }

         else {
            Debug.Log("butwhytho?",0,DEBUGTYPE.DETAIL);
        }


    }

}














   /* public void insert(String datpfad, Connection con) {
        String filePath = "datpfad";
        File file = new File(filePath);
        String line;
        try{
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            if(file.isFile()) {
                while((line = br.readLine()) != null){
                    String sql = "INSERT INTO Test1 (Name) values (8)";
                    PreparedStatement statement = con.prepareStatement(sql);
                    statement.executeUpdate();


                }

            }
            else System.out.println("WHY");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }*/



























    /*public void insert(String datName, Connection con){
        Scanner scan = null;
        try {
            scan = new Scanner(new File("C:\\Users\\henry\\Desktop\\yo.txt"));
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scan.hasNext()) {


            try {
                String sql = "INSERT INTO Test1 (MAC) values (scan.nextLine())";
                PreparedStatement statement = con.prepareStatement(sql);
                statement.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        scan.close();




    }*/



