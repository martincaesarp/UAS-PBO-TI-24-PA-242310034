package Database;
import java.sql.*;

public class ConnectionDB {
	private Connection conn;
	
	public Connection connect() throws SQLException {
		String host = "localhost:3306";
		String dbName = "inventory";
		String dbUser = "root";
		String dbPassword = "";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection conn = DriverManager.getConnection("jdbc:mysql://" + host + "/" + dbName, dbUser, dbPassword);
		return conn;		
	}
	
	public Connection close() throws SQLException {
		conn.close();
		return conn;
	}
}