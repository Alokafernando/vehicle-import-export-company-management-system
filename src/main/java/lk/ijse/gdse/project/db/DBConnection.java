package lk.ijse.gdse.project.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection dBConnection;
    private Connection connection;

    private DBConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/supermarketfx",
                                                "root",
                                            "1234"
        );
    }

    public static DBConnection getInstance() throws SQLException, ClassNotFoundException {
        if(dBConnection == null){
            dBConnection = new DBConnection();
        }
        return dBConnection;
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        return connection;
    }
}
