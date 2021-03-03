package aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {

	@Pointcut("execution(* service.*.*(..))")
	private void myPointCut()	{}
	
	@Before("myPointCut()")
	public void MyBefore(JoinPoint joinPoint)
	{
		System.out.println("前置通知开始了");
	}
	
}
