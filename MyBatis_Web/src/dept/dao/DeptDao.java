package dept.dao;

import java.util.List;

import dept.dto.Dept;

public interface DeptDao {

	// 부서 전체 조회
	public List<Dept> selectAll();

	// 부서 상세 정보 조회
	public Dept selectDept(Dept dept);

	// 부서 정보 삽입
	public void insertDept(Dept dept);

}
