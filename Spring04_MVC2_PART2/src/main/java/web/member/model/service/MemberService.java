package web.member.model.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import web.member.model.vo.Member;

// @Controller나 @Repository와 달리 bean으로 등록시켜주는 기능 외에는 별다른 기능이 없다.
// @Component와 동일하지만 가독성을 위해 Service class에 사용하는 어노테이션
@Service
public interface MemberService {
	
	public int insertMember(Member member);
	
	public Member selectMember(Map<String, Object> map);

	public int updateMember(Member m);

	public int leaveMember(String userId);
	
	public int selectId(String userId);
	
	public void mailSending(Member member, String urlPath);

	public void leaveMailSending(Member member, String urlPath);

}
