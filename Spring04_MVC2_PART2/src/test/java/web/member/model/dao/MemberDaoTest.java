package web.member.model.dao;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import web.member.model.vo.Member;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/*-context.xml"})
public class MemberDaoTest {

	// SpringJunit4ClassRunner 를 사용해 만든 applicationContext로부터 의존성 주입
	@Autowired
	private MemberDao memberDao;
	
	// 테스트를 수행하기 위한 메소드
	// notNullValue : null이면 false, null이 아니면 true
	@Test
	public void test1() {
		assertThat(memberDao, is(notNullValue()));
	}
	
	// 데이터베이스에 존재하는 특정 회원을 조회
	// 예상값과 결과로 반환되는 member 객체의 userId 값이 일치하는지 확인
	// equalTo 사용
	@Test
	public void test2() {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", "eee");
		Member member = memberDao.selectMember(map);
		
		// assertThat(결과값, 예상값)
		assertThat(member.getUserId(), equalTo( map.get("id")));
		
		// 에러 "eee"를 예상했지만 ""를 반환
//		member.setUserId("");
//		assertThat(member.getUserId(), equalTo( map.get("id")));
		
	}
	
	
	@Test
	public void test3(){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("id", "eeeeeeeeeeeeee");
		
		Member member = memberDao.selectMember(map);
		
		// 객체가 null 이면 true 반환
		assertThat(member, is(nullValue()));
		
	}
}