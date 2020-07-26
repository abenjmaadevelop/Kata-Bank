package com.softeam.kata.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.softeam.kata.model.InMemoryTransactions;
import com.softeam.kata.model.Transaction;

public class Account {

	private final InMemoryTransactions transactions;

	public Account(BigDecimal initAmount, InMemoryTransactions transactions) {
		if (initAmount.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("Cannot create account with negative balance");
		}
		this.transactions = transactions;
		this.transactions.add(new Transaction(LocalDateTime.now(), initAmount, initAmount));
	}

	public void deposit(BigDecimal amount) {
		// TODO Auto-generated method stub
		
	}

}
