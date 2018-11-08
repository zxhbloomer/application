package com.main;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class ControllerAopConfig {
	/**
	 * 定义拦截规则
	 * PS:参数可以使用 .. 表示所有, 其他的可以使用*号表示
	 */
	@Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
	public void webLog(){}

	/**
	 *  方法执行之前
	 */
	@Before("webLog()")
	public void before(JoinPoint joinPoint) throws Throwable {
		log.info("@Before: 方法进入之前");
	}

	/**
	 * 环绕执行
	 */
	@Around("webLog()")
	public Object around(ProceedingJoinPoint pjp){
		log.info("@Around : 方法环绕");

		//参数列表(也可通过Request来获取其他数据)
		for(int i=0;i<pjp.getArgs().length;i++){ log.info("@Around : 方法第 " + i + " 个参数 = " + pjp.getArgs()[i]);}

		//返回结果(这里可以try/catch进行处理)
		Object result = null;
		try {
			result = pjp.proceed();
		} catch (Throwable throwable) {
			throwable.printStackTrace();
			return null;
		}

		log.info("@Around : 返回结果 :" + result);
		return result;
	}

	/**
	 * 处理完成返回(出现异常有可能不执行)
	 */
	@AfterReturning(returning = "ret", pointcut = "webLog()")
	public void afterReturning(Object ret) throws Throwable {
		log.info("@AfterReturning : 方法最后执行(Not Final) = " + ret);
	}

	/**
	 * 处理完成返回(无论如何都会执行)
	 */
	@After("webLog()")
	public void after(JoinPoint jp){
		log.info("@After : 方法最后执行(Final)");
	}

	/**
	 * 出现异常执行(注意此注解某些情况会和@Around冲突,当设置了@Around之后@AfterThrowing会失效)
	 */
	@AfterThrowing("webLog()")
	public void afterThrowing(JoinPoint jp){
		log.info("@AfterThrowing : 方法异常时执行");
	}

}
