package dept.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dept.dao.DeptDao;
import dept.dao.DeptDaoImpl;
import dept.dto.Dept;

@WebServlet("/dept/detail")
public class DeptDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DeptDao deptDao = new DeptDaoImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 파라미터
		int deptno = 0;
		String param = req.getParameter("deptno");
		if( param != null && !"".equals(param)) {
			deptno = Integer.parseInt(param);
		}
		
		Dept dept = new Dept();
		
		dept.setDeptno(deptno);
		
		// 부서 상세 정보
		Dept res = deptDao.selectDept(dept);
		
		// MODEL 전달
		req.setAttribute("res", res);
	
		// VIEW 지정
		req.getRequestDispatcher("/WEB-INF/views/dept/detail.jsp").forward(req, resp);
	
	}

}
