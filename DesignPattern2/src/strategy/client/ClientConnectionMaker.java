package strategy.client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import strategy.library.dao.ConnectionMaker;
import strategy.library.dao.DeptDao;

public class ClientConnectionMaker implements ConnectionMaker{

	@Override
	public Connection getConnection() {
		
		Connection connection = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe","scott","tiger");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return connection;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
