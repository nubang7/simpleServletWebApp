package codari.simplewebapp.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DerbyDbConnectionUtil {
	public static Connection getOracleConnection() throws ClassNotFoundException, SQLException {

		// Note: Change the connection parameters accordingly.
		//String hostName = "localhost";
		//String sid = "db12c";
		String userName = "redca";
		String password = "redca";

		//return getDerbyDbConnection(hostName, sid, userName, password);
		return getDerbyDbConnection(userName, password);
	}

	//public static Connection getDerbyDbConnection(String hostName, String sid, String userName, String password)
	public static Connection getDerbyDbConnection(String userName, String password)
			throws ClassNotFoundException, SQLException {

		// Class.forName("oracle.jdbc.driver.OracleDriver");
		Class.forName("org.apache.derby.jdbc.ClientDriver");
		// URL Connection for Oracle
		// Example:
		// jdbc:oracle:thin:@localhost:1521:db11g
		// jdbc:oracle:thin:@//HOSTNAME:PORT/SERVICENAME
		//String connectionURL = "jdbc:oracle:thin:@" + hostName + ":1521:" + sid;
		String connectionURL = "jdbc:derby://localhost:8133/redcaDB";
		
		Connection conn = DriverManager.getConnection(connectionURL, userName, password);
		return conn;
	}
}
