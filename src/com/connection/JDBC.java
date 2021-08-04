package com.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
	
	String Url = "jdbc:mysql://localhost:3306/addressbookservice" ;
	String userName = "root" ;
	String password = "Kuldeep725@";
	
	public Connection connection() {
		
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(Url,userName , password);
			return connection;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

}
