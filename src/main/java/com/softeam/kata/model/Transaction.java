package com.softeam.kata.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
	private final LocalDateTime time;
	private final BigDecimal amount;
	private final BigDecimal balance;

	public Transaction(LocalDateTime time, BigDecimal amount, BigDecimal balance) {
		this.time = time;
		this.amount = amount;
		this.balance = balance;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public BigDecimal getBalance() {
		return balance;
	}
}
