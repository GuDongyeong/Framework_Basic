package strategy.library.dao;

import java.sql.Connection;

public interface ConnectionMaker {
	
	public Connection getConnection(); 

}
