package com.luv2code.aopdemo.dao;

import org.springframework.stereotype.Component;

@Component
public class MembershipDAO {

	public void addAccount() {
		
		System.out.println(getClass() + ": Doing add account in membershipDAO");
	}
	
	public boolean goToSleep() {
		
		System.out.println(getClass() + ": ZZZ...");
		return false;
	}
}
