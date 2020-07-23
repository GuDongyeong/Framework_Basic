package selectKey.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import selectKey.dto.Member;
import selectKey.service.MemberService;
import selectKey.service.MemberServiceImpl;

@WebServlet("/mybatis/selectKey")
public class SelectKeyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberService memberService = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println("/mybatis/selectKey [GET]");
		
		// view 지정
		req.getRequestDispatcher("/WEB-INF/views/member/join.jsp").forward(req, resp);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println("/mybatis/selectKey [POST]");
		
		// 파라미터 처리
		Member member = memberService.getParam(req);
		
//		System.out.println(member);
		
		// 멤버 정보 삽입
		memberService.insertMember(member);
		
		req.setAttribute("member", member);
		
		// 포워드
		req.getRequestDispatcher("/WEB-INF/views/member/selectKeyResult.jsp").forward(req, resp);
		
	}

}
