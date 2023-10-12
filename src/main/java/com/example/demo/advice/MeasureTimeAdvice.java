package com.example.demo.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MeasureTimeAdvice {
//	execution(modifiers-pattern? return-type-pattern declaring-type-pattern? name-pattern( param-pattern)
//			  throws-pattern?)
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Around(value = "execution(* com.example.demo.**.*.save*(com.example.demo.entity.Orders))")//pointcut
	public void measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.currentTimeMillis();
		joinPoint.proceed();
		long end = System.currentTimeMillis();
		logger.debug("class name:"+joinPoint.getSignature().getDeclaringTypeName()
				+", method name: "+joinPoint.getSignature().getName()
				+", time take ="+(end-start));
	}
	@Before(value="execution(* com.example.demo.**.*.save*(*))")
	public void beforeAdvice(){
		logger.debug("Before advice called");
	}
}
