package com.luv2code.aopdemo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;

@Component
public class AccountDAO {

	// give a new method: findAccounts()
	public List<Account> findAccounts(boolean tripWire) {
		
		//simulate an exception
		if (tripWire) {
			throw new RuntimeException("It is an exception!");
		}
		List<Account> myAccounts = new ArrayList<>();
		
		//create sample accounts
		Account temp1 = new Account("John", "Silver");
		Account temp2 = new Account("Madhu", "Platinum");
		Account temp3 = new Account("Darin", "Gold");
		
		//add them to our accounts list
		myAccounts.add(temp1);
		myAccounts.add(temp2);
		myAccounts.add(temp3);
		
		return myAccounts;
	}
	
	
	public void addAccount(Account theAccount) {
		
		System.out.println(getClass() + ": Doing add account");
	}
	
	public boolean doWork() {
		
		System.out.println(getClass() + ": Working");
		return false;
	}
	
}
