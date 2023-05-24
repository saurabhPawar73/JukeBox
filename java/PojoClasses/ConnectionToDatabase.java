package PojoClasses;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionToDatabase {
    public Connection getConnectionDatabase () {

        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox", "root", "sprootpassword");
            System.out.println("Connected to Database");
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }
}
