package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBConnection {
    private static java.sql.Connection connection;
    private static final String USER = "Yana";
    private static final String PASSWORD = "yana12345";
    private static final String URL = "jdbc:mysql://localhost:3306/BANK?useUnicode=yes&useSSL=false";

    public static boolean createConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = connection.prepareStatement("DROP DATABASE IF EXISTS BANK");
            stmt.execute();
            stmt = connection.prepareStatement("CREATE DATABASE BANK");
            stmt.execute();
            stmt = connection.prepareStatement("USE BANK");
            stmt.execute();
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean createTables() {
        String userTable = "CREATE TABLE user " +
                "(id VARCHAR(100) NOT NULL, " +
                "login VARCHAR(100) NOT NULL, " +
                "password VARCHAR(100) NOT NULL, " +
                "address VARCHAR(100) NOT NULL, " +
                "phone VARCHAR(100) NOT NULL, " +
                "PRIMARY KEY (id))";

        String accountTable = "CREATE TABLE account " +
                "(id VARCHAR(100) NOT NULL, " +
                "client_id VARCHAR(100) NOT NULL, " +
                "amount DECIMAL(100) NOT NULL, " +
                "acc_code VARCHAR(3) NOT NULL, " +
                "PRIMARY KEY (id))";

        String operationTable = "CREATE TABLE operation " +
                "(id VARCHAR(100) NOT NULL, " +
                "date VARCHAR(100) NOT NULL, " +
                "currency VARCHAR(3) NOT NULL, " +
                "from_account VARCHAR(100) NOT NULL, " +
                "to_account VARCHAR(100) NOT NULL, " +
                "amount DECIMAL(100) NOT NULL, " +
                "amount_before DECIMAL(100) NOT NULL, " +
                "amount_after DECIMAL(100) NOT NULL, " +
                "PRIMARY KEY (id))";

        Connection con = DBConnection.getConnection();

        try {
            PreparedStatement stmt = con.prepareStatement(userTable);
            stmt.execute();
            stmt = con.prepareStatement(accountTable);
            stmt.execute();
            stmt = con.prepareStatement(operationTable);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
