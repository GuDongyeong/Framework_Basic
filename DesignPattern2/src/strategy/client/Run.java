package strategy.client;

import java.util.Scanner;

import strategy.library.dao.ConnectionMaker;
import strategy.library.dao.DeptDao;
import strategy.library.vo.Dept;

public class Run {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		DeptDao deptDao = new DaoFactory().createDeptDao();
		Dept dept = new Dept();
		
		System.out.println("=======�μ��������=======");
		System.out.println("�μ���ȣ�� �Է��ϼ���.");
		 dept.setDeptNo(sc.nextInt());
		System.out.println("�̸��� �Է��ϼ���.");
		 dept.setdName(sc.next());
		System.out.println("������ �Է��ϼ���.");
		 dept.setLoc(sc.next());
		
		 deptDao.insertDept(dept);
		 Dept res = deptDao.selectDept(dept.getDeptNo());
		 
		 System.out.println(res);

	}

}
