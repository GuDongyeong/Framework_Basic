package web.member.model.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import web.member.model.vo.Member;

// 해당 클래스를 Dao 역할을 하는 Bean으로 등록한다
// SqlException을 DataAccessException으로 전환한다.
@Repository
public class MemberDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public int insertMember(Member member) {
		
		// MEMBER.insertMember : Mapper의 NameSpace 이름.태그 id 속성값
		return sqlSession.insert("MEMBER.insertMember", member);
	}

	public Member selectMember(Map<String, Object> map) {
		
		return sqlSession.selectOne("MEMBER.selectMember", map);
	}

	public int updateMember(Member m) {
		return sqlSession.update("MEMBER.updateMember", m);
	}

	public int leaveMember(String userId) {
		return sqlSession.update("MEMBER.leabeMember", userId);
	}
	
	public int selectId(String userId) {
		return sqlSession.selectOne("MEMBER.selectId", userId);
	}

}
