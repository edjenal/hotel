package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionFactory {

	public Connection getConnection() {
		try {
			ResourceBundle bundle = ResourceBundle.getBundle("con");
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			return DriverManager.getConnection(bundle.getString("server"), bundle.getString("user"),
					bundle.getString("password"));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
