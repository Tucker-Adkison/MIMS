import java.sql.*;

public class Database {
    private Connection conn;
    //TODO create methods to add and get from the datbase
    public Database(String db_name, String username, String password) throws SQLException{
        conn = DriverManager.getConnection(db_name, username, password);
        conn.close();
    }
}


