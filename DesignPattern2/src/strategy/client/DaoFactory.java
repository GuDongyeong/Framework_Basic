package strategy.client;

import strategy.library.dao.ConnectionMaker;
import strategy.library.dao.DeptDao;

public class DaoFactory {
	
	public DeptDao createDeptDao() {
		ConnectionMaker pclassConnectionMaker 
		= new ClientConnectionMaker();
	
		DeptDao deptDao = new DeptDao(pclassConnectionMaker);
		return deptDao;
	}
	
	
	
	
	
	
	
}
