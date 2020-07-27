package web.service;

import java.util.List;

import web.dto.Dept;

public interface DeptService {

	// 전체 부서 정보 조회
	public List<Dept> getList();

}
