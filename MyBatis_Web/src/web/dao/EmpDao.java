package web.dao;

import java.util.List;

import web.dto.Emp;

public interface EmpDao {

	// 전체 조회
	public List<Emp> selectAll();

	// 상세 정보 조회
	public Emp selectByEmpno(int empno);

}
