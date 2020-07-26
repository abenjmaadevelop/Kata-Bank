package com.softeam.kata.test.operation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.softeam.kata.exception.NonSufficientFundsException;
import com.softeam.kata.model.InMemoryTransactions;
import com.softeam.kata.model.Transaction;
import com.softeam.kata.services.Account;

@ExtendWith(MockitoExtension.class)
class AccountOperation {

	private Account account;

	@Mock
	private InMemoryTransactions transactions;

	@BeforeEach
	void init() {
		account = new Account(BigDecimal.valueOf(100L), transactions);
	}

	@Test
	public void init_a_transaction_with_a_positive_amount_for_a_deposit() {
		when(transactions.lastBalance()).thenReturn(BigDecimal.valueOf(50L));
		account.deposit(BigDecimal.valueOf(100L));
		verify(transactions, atLeastOnce()).add(any(Transaction.class));
	}

	@Test
	public void init_a_transaction_with_a_negative_amount_for_a_deposit() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> account.deposit(BigDecimal.valueOf(-100L)));
	}

	@Test
	public void init_a_transaction_with_a_null_amount_for_a_deposit() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> account.deposit(BigDecimal.ZERO));
	}

	@Test
	public void init_a_transaction_with_a_positive_amount_equals_to_the_balance_for_a_withdraw()
			throws NonSufficientFundsException {
		when(transactions.lastBalance()).thenReturn(BigDecimal.valueOf(100L));
		account.withdraw(BigDecimal.valueOf(50L));
		verify(transactions, times(2)).add(any(Transaction.class));
	}

	@Test
	public void init_a_transaction_with_a_positive_amount_greater_than_the_balance_for_a_withdraw()
			throws NonSufficientFundsException {
		BigDecimal openingAmount = BigDecimal.valueOf(100L);
		when(transactions.lastBalance()).thenReturn(openingAmount);
		account.withdraw(openingAmount);
		Assertions.assertThrows(NonSufficientFundsException.class,
				() -> account.withdraw(openingAmount.add(BigDecimal.TEN)));
	}

	@Test
	public void init_a_transaction_with_a_negative_amount_for_a_withdraw() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> account.withdraw(BigDecimal.valueOf(-100L)));
	}

	@Test
	void create_a_statement_with_transactions() {
		LocalDateTime transactionOneTime = LocalDateTime.of(2020, 7, 9, 15, 0);
		BigDecimal transactionOneAmount = BigDecimal.valueOf(100L);
		Transaction transactionOne = new Transaction(Transaction.Type.DEPOSIT, transactionOneTime, transactionOneAmount,
				transactionOneAmount);

		LocalDateTime transactionTwoTime = LocalDateTime.of(2020, 7, 10, 10, 0);
		BigDecimal transactionTwoAmount = BigDecimal.valueOf(50L);
		BigDecimal transactionTwoBalance = transactionOneAmount.add(transactionTwoAmount);
		Transaction transactionTwo = new Transaction(Transaction.Type.WITHDRAW, transactionTwoTime,
				transactionTwoAmount, transactionTwoBalance);

		List<Transaction> transactionsList = Stream.of(transactionOne, transactionTwo).collect(Collectors.toList());

		assertEquals("operation | date       | amount | balance" + "\nDEPOSIT | 09/07/2020 | 100,00 | 100,00"
				+ "\nWITHDRAW | 10/07/2020 | 50,00 | 150,00", Account.printHistory(transactionsList));
	}
}
