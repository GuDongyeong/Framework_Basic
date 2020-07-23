package dynamic.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dynamic.dao.DynamicQueryDao;
import dynamic.dto.Emp;
import mybatis.MyBatisConnectionFactory;

@WebServlet("/dynamic/query")
public class DynamicQueryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// 마이바티스 연결 객체
	private SqlSessionFactory factory = MyBatisConnectionFactory.getSqlSessionFactory();
	
	// 마이바티스 수행객체
	private SqlSession sqlSession;
	
	// DAO 객체
	private DynamicQueryDao dynamicQueryDao;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		sqlSession = factory.openSession(true);
		dynamicQueryDao = sqlSession.getMapper(DynamicQueryDao.class);
		
		//-------------------------------------------------------------
		// if 태그 예제
		
//		List<HashMap<String, Object>> list = dynamicQueryDao.select();
		
//		for( Object obj : list ) {
//			System.out.println(obj);
//		}
		
//		req.setAttribute("list", list);
		
//		req.getRequestDispatcher("/WEB-INF/views/dynamic/query.jsp").forward(req, resp);
		

//		List res2 = dynamicQueryDao.select2( "J" );
//		System.out.println(res2);
	
		
//		Emp emp = new Emp();
//		emp.setEmpno(7839);
//		emp.setEname( "KING" );
		
		// choose 예제
		
//		List res3 = dynamicQueryDao.select3(emp);
//		System.out.println(res3);

		//-------------------------------------------------------------
		// trim 태그 예제
		
//		HashMap<String, Object> map = new HashMap<>();
//		map.put("category", "ename");
//		map.put("keyword", "FORD");
//		
//		List<Emp> res4 = dynamicQueryDao.select4(map);
//		
//		System.out.println(res4);
//		
//		System.out.println("------------------------------");
//		
//		map.put("category", "job");
//		map.put("keyword", "SALESMAN");
//		
//		List<Emp> res5 = dynamicQueryDao.select4(map);
//		
//		System.out.println(res5);
		
		//-------------------------------------------------------------
		
		req.getRequestDispatcher("/WEB-INF/views/dynamic/queryCheckbox.jsp").forward(req, resp);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		sqlSession = factory.openSession(true);
		dynamicQueryDao = sqlSession.getMapper(DynamicQueryDao.class);
		
		//-------------------------------------------------------------
		
		// 여러 개의 묶음을 보내도 하나만 아무거나 가져온다..getParameter 쓰면 안된다(전달값이 하나일때만)
		// 전달 파라미터가 하나의 값을 때만 getParameter를 써야함
//		String data = req.getParameter("deptno");
//		System.out.println(data); // 전달된 데이터 중 한 개만 꺼내옴
	
		// ----------------------------------------------------------
		
		String[] dataArr = req.getParameterValues("deptno");
		System.out.println(Arrays.toString(dataArr));
		
		// 데이터가 여러 개이면 hashmap 에 넣어서 전달
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("deptnoArr", dataArr);
		
		List<Emp> list = dynamicQueryDao.selectCheckbox(map);

		for (Emp emp : list) {
			System.out.println(emp);
		}
		
		sqlSession.clearCache();
		
		req.setAttribute("list", list);
		
		req.getRequestDispatcher("/WEB-INF/views/dynamic/resultCheckbox.jsp").forward(req, resp);
		
		
	}

}
