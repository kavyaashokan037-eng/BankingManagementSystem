import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public static Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/bankingdb";
        String username = "root";
        String password = "Kavya@2008";

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            return con;
        } catch (Exception e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
            return null;
        }
    }
}