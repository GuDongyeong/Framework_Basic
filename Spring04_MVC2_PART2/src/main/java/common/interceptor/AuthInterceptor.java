package common.interceptor;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

// HandlerInterceptor 를 구현해 인터페이스로 해당 클래스를 활용
public class AuthInterceptor implements HandlerInterceptor{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		
		// 사용자가 요청한 URI에 notice가 포함되어 있고 session정보에 logInInfo가 저장되어있지 않다면(로그인을 하지 않았다면)
		if(request.getRequestURI().contains("notice/") && request.getSession().getAttribute("logInInfo") == null ) {
			
			request.setAttribute("alertMsg", "비회원은 접근할 권한이 없습니다.");
			request.setAttribute("url", request.getContextPath() + "/member/login.do");
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/result.jsp");
			try {
				rd.forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			// 컨트롤러 메소드를 호출하지 않음
			return false;
		}
		
		return true;
	}

}
