package com.luv2code.aopdemo.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;

@Aspect
@Component
public class MyDemoLoggingAspect {

	// this is where we add all of related advice for logging
	@Around("execution(* com.luv2code.aopdemo.service.*.getFortune(..))")
	public Object aroundGetFrotune(
			ProceedingJoinPoint theProceedingJoinPoint) throws Throwable{
		
		//print out method we advising on
		String method = theProceedingJoinPoint.getSignature().toShortString();
		System.out.println("\n=====> Executing @Around advice on method: " + method);
		
		//get begin timestamp
		long begin = System.currentTimeMillis();
		
		//execute the method
		Object result = theProceedingJoinPoint.proceed();
		
		//get end timestamp
		long end = System.currentTimeMillis();
		
		//compute duration and display it
		long duration = end - begin;
		System.out.println("\n=====> Duration: " + duration / 1000.0 + " seconds");
		
		return result;
	}
	
	
	
	@AfterThrowing(
			pointcut="execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
			throwing="theExc")
	public void afterThrowingFindAccountsAdvice(JoinPoint theJoinPoint, Throwable theExc) {
		
		//print out which method we advice on
		String method = theJoinPoint.getSignature().toShortString();
		System.out.println("\n=====> Executing @Afterthrowing advice on method: " + method);
		
		//log the exception
		System.out.println("\n=====> The exception is: " + theExc);
		
	}
	
	//add a new advice for @AfterReturning on the findAccounts method
	@AfterReturning(
			pointcut="execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
			returning="result")
	public void afterReturningFindAccountsAdvice(
					JoinPoint theJoinPoint, List<Account> result) {
		
		//print out which method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		System.out.println("\n=====> Executing @AfterReturning advice on method: " + method);
		
		//print out the result of the method call
		System.out.println("\n=====> result is: " + result);
		
		//convert the result to Uppercase
		convertToUpperCase(result);
	}
	
	//@before advice
	
	private void convertToUpperCase(List<Account> result) {

		if (!result.isEmpty()) {
			for(Account temp: result) {
				String tempAccount = temp.getName().toUpperCase();
				temp.setName(tempAccount);
			}
		}
	}

	@Pointcut("execution(* com.luv2code.aopdemo.dao.*.* (..))")
	public void forDaoPackage() {}
	
	@Before("forDaoPackage()")
	public void beforeAddAccountAdvice() {

		System.out.println("\n=====> Executing @Before advice on addAcount()");
	}
	
	@Before("forDaoPackage()")
	public void performApiAnalytics() {
		
		System.out.println("\n=====> perform Api Analytics");
	}
}
