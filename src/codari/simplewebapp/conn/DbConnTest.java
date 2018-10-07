package codari.simplewebapp.conn;

import java.sql.Connection;
import java.sql.SQLException;

public class DbConnTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection con= ConnectionUtils.getConnection();
		System.out.println("=== "+con);
	}

}
