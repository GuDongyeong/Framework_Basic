package templateMethod.library.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import templateMethod.library.vo.Dept;

public abstract class DeptDao {
	
	//�θ�Ŭ�������� �⺻���� ������ �帧�� �����
	//�� ����� �Ϻθ� �߻�޼���� �����
	//�ڽ�Ŭ�������� ����� ������ �� �ֵ��� �ϴ� ����
	
	public void insertDept(Dept dept){
		
		Connection connection = null;
		PreparedStatement pstm = null;
		
		try {
			connection = getConnection();
			pstm = connection.prepareStatement("insert into dept(deptno, dname, loc)"
					+ " values(?,?,?)");
			
			pstm.setInt(1, dept.getDeptNo());
			pstm.setString(2, dept.getdName());
			pstm.setString(3, dept.getLoc());
			
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstm.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public Dept selectDept(int deptno){
		
		Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Dept dept = null;
		
		try {
			connection = getConnection();
			pstm = connection.prepareStatement(
					"select deptno, dname, loc from dept where deptno = ?");

			pstm.setInt(1, deptno);
			rs = pstm.executeQuery();
			
			if(rs.next()) {
				dept = new Dept();
				dept.setDeptNo(rs.getInt(1));
				dept.setdName(rs.getString(2));
				dept.setLoc(rs.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstm.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return dept;
	}
	
	public abstract Connection getConnection(); 
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
