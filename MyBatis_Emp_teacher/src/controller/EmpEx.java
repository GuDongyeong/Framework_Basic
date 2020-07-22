package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dao.EmpDao;
import dto.Emp;
import mybatis.MyBatisConnectionFactory;

public class EmpEx {

	// sqlSession 객체 준비 과정, configuration을 읽음
	private static SqlSessionFactory factory = MyBatisConnectionFactory.getSqlSessionFactory();
	
	public static void main(String[] args) {
		
		
		// 마이바티스 수행 객체
		SqlSession sqlSession;
		
		sqlSession = factory.openSession(true); // 오토커밋
		
		//----------------------------------------------------
		
		// 마이바티스의 매퍼와 자바프로그램의 DAO 인터페이스매핑(연결), Mapper를 읽음
		EmpDao empDao = sqlSession.getMapper(EmpDao.class);
		// test.getClass() == test.class
		//	class Class에는 클래스에 관한 모든 정보가 들어있다.
		//	test.class 는 test에 대한 클래스 정보를 반환해주는 예약어
		
		//----------------------------------------------------
		
		Scanner sc = new Scanner(System.in);
		
		//----------------------------------------------------

//		sqlSession.getMapper(arg0);
		
		// 쿼리수행 메소드
		// 첫번째 인자는 sql 쿼리, 매퍼를 따로 두지 않고 쿼리만 사용할 수 있다
		// 두번째 인자는 preparedstatement 에 ?를 object로 지정 가능
//		sqlSession.selectOne();
//		sqlSession.selectList();
//		sqlSession.selectMap();
//		
		// int 반환해준다, mapper에서는 반환 불가능
//		sqlSession.insert();
//		sqlSession.update();
//		sqlSession.delete();
		
		// 트랜잭션 관리 메소드
//		sqlSession.commit();
//		sqlSession.rollback();
		
//		sqlSession.close();
		//----------------------------------------------------
		
		
		System.out.println("--- 사원 정보전체 조회 ---");
		
		List<Emp> list1 = empDao.selectAll();
		
		for( Emp emp : list1 ) {
			System.out.println(emp);
		}
		
		System.out.println("--- 사원 정보 입력 ---");
		
		Emp emp1 = new Emp();
		
		System.out.print(">> 사원 번호 : ");
		emp1.setEmpno(sc.nextInt());
		
		System.out.print(">> 사원 이름 : ");
		sc.nextLine();
		emp1.setEname( sc.nextLine());
		
		System.out.print(">> 사원 직무 : ");
		emp1.setJob(sc.nextLine());
		
		System.out.print(">> 매니저 : ");
		emp1.setMgr(sc.nextInt());
		
		System.out.print(">> 입사일(yyyy-MM-dd) : ");
		sc.nextLine();
		String input = sc.nextLine();
		
		// String -> java.util.Date : SimpleDateFormat 사용
		SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
		Date hiredate = null;
		
		try {
			hiredate = form.parse(input);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		emp1.setHiredate( hiredate );
		
		System.out.print(">> 급여 : ");
		emp1.setSal(sc.nextDouble());
		
		System.out.print(">> 성과금 : ");
		emp1.setComm(sc.nextDouble());
		
		System.out.print(">> 부서번호 : ");
		emp1.setDeptno(sc.nextInt());
		
		empDao.insertEmp(emp1);
		
		System.out.println("--- 사원번호로 사원정보 출력 ---");
		
		Emp emp2 = empDao.selectByEmpNo(emp1);
		System.out.println(emp2);
		
		System.out.println("--- 사원번호로 사원정보 삭제 ---");
		
		empDao.deleteByEmpNo(emp1);
		
		System.out.println("--- 사원 정보전체 조회 ---");
		List<Emp> list2 = empDao.selectAll();
		
		for( Emp emp : list2) {
			System.out.println(emp);
		}
		
		
	}
}
