package controller;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dao.EmpDao;
import dto.Emp;
import mybatis.MyBatisConnectionFactory;

public class EmpEx {
	public static void main(String[] args) {
		
		// 마이바티스 수행 객체
		SqlSession sqlSession;
		
		SqlSessionFactory factory = MyBatisConnectionFactory.getSqlSessionFactory();
		
		sqlSession = factory.openSession(true); // 오토커밋
		
		//----------------------------------------------------
		
		// 마이바티스의 매퍼와 자바프로그램의 DAO 인터페이스매핑(연결)
		EmpDao empDao = sqlSession.getMapper(EmpDao.class);
		
		//----------------------------------------------------
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("--- 사원 정보전체 조회 ---");
		List<Emp> list1 = empDao.selectAll();
		
		for( Emp emp : list1) {
			System.out.println(emp);
		}
		
		System.out.println("--- 사원 정보 입력 ---");
		
		Emp emp1 = new Emp();
		
		emp1.setEmpno(10);
		emp1.setEname("dydydy");
		emp1.setJob("SALES");
		emp1.setMgr(66);
		emp1.setHiredate( new Date());
		emp1.setSal(123);
		emp1.setComm(123);
		emp1.setDeptno(10);
		
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
