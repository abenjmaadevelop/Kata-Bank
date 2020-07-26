package com.softeam.kata.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.softeam.kata.exception.NonSufficientFundsException;
import com.softeam.kata.model.InMemoryTransactions;
import com.softeam.kata.model.Transaction;

public class Account {

	private final InMemoryTransactions transactions;

	public Account(BigDecimal initAmount, InMemoryTransactions transactions) {
		if (initAmount.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("Cannot create account with negative balance");
		}
		this.transactions = transactions;
		this.transactions.add(new Transaction(Transaction.Type.DEPOSIT, LocalDateTime.now(), initAmount, initAmount));
	}

	public void deposit(BigDecimal amount) {
		if (amount.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("Cannot deposit a negative or null amount");
		}
		BigDecimal previousBalance = transactions.lastBalance();
		transactionRecord(Transaction.Type.DEPOSIT, amount, previousBalance);

	}

	public void withdraw(BigDecimal amount) throws NonSufficientFundsException {
		BigDecimal previousBalance = transactions.lastBalance();

		if (amount.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("Cannot withdraw a negative or null amount");
		}

		if (previousBalance.compareTo(amount) < 0) {
			throw new NonSufficientFundsException("Insufficient funds");
		}
		transactionRecord(Transaction.Type.WITHDRAW, amount, previousBalance);

	}

	private void transactionRecord(Transaction.Type type, BigDecimal amount, BigDecimal previousBalance) {
		Transaction transaction = new Transaction(type, LocalDateTime.now(), amount.multiply(BigDecimal.valueOf(-1L)),
				previousBalance.subtract(amount));
		transactions.add(transaction);
	}

	public static String printHistory(List<Transaction> transactionsList) {
		StringBuilder line = new StringBuilder("operation | date       | amount | balance");
		String tranLines = transactionsList.stream().map(transaction -> new LineFormatter().format(transaction))
				.collect(Collectors.joining("\n"));
		return line.append("\n").append(tranLines).toString();
	}

}
