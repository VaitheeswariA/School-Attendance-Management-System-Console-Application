package db_connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnect {
	public DataBaseConnect() {
		try {
	
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/School_db", "root", "@Jjack007");
			System.out.println("hai");
		} catch (SQLException e) {
			System.out.println("failed");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DataBaseConnect connect = new  DataBaseConnect();

	}

}