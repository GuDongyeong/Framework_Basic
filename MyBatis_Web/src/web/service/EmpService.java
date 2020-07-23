package web.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import web.dto.Emp;

public interface EmpService {

	/**
	 * 전체 사원 조회
	 * @return
	 */
	public List<Emp> getList();

	/**
	 * 사원 상세 정보
	 * @param empno
	 * @return
	 */
	public Emp getDetail(int empno);

	// 파라미터 처리
	public int getParam(HttpServletRequest req);

}
