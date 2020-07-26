package com.softeam.kata.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.softeam.kata.model.InMemoryTransactions;
import com.softeam.kata.model.Transaction;

public class Account {

	public Account(BigDecimal initAmount, InMemoryTransactions transactions) {
		if (initAmount.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("Cannot create account with negative balance");
		}
		transactions.add(new Transaction(LocalDateTime.now(), initAmount, initAmount));
	}

}
