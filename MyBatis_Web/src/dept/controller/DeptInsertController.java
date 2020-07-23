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

@WebServlet("/dept/insert")
public class DeptInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DeptDao deptDao = new DeptDaoImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//VEIW 지정 - forward
		req.getRequestDispatcher("/WEB-INF/views/dept/insert.jsp").forward(req, resp);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// form 태그로 받은 파라미터 저장
		Dept dept = new Dept();
		
		int deptno = 0;
		String param = req.getParameter("deptno");
		if( param != null && !"".equals(param)) {
			deptno = Integer.parseInt(param);
		}
		
		dept.setDeptno(deptno);
		dept.setDname(req.getParameter("dname"));
		dept.setLoc(req.getParameter("loc"));
		
		// DB에 저장
		deptDao.insertDept(dept);
		
		// 리다이렉트
		resp.sendRedirect("/dept/list");
	
	
	}
	
}
