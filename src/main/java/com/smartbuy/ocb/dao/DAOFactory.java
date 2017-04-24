package com.smartbuy.ocb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public abstract class DAOFactory {

	Connection conRes = null;

	public DAOFactory() {
		// TODO Auto-generated constructor stub
	}

	// Rishi - Can we make it really simple. 
	/* Connection getConnection(String param){
	 * Connection conn  = null;
	 *  if(param = 0){
	 *  	conn  = getConnectionWithJDBC();
	 *  }else{
	 *  	conn = getConnectionWithJNDI();
	 *  }
	 *  }
s	 */
	
	
	public static DAOFactory getInstance() throws Exception {
		Connection con;
		DataSource dataSource = null;
		DAOFactory instance = null;
		String url = "jdbc:mysql://localhost:3306/retail?useSSL=false";
		String user = "root";
		String password = "root";
		String driverClassName = "com.mysql.jdbc.Driver";
		if (driverClassName != null) { // Rishi - variable defined above - compared here
			try {
				Class.forName("com.mysql.jdbc.Driver"); 
			} catch (Exception e) {
				// Rishi - Exception suppressed
				e.printStackTrace();
			}
			instance = new JDBCDaoFactory(url, user, password);
		} 
			else {
					
					try {
						InitialContext initContext = new InitialContext();
						Context envContext = (Context) initContext.lookup("java:comp/env");

				// Look for data source
						dataSource = (DataSource) envContext.lookup("jdbc/retail");
						
						} catch (Exception e) {
								e.printStackTrace();
							}
					instance = new JNDIDaoFactory(dataSource);
			}
		return instance;
	}


	abstract Connection getConnection() throws Exception;
		
	public IOrderCreationDAO getOrderCreation(){
		return new OrderCreationDaoImpl(this);
	}
	//close database connection
	public void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}
	class JDBCDaoFactory extends DAOFactory {
		private String url, user, password;

		JDBCDaoFactory(String url, String user, String password) {
			this.url = url;
			this.user = user;
			this.password = password;
		}

		@Override
		Connection getConnection() throws Exception {
			return DriverManager.getConnection(url, user, password);
		}

	}

	class JNDIDaoFactory extends DAOFactory {
		private DataSource dataSource;

		JNDIDaoFactory(DataSource dataSource) {
			
			this.dataSource = dataSource;
		}
		
		@Override
		Connection getConnection() throws Exception
		{
			return dataSource.getConnection();
		}
	}

	

