package test01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Run {
	public static void main(String[] args) {
		
		// 자바 빈을 생성해주는 객체
		// ApplicationContext가 생성되는 시점에 applicationContext.xml을 참조하도록 생성자의 매개변수에 xml 파일의 경로를 입력한다
		// applicationContext는 생성과 동시에 applicationContext.xml 파일에서 설정한 일들(객체 생성, 의존성 주입 등등)을 처리한다
		ApplicationContext factory = new ClassPathXmlApplicationContext("test01/applicationContext.xml");
		
		// ApplicationContext로부터 해당 아이디를 가진 bean을 반환받음
		Address lee = (Address) factory.getBean("lee");
		Address hong = (Address) factory.getBean("hong");
		
		System.out.println(lee);
		System.out.println(hong);
	}
}
