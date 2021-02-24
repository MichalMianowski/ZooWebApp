package zoo_webapp.zoomanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private static String url = "jdbc:oracle:thin:@ora3.elka.pw.edu.pl:1521:ora3inf";
    private static String user = "USERNAME";
    private static String password = "PASSWORD";


    public static Connection connectionDB;


    public static void connect(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url,user,password);
            System.out.println("Connected!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionDB = connection;

    }

    public static Connection getConnectionDB() {
        return connectionDB;
    }

}
