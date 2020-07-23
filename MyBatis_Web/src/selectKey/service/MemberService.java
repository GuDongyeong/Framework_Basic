package selectKey.service;

import javax.servlet.http.HttpServletRequest;

import selectKey.dto.Member;

public interface MemberService {

	// 파라미터 처리
	public Member getParam(HttpServletRequest req);

	// 멤버 삽입
	public void insertMember(Member member);

}
