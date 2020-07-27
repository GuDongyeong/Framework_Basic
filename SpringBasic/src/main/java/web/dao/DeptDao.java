package web.dao;

import java.util.List;

import web.dto.Dept;

public interface DeptDao {

	// 전체 부서 조회
	public List<Dept> selectAll();

}
