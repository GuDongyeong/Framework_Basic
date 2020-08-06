package web.member.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import web.member.model.service.MemberService;
import web.member.model.vo.Member;

// Controller 어노테이션으로 해당 객체를 WebApplicationContext에 등록
// @RequestParam, @RequestMapping 등 Controller를 위한 어노테이션을 사용할 수 있다
@Controller
//RuquestMapping을 클래스에 적용하면 해당 클래스의 모든 메소드에 적용되는 url을 지정할 수 있다. 메소드에 붙는 url의 앞 경로를 의미
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
		
	// 1. 메소드에만 RequestMapping - url 전체를 모두 기입
//	@RequestMapping(value="/member/join.do")
//	public void join() {
//		// void : 컨트롤러에서 return 타입이 void라면 요청온 url과 동일한 경로의 jsp를 찾아서 사용자에게 보내준다.
//		
//	}
	
	// 2. 클래스와 메소드에 모두 RequestMapping 어노테이션 붙임
//	@RequestMapping("/join.do")
//	public String join() {
//		// String이 반환형이면 String으로 반환되는 문자열이 jsp의 경로가 된다
//		return "/member/join";
//	}
	
	// 3
	// 만약 method 속성을 작성하지 않으면 get, post 둘다 허용한다
	@RequestMapping(value="/join.do", method=RequestMethod.GET)
	public ModelAndView join() {
		//ModelAndView : model 객체에 값을 담고 viewName을 보내고 싶은 jsp의 경로로 지정
		
		ModelAndView mav = new ModelAndView();
		
		// jsp 경로 지정
		mav.setViewName("/member/join");
		return mav;
	}
	
	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	public void login() {
//		return "/member/login";
	}
	
	//------회원가입----------------------------------------------------------

	@RequestMapping(value="/joinimple.do")
	public ModelAndView joinImple(
			@ ModelAttribute Member m, HttpServletRequest req) {
		
		// ContextPath 경로값을 받아온다
		String root = req.getContextPath();
		System.out.println(root);
		ModelAndView mav = new ModelAndView();
		
		int res = memberService.insertMember(m);
		
		if( res > 0 ) {
			mav.addObject("alertMsg", "회원가입에 성공하였습니다");
			mav.addObject("url", root + "/member/login.do");
		}else {
			mav.addObject("alert", "회원가입에 실패하였습니다.");
			mav.addObject("url", root + "/member/join.do");
		}
//		System.out.println(map);
//		System.out.println(m);
		
		mav.setViewName("/common/result");
		return mav;
	}
	
	//------로그인-------
	@RequestMapping(value="/loginimple.do", method=RequestMethod.POST)
	public ModelAndView loginImpl(
			@RequestParam Map<String, Object> map, HttpSession session, HttpServletRequest req) {
		
		// view를 지정하고 값을 전달할 객체
		ModelAndView mav = new ModelAndView();
		
		// 결과값 객체
		Member member = memberService.selectMember(map);
		
		if( member != null) {
			// member 값을 잘 꺼내온 경우
			session.setAttribute("logInInfo", member);
			mav.addObject("alertMsg", "로그인에 성공했습니다.");
			mav.addObject("url", req.getContextPath() + "/member/mypage.do");
			mav.setViewName("common/result");
		}else {
			// 로그인에 실패한 경우
			mav.addObject("alertMsg", "로그인에 실패했습니다.");
			mav.addObject("url", req.getContextPath() + "/member/login.do");
			mav.setViewName("common/result");
		}
		return mav;
	}
	
	//----마이페이지------
	@RequestMapping(value="/mypage.do", method=RequestMethod.GET)
	public void mypage() {}
	
	//----마이페이지 수정 ----
//	@RequestMapping(value="/modify.do", method=RequestMethod.POST)
//	public ModelAndView mypageUpdate(@ModelAttribute Member m, HttpServletRequest req) {
//		// ModelAttribute 어노테이션 생략 가능
//		HttpSession session = req.getSession();
//		
//		ModelAndView mav = new ModelAndView();
//
//		// member 정보 수정
//		int res = memberService.updateMember(m);
//		
//		if( res > 0) {
//			session.setAttribute("logInInfo", m);
//			mav.addObject("alertMsg", "내 정보가 수정되었습니다.");
//			mav.addObject("url", req.getContextPath() + "/member/mypage.do");
//		}else {
//			mav.addObject("alertMsg", "수정 실패하였습니다.");
//			mav.addObject("url", req.getContextPath() + "/member/mypage.do");
//			
//		}
//		mav.setViewName("common/result");
//		return mav;
//	}
	
	
	//-----마이페이지 수정(선생님)------
	@RequestMapping("/modify.do")
	public String mypageUpdate(Member m, HttpSession session, Model model) {
		// ModelAttribute 어노테이션 생략 가능
		
		// Model 객체는 ModelAndView 와 다르게 파라미터로 받아올 수 있다.
		
		// 세션에서 user 정보 값 받아오기
		Member sessionMember = (Member)session.getAttribute("logInInfo");
		
		// jsp 에서 받아온 파라미터에 세션에 저장된 id 값 저장해주기 - 사용자가 조작할 수 없는 session에서 가져오는 것이 보안상 문제가 없다
		m.setUserId(sessionMember.getUserId());
		
		int res = memberService.updateMember(m);
		
		if(res > 0) {
			// 회원 수정에 성공한 경우
			// Model.addAttribute(K, V) : view에 전달할 데이터 추가
			model.addAttribute("alertMsg", "회원 정보 수정에 성공했습니다");
			model.addAttribute("url", "mypage.do");
			session.setAttribute("logInInfo", m);
		}else {
			model.addAttribute("alertMsg", "회원정보 수정에 실패했습니다.");
			model.addAttribute("url", "mypage.do");
		}
		
		return "common/result";
	}
	
	//--- 탈퇴 ---
//	@RequestMapping(value="/leave.do", method=RequestMethod.POST)
//	public ModelAndView memberLeave(@ModelAttribute Member m, HttpServletRequest req) {
//		
//		ModelAndView mav = new ModelAndView();
//		
//		// member 탈퇴
//		int res = memberService.leaveMember(m);
//		
//		
//		if( res > 0) {
//			mav.addObject("alertMsg", "탈퇴되었습니다.");
//			mav.addObject("url", req.getContextPath() + "/member/login.do");
//		}else {
//			mav.addObject("alertMsg", "탈퇴 실패하였습니다");
//			mav.addObject("url", req.getContextPath() + "/member/mypage.do");
//		}
//		
//		mav.setViewName("common/result");
//		return mav;
//	}
	
	// 탈퇴 선생님
	@RequestMapping(value="/leave.do", method=RequestMethod.POST)
	public String memberLeave(@ModelAttribute Member m, HttpServletRequest req, HttpSession session, Model model) {
		
		String userId = ((Member)session.getAttribute("logInInfo")).getUserId();
		
		
		// member 탈퇴
		int res = memberService.leaveMember(userId);
		
		
		if( res > 0) {
			model.addAttribute("alertMsg", "탈퇴되었습니다.");
			model.addAttribute("url", "login.do");

			String urlPath = req.getServerName() + ":" + req.getServerPort() + req.getContextPath();
			memberService.leaveMailSending(m, urlPath);
			
			session.invalidate();
		}else {
			model.addAttribute("alertMsg", "탈퇴 실패하였습니다");
			model.addAttribute("url", "mypage.do");
		}
		return "common/result";
	}
	
	
	@RequestMapping(value="/idcheck.do")
	@ResponseBody // 메소드에 지정하면 메소드에서 리턴하는 값을 viewResolver를 거쳐서 출력하지 않고 Http Response Body에 직접 쓰게 된다
	public String idCheck(String userId) {
		
		System.out.println("idCheck 메소드가 호출되었습니다.");
		System.out.println(userId);
		
		int res = memberService.selectId(userId);
		
		if( res > 0) {
			return userId;
		}else {
			return "";
		}
		
	}
	
	// 카카오 API 도서 검색
	@RequestMapping("/search")
	public String search() {
		return "member/kakaoSearch";
	}
	
	// 회원가입 이메일 인증
	@RequestMapping("/joinemailcheck.do")
	public ModelAndView joinEmailCheck(@ModelAttribute Member member, HttpServletRequest req) {
		
		// View 지정 객체
		ModelAndView mav = new ModelAndView();
		
		String urlPath = req.getServerName() + ":" + req.getServerPort() + req.getContextPath();
		
		memberService.mailSending(member, urlPath);
		
		mav.addObject("alertMsg", "이메일로 확인 메일이 발송되었습니다.");
		mav.addObject("url", "login.do");
		mav.setViewName("common/result");
		
		return mav;
	}
	
	

}
