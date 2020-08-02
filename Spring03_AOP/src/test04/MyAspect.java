package test04;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

// 해당 클래스가 Aspect 역할을 하도록 해주는 어노테이션
@Aspect
public class MyAspect {
	// advice와 pointcut 역할을 모두 한다
	
	// 순서 @Before -> @After -> @AfterReturning/@AfterThrowing(예외 시)
	
	
	// advice 역할을 할 메소드
	
	
	// 어노테이션으로 pointcut을 지정해준다. 속성으로 적용될 메소드 지정
	@Before("execution(public * *(..))")
	public void before(JoinPoint join) {
		System.out.println("출석카드를 찍는다");
	}
	
	// target 객체가 예외를 반환한 뒤에 실행하는 메소드, catch 문에 작성하는 코드
	@AfterThrowing("execution(public * *(..))")
	public void throwing(JoinPoint join) {
		System.out.println("쉬는 날이었다");
	}
	
	// target 객체가 반환값을 반환하는 시점에 호출되는 메소드
	// returning : target 객체가 반환하는 값을 담을 변수 -> advice 메소드의 매개변수로 들어간다
	@AfterReturning(pointcut="execution(public * *(..))", returning="returnVal")
	public void returning(JoinPoint join, Object returnVal) {
		System.out.println(returnVal + " 공부 하는 날");
	}
	
	// 타깃 객체의 메소드를 실행하고 결과값을 반환하기 직전에 실행된다
	// return 값을 가지고 오지 않는다
	@After("execution(public * *(..))")
	public void after(JoinPoint join) {
		System.out.println("집에 간다");
	}

}
