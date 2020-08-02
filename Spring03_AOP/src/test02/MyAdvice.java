package test02;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MyAdvice implements MethodInterceptor {

	// Advice로 바꾸기, 동적으로 조작할 수 있도록 AOP..
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		
		// target 객체의 메소드가 반환하는 값을 받기 위한 변수
		Object target = null;
		
		// 부기능 : 중복되는 코드(예외처리 등등)
		System.out.println("출석카드를 찍는다.");
		
		try {
			// 주기능 호출
			// target 객체의 메소드 실행
			target = invocation.proceed();
		}catch(Exception e) {
			System.out.println("쉬는 날이었다");
		}finally {
			System.out.println("집에 간다");
		}
		return target;
	}

}
