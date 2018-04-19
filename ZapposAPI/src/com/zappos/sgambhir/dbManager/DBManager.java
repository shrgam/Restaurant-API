package com.zappos.sgambhir.dbManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


//import javax.servlet.ServletContext;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Context;

import com.zappos.sgambhir.exceptions.ServerException;

/**
 * @author Shriya
 *
 */
public class DBManager {

	@Context
	private Configuration config;
	
	/**
	 * This method is making a 
	 * connection with the local database
	 * stored in mysql server
	 * @return
	 * @throws ServerException
	 */
	public Connection getConnection() throws ServerException{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "","" );
			return conn;
		} catch (ClassNotFoundException e) {
			throw new ServerException(e.getMessage());
		} catch (SQLException e) {
			throw new ServerException(e.getMessage());
		}
	}
}