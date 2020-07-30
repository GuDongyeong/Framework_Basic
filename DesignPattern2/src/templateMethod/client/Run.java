package templateMethod.client;

import java.util.Scanner;
import templateMethod.library.dao.DeptDao;
import templateMethod.library.vo.Dept;

public class Run {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		DeptDao deptDao = new ClientDeptDao();
		Dept dept = new Dept();
		
		System.out.println("=======부서정보등록=======");
		System.out.println("부서번호를 입력하세요.");
		 dept.setDeptNo(sc.nextInt());
		System.out.println("이름을 입력하세요.");
		 dept.setdName(sc.next());
		System.out.println("지역을 입력하세요.");
		 dept.setLoc(sc.next());
		
		 deptDao.insertDept(dept);
		 Dept res = deptDao.selectDept(dept.getDeptNo());
		 
		 System.out.println(res);

	}

}
