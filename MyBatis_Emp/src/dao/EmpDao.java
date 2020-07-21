package dao;

import java.util.List;

import dto.Emp;

public interface EmpDao {

	// 모든 사원정보 출력
	public List<Emp> selectAll();

	// 사원정보 입력
	public void insertEmp(Emp emp1);

	// 사원번호로 사원정보 출력
	public Emp selectByEmpNo(Emp emp);

	// 사원번호로 사원정보 삭제
	public void deleteByEmpNo(Emp emp);

}
