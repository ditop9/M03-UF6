package trackBasicJDBC;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLConnection {
    private static Connection con;
    public static Connection getConnection() {
        if (con == null) {
            try {
                Class.forName("org.sqlite.JDBC");
                con = DriverManager.getConnection("jdbc:sqlite:Chinook_Sqlite.sqlite");
            } catch (Exception e) {
                System.out.println();
            }
        }
        return con;
    }
}
