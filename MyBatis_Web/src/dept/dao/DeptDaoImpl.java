package dept.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dept.dto.Dept;
import mybatis.MyBatisConnectionFactory;

// 시험 나옴
// getMapper 대신 sqlSession 메소드 사용하기

public class DeptDaoImpl implements DeptDao {

	// 마이바티스 객체 생성
	private SqlSessionFactory factory = MyBatisConnectionFactory.getSqlSessionFactory();
	
	private String namespace = "dept.dao.DeptDao.";
	
	// 두번쨰 인자 map, 이런식으로 준비해놓기도 한다
	private HashMap map;
	
	@Override
	public List<Dept> selectAll() {
		
		// 마이바티스 수행 객체
		SqlSession sqlSession = factory.openSession();
		
		// DB 조회 결과
		// 파라미터로 mapper의 namespace.쿼리 아이디를 작성한다, 파라미터가 있는 경우에는 두 번째 인자로 DTO or map을 넣어준다
		List<Dept> deptList = sqlSession.selectList( namespace + "selectAll");
		
//		sqlSession.commit();
//		sqlSession.clearCache();
		sqlSession.close();
		
		return deptList;
	}

	@Override
	public Dept selectDept(Dept dept) {
		
		// 마이바티스 수행 객체
		SqlSession sqlSession = factory.openSession();
		
		// DB 조회 결과
		Dept res = sqlSession.selectOne(namespace + "selectDept", dept);
		
		// 마이바티스 객체 닫기
		sqlSession.close();
		
		// 결과 반환
		return res;
	}

	@Override
	public void insertDept(Dept dept) {
		
		// 마이바티스 수행 객체
		SqlSession sqlSession = factory.openSession(true);
		
		// DB에 저장,
		// SqlSession.insert는 테이블의 영향받은 행 수를 반환한다 - executeUpdate 와 같음
		// update, delete도 마찬가지
		int res = sqlSession.insert(namespace + "insertDept", dept);
				
		if( res > 0) {
			System.out.println("삽입 성공");
		}else {
			System.out.println("삽입 실패");
		}
		
		sqlSession.commit();
		// close 하면서 commit 도 됨
		sqlSession.close();
	}

}
