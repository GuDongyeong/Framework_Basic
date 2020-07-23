package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.Emp;
import web.service.EmpService;
import web.service.EmpServiceImpl;

/**
 * Servlet implementation class EmpDetailController
 */
@WebServlet("/emp/detail")
public class EmpDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private EmpService empService = new EmpServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		System.out.println("/emp/detail [GET]");
		
		// 파라미터 처리
		int empno = empService.getParam(req);
		
//		System.out.println(empno);
		
		// 사원 상세 정보
		Emp emp = empService.getDetail(empno);
		
		// 상세 정보 request 영역에 추가
		req.setAttribute("emp", emp);
		
		// VIEW 지정 및 forward
		req.getRequestDispatcher("/WEB-INF/views/emp/detail.jsp").forward(req, resp);
	
	}

}
