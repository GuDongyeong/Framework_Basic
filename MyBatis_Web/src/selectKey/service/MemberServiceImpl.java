package selectKey.service;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import mybatis.MyBatisConnectionFactory;
import selectKey.dao.SelectKeyDao;
import selectKey.dto.Member;

public class MemberServiceImpl implements MemberService {
	
	// 마이바티스 연결 객체
	private SqlSessionFactory factory = MyBatisConnectionFactory.getSqlSessionFactory();
	
	// dao 객체
	private SelectKeyDao selectKeyDao;

	@Override
	public Member getParam(HttpServletRequest req) {
		
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		
		Member member = new Member();
		
		member.setId(id);
		member.setPw(pw);
		
		return member;
	}

	@Override
	public void insertMember(Member member) {
		
		// 마이바티스 수행객체
		SqlSession sqlSession = factory.openSession(true);
		
		// 매퍼를 이용한 DAO 연결
		selectKeyDao = sqlSession.getMapper(SelectKeyDao.class);
		
		// 로그인정보 삽입
		selectKeyDao.insert(member);
		
	}

}
