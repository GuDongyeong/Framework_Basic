package multiparam.controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;


import dynamic.dto.Emp;
import multiparam.dao.MultiParamDao;
import mybatis.MyBatisConnectionFactory;

@WebServlet("/multi/param")
public class MultiParamController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// 마이바티스 연결 객체
	private SqlSessionFactory factory = MyBatisConnectionFactory.getSqlSessionFactory();
	
	// 마이바티스 수행객체
	private SqlSession sqlSession;
	
	private MultiParamDao multiParamDao;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		sqlSession = factory.openSession(true);
		
		multiParamDao = sqlSession.getMapper(MultiParamDao.class);
		
		//---------------------------------------------------------
	
		System.out.println("/multi/param [GET]");
	
		//---------------------------------------------------------
		
//		// DTO를 이용한 파라미터 전달
//		Emp emp = new Emp();
//		emp.setEmpno(5003);
//		emp.setEname("CCCCCC");
//		emp.setJob("CCCCC");
//		
//		multiParamDao.insert(emp);
		
		//---------------------------------------------------------
		
		// 여러 개의 파라미터 전달
		// 부적합한 열 유형: 1111 에러, property에 null로 들어간 값이 나옴
		// null을 넣으면 에러남 - null은 자바 언어이기 때문에 null은 데이터 타입이 Object이다/ 오라클은 각 데이터타입 마다 null 이 있다
		// 오라클에서는 Object null을 받아주지 못함
//		multiParamDao.insert2(5009, "I", null);
		
		// 원래 PreparedStatement에는 setNull이 있다
//		PreparedStatement ps;
//		
//		ps.setInt(1, 123);
//		ps.setString(2, "hi");
//		ps.setNull(3, Type.VARCHAR2);
		
		//---------------------------------------------------------
		
		// HashMap을 파라미터 전달하는 방법
		HashMap<String, Object> map = new HashMap<>();
		
		map.put("empno", 5010);
		map.put("ename", "J");
		map.put("job", "III");
		
		multiParamDao.insert3(map);
		
		
		
		
	}

}
