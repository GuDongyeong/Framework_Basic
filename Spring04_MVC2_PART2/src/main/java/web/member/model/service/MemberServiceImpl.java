package web.member.model.service;

import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import common.exception.MailException;
import web.member.model.dao.MemberDao;
import web.member.model.vo.Member;

// @Controller나 @Repository와 달리 bean으로 등록시켜주는 기능 외에는 별다른 기능이 없다.
// @Component와 동일하지만 가독성을 위해 Service class에 사용하는 어노테이션
@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public int insertMember(Member member) {

		// 비번 암호화 처리
		
		// 사용자가 입력한 password
		String password = member.getPassword();
		
		// password, 매번 다른 방식으로 암호화가 진행
		password = passwordEncoder.encode(password);
		System.out.println("암호화된 비번 : " + password);
		
		member.setPassword(password);
		return memberDao.insertMember(member);
	}
	
	public Member selectMember(Map<String, Object> map) {
		
		// 사용자가 입력한 비밀번호
		String password = (String) map.get("pw");
		
		// DB에 저장된 사용자 정보
		Member member = memberDao.selectMember(map);
		
		// 사용자가 입력한 비밀번호와 DB에 인코딩되어 저장된 비밀번호가 같은지 확인
		if( passwordEncoder.matches(password, member.getPassword())) {
			
			member.setPassword(password);
			
			return member;
		}else {
			return null;
		}
		
	}

	public int updateMember(Member m, HttpSession session) {
		
		// 비번 암호화 처리
		String password = m.getPassword();
		
		password = passwordEncoder.encode(password);
		
		m.setPassword(password);
		
		return memberDao.updateMember(m);
	}
	

	public int leaveMember(String userId) {
		return memberDao.leaveMember(userId);
	}
	
	public int selectId(String userId) {
		return memberDao.selectId(userId);
	}
	

	public void mailSending(Member member, String urlPath) throws MailException {
			
		String setfrom = "ehd1722@naver.com";
		String tomail = member.getEmail();
		String title = "회원가입을 환영합니다.";
		String htmlBody = "<form "
				+ "action='http://"+urlPath+"/member/joinimple.do'"
				+" method='post'>"
				+ "<h3>회원가입을 환영합니다</h3>"
				+ "<input type='hidden' value='" 
				+ member.getUserId() 
				+ "' name='userId'>"
				+ "<input type='hidden' value='"
				+ member.getPassword()
				+"' name='password'>"
				+ "<input type='hidden' value='"
				+ member.getTell()
				+"' name='tell'>"
				+ "<input type='hidden' value='"
				+ member.getEmail()
				+"' name='email'>"
				+ "<button type='submit'>전송하기</button></form>";
		try {
		
//			int errorNumber = 10/0;
			
			mailSender.send(new MimeMessagePreparator() {
				@Override
				public void prepare(MimeMessage mimeMessage) throws MessagingException {
			
				// true : 멀티파트 메세지를 사용하겠다
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				
				// 보내는 이
				message.setFrom(setfrom);
				
				// 받는 이
				message.setTo(tomail);
				
				// 메일 제목
				message.setSubject(title);
				
				// 메일 내용, true : html 사용하겠다
				message.setText(htmlBody, true);
			}
		
			});

		}catch(Exception e) {
//					e.printStackTrace();
			throw new MailException("M_ERROR_01");
		}

	}

	public void leaveMailSending(Member member, String urlPath) {
		mailSender.send(new MimeMessagePreparator() {
			
			@Override
		   public void prepare(MimeMessage mimeMessage) throws MessagingException {
			   
			   String setfrom = "ehd1722@naver.com";
			   String tomail = member.getEmail();
			   String title = "회원 탈퇴";
			   String htmlBody = "<h1>회원탈퇴 처리가 완료되었습니다.</h1>";
			   
			   // true : 멀티파트 메세지를 사용하겠다
			   MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			   
			   // 보내는 이
			   message.setFrom(setfrom);
			   
			   // 받는 이
			   message.setTo(tomail);
			   
			   // 메일 제목
			   message.setSubject(title);
			   
			   // 메일 내용, true : html 사용하겠다
			   message.setText(htmlBody, true);
		   }
		
		});
		
	}

}
