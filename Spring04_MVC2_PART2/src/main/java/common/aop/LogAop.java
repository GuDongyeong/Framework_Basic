package common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LogAop {
	
	@Before("execution(* web..*.*(..))")
	public void before(JoinPoint join) {
		
		// 클래스 정보를 문자열로 반환
		Logger logger = LoggerFactory.getLogger(join.getTarget().toString());
		
		logger.info("----------------log----------------");

		// 메소드 명
		logger.info("method - " + join.getSignature().getName());
		
		// 매개변수를 배열로 반환받음
		Object[] args = join.getArgs();
		if( args.length >0 ) {
			logger.info("----매개변수----");
			for(Object arg : args) {
				logger.info(arg.toString());
			}
		}
		
	}

}
