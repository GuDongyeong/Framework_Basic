package web.board.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import web.board.model.service.NoticeService;
import web.board.model.vo.Notice;
import web.member.model.vo.Member;

@Controller
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;

	@RequestMapping("/board/boardForm.do")
	public String boardForm() {
		return "board/boardForm";
	}
	
	
	@RequestMapping(value="/notice/noticeupload.do", method=RequestMethod.POST)
	public ModelAndView noticeUpload(@RequestParam List<MultipartFile> files, HttpSession session, Notice notice ) {
		// 다중파일 업로드이므로 여러 개의 MultipartFile 을 담기 위한 List 파라미터로 요청 받기
		
		
		// MultipartFile 예외처리
		//	1. 사용자가 게시글만 작성하고 파일 업로드를 하지 않은 경우
		//		사용자가 첨부한 파일이 없어도 list의 size가 1로 잡혀서 서버에 빈 파일이 쌓이게 된다.
		//		이때 첨부한 파일의 이름은 ""(공백)이다.
		
//		if(files.size() != 1 && !files.get(0).getOriginalFilename().equals("")) {
//			
//			for( MultipartFile mf : files) {
//				
//				// 파일을 저장할 경로
//				String savePath = root + "resources/upload";
//				System.out.println("savePath : " + savePath);
//				
//				// transferTo 메소드의 매개변수에 넣어줄 빈 파일 객체를 저장할 경로 + 사용자가 등록한 이름
//				File fileData = new File(savePath + "/" + mf.getOriginalFilename());
//				
//				try {
//					// 빈 파일 객체에 사용자가 등록한 파일을 덮어씌우는 메소드 
//					mf.transferTo(fileData);
//				} catch (IllegalStateException | IOException e) {
//					e.printStackTrace();
//				}
//				
//			}
//			
//		}
		
		// -> Service 단으로 이동
		
//		System.out.println(notice);

		ModelAndView mav = new ModelAndView();
		
		// 파일 업로드를 위한 절대 경로 반환(실제 C 드라이브 경로)
		String root = session.getServletContext().getRealPath("/");

		// 로그인 정보 session에서 받아오기
		Member sessionMember = (Member)session.getAttribute("logInInfo");
		
		// 로그인한 상태라면
		if( sessionMember != null) {
			notice.setUserId(sessionMember.getUserId());
		}else {
			notice.setUserId("비회원");
		}
		
		
		// 게시판 입력
		noticeService.insertNotice(notice, files, root);
		
		// redirect:url - url로 리다이랙트 해준다
		mav.setViewName("redirect:noticelist.do");
		
		return mav;
	}
	
	@RequestMapping("/notice/noticelist.do")
	public ModelAndView noticeList(@RequestParam(required=false, defaultValue="1") int cPage) {
		
		ModelAndView mav = new ModelAndView();
		// 한번에 보여줄 페이지수
		int cntPerPage = 10;
		
		Map<String, Object> map = noticeService.selectNoticeList(cPage, cntPerPage);
		
		mav.addObject("paging", map.get("paging"));
		mav.addObject("noticeData", map);
		
		mav.setViewName("board/boardList");
		
		return mav;
	}
	
	@RequestMapping("/notice/noticedetail.do")
	public ModelAndView noticeDetail(int nIdx) {
		
		ModelAndView mav = new ModelAndView();
		
		Map<String, Object> map = noticeService.selectNoticeDatail(nIdx);
		
		// 예외처리
		// 해당 게시글이 존재하는지 여부 판단, Map 안의 notice 객체가 null인지 여부로 판단
		if( map.get("notice") != null) {
			mav.setViewName("board/boardView");
			mav.addObject("data", map);
		}else {
			mav.addObject("alertMsg", "해당 게시물이 존재하지 않습니다.");
			mav.addObject("url", "board/boardList");
			mav.setViewName("common/result");
		}
		
		return mav;
	}
	
	// 파일 다운로드
//	@RequestMapping("notice/noticedownload.do")
//	public void noticeDownload(HttpServletResponse res, HttpSession session, String ofname, String rfname) {
//		// response header 지정을 위한 response, 파일 경로 지정을 위한 session
//		// afname 사용자가 올린 파일 이름, String rfname 서버에 저장된 파일 이름
//		
//		// C부터 시작하여 resources/upload 폴터까지의 절대 경로를 반환
//		String readFolder = session.getServletContext().getRealPath("/resources/upload");
//		
//		// file 객체 생성
//		File downFile = new File(readFolder + "/" + rfname);
//		
//
//		OutputStream downOut = null;
//		BufferedInputStream bis = null;
//		
//		// 응답 대상과 연결되는 OutputStream
//		try {
//			// header 는 output 위에 지정
//			res.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(ofname, "UTF-8"));
//
//			downOut = res.getOutputStream();
//			
//			// downFile에 저장된 파일 정보를 읽어온다
//			bis = new BufferedInputStream(new FileInputStream(downFile));
//			
//			int read = 0;
//			while( (read = bis.read()) != -1) {
//				downOut.write(read);
//				downOut.flush();
//			}
//			
//			// response header
//			// Content-Dispostition : 브라우저 화면에 출력할 지, 다운받을지 결정
//			// attachment : download
//			// file name : download 받을 떄 파일명
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//	}
	
	// 스프링 FileSystemResource를 활용하여 파일 다운로드
	@RequestMapping("notice/noticedownload.do")
	@ResponseBody // 메소드에 지정하면 메소드에서 리턴하는 값을 viewResolver를 거쳐서 출력하지 않고 Http Response Body에 직접 쓰게 된다, OutputStream을 열어서 보내줌
	//Http Response Body에 값을 가지고 올수 있도록 통로를 열고 데이터를 가지고 옴 
	   //OutputStream ownOut = response.getOutputStream(); 대신에 사용(spring 방식)
	public FileSystemResource noticeDownload(HttpServletResponse res, HttpSession session, String ofname, String rfname) {
		
		// 저장된 파일 경로
		String readFolder = session.getServletContext().getRealPath("/resources/upload");
		
		// FileSystemResource
		FileSystemResource downFile = new FileSystemResource(readFolder + "/" + rfname);
		
		try {
			res.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(ofname, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return downFile;
	}
	
	@RequestMapping(value="notice/noticeModify.do", method=RequestMethod.GET)
	public ModelAndView noticeModify(int nIdx, String userId, HttpSession session, HttpServletRequest req) {
		
		ModelAndView mav = new ModelAndView();
		
		
		// 게시글 정보 불러오기
		Map<String, Object> map = noticeService.selectNoticeDatail(nIdx);
		
		// view 단에 불러온 정보 전달
		mav.addObject("data", map);
		// view 지정
		mav.setViewName("board/boardModify");
			
		return mav;
	}
	
	@RequestMapping(value="notice/noticeModify.do", method=RequestMethod.POST)
	public ModelAndView noticeModify(String userId, Notice notice, List<MultipartFile> files, HttpSession session) {
		
		ModelAndView mav = new ModelAndView();
		
		// 파일 업로드
		System.out.println(notice);
		// 경로
		String root = session.getServletContext().getRealPath("/");
		
		// 게시판 입력
		int res = noticeService.modifyNotice(notice, files, root);
		
		// redirect:url - url로 리다이랙트 해준다
		mav.setViewName("redirect:/notice/noticedetail.do?nIdx=" + notice.getnIdx());
			
		return mav;
		
	}
	
	@RequestMapping(value="/notice/filedelete.do", method=RequestMethod.POST)
	@ResponseBody
	public int fileDelete(int fIdx) {
		
		int res = noticeService.deleteFile(fIdx);
		
		if( res > 0) {
			return 1;
		}else {
			return 0;
		}
	}
	
	@RequestMapping("notice/noticedelete.do")
	public ModelAndView deleteNotice(int nIdx, String userId) {
		
		ModelAndView mav = new ModelAndView();
		
		int res = noticeService.deleteNotice(nIdx);
		
		mav.setViewName("redirect:/notice/noticelist.do");
		
		return mav;
	}
	
	
	
	


}
