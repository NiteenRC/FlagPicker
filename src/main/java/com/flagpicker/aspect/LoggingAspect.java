package com.flagpicker.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * This LoggingAspect implements check performance for each service hit by user
 * 
 * @author Niteen Chougula
 * @version 1.0
 * @since 2019-12-27
 */
@Aspect
@Component
public class LoggingAspect {
	private static final Logger LOGGER = LogManager.getLogger(LoggingAspect.class);

	/**
	 * This method calculate time for each service request and return result as
	 * class name, method name and time taken
	 * 
	 * @param proceedingJoinPoint
	 * @return Object
	 * @throws Throwable
	 */
	@Around("execution(* com.flagpicker.service..*(..)))")
	public Object profileAllMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		// Get intercepted method details
		MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
		String className = methodSignature.getDeclaringType().getSimpleName();
		String methodName = methodSignature.getName();

		// Measure method execution time
		final StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		Object result = proceedingJoinPoint.proceed();
		stopWatch.stop();

		// Log method execution time
		LOGGER.info(
				"Execution time of " + className + "." + methodName + " :: " + stopWatch.getTotalTimeMillis() + " ms");
		return result;
	}
}
