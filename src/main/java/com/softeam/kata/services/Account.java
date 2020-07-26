package com.softeam.kata.services;

import java.math.BigDecimal;

import com.softeam.kata.model.InMemoryTransactions;

public class Account {

	public Account(BigDecimal initAmount, InMemoryTransactions transactions) {
		System.out.println("Create account");
	}

}
