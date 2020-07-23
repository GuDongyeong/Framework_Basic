package web.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import mybatis.MyBatisConnectionFactory;
import web.dao.EmpDao;
import web.dto.Emp;

public class EmpServiceImpl implements EmpService {

	// 마이바티스 연결 객체
	private SqlSessionFactory factory = MyBatisConnectionFactory.getSqlSessionFactory();
	
	// DAO 객체
	private EmpDao empDao;
	
	@Override
	public List<Emp> getList() {
		
		// 마이바티스 수행 객체, 메소드 안에서 만들 것, 커밋, 롤백, 클로즈 등을 처리해주기 위해
		SqlSession sqlSession = factory.openSession();
		
		// 매퍼를 이용한 DAO 연결
		empDao = sqlSession.getMapper(EmpDao.class);
		
		// 전체 리스트 조회
		List<Emp> list = empDao.selectAll();
		
//		sqlSession.commit();
//		sqlSession.rollback();
//		
//		sqlSession.close();
		
		return list;
	}

	@Override
	public Emp getDetail(int empno) {
		
		// 마이바티스 수행 객체
		SqlSession sqlSession = factory.openSession();
		
		// 매퍼를 이용한 DAO 연결
		empDao = sqlSession.getMapper(EmpDao.class);
		
		Emp emp = empDao.selectByEmpno(empno);
		
		return emp;
	}

	@Override
	public int getParam(HttpServletRequest req) {
		
		int empno = 0;
		
		String param = req.getParameter("empno");
		
		if( param != null && !"".equals(param)) {
			empno = Integer.parseInt(param);
		}
		
		return empno;
	}

}
