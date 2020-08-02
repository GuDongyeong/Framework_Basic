package test03;

import org.aspectj.lang.JoinPoint;

public class MyAdvice {
	
	// 분리하고 싶은 부기능을 메소드 형식으로 분리
	
	// JoinPoint
	//	타겟 객체에 대한 정보를 담고 있다
	// 	Spring AOP 를 사용하기 위해서는 반드시 매개변수로 넣어줘야 하는 객체	
	public void before(JoinPoint join) {
		
		// 클래스명
		System.out.println(join.getTarget().getClass());
		
		// 메소드 이름
		System.out.println(join.getSignature().getName());
		
		// 대상 객체 메소드의 정보
		System.out.println(join);
		
		// 메소드 매개변수, 없으면 Object 가 찍힘
		System.out.println(join.getArgs());
		
		
		
		System.out.println("출석 카드를 찍는다");
	}
	
	public void after(JoinPoint join) {
		System.out.println(join.getTarget().getClass());
		System.out.println(join.getSignature().getName());
		System.out.println("집에 간다");
	}

}
