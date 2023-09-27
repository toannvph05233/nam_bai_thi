package connect_CSDL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLServerConnection {
    private static final String JDBC_URL = "jdbc:sqlserver://localhost:1433;databaseName=bai_thi";
    private static final String JDBC_USERNAME = "YourUsername";
    private static final String JDBC_PASSWORD = "YourPassword";

    // Phương thức này trả về một kết nối đến CSDL
    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
            System.out.println("Kết nối đến CSDL thành công.");
        } catch (ClassNotFoundException e) {
            System.out.println("Không tìm thấy driver JDBC.");
            e.printStackTrace();
        }
        return connection;
    }


}
