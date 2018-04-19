package com.zappos.sgambhir.dbManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


//import javax.servlet.ServletContext;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Context;

import com.zappos.sgambhir.exceptions.ServerException;

public class DBManager {

	@Context
	private Configuration config;
	
	public Connection getConnection() throws ServerException{
		
		/*String mysqlUrl = (String) config.getProperty("mysqlUrl");
		String mysqlUser = (String) config.getProperty("mysqlUser");
		String mysqlPwd = (String) config.getProperty("mysqlPwd");
		
		System.out.println(mysqlUrl);
		System.out.println(mysqlUser);
		System.out.println(mysqlPwd);*/
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root","sesame" );
			return conn;
		} catch (ClassNotFoundException e) {
			throw new ServerException(e.getMessage());
		} catch (SQLException e) {
			throw new ServerException(e.getMessage());
		}
	}
}