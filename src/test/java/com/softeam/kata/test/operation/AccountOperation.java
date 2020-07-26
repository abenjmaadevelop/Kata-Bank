package com.softeam.kata.test.operation;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.softeam.kata.model.InMemoryTransactions;
import com.softeam.kata.model.Transaction;
import com.softeam.kata.services.Account;

@ExtendWith(MockitoExtension.class)
class AccountOperation {

	private Account account;

	@Mock
	private InMemoryTransactions transactions;
	
	@BeforeEach
	 void init(@Mock InMemoryTransactions transactions) {
		account = new Account(BigDecimal.valueOf(100L), transactions);
	}

	@Test
	public void init_a_transaction_with_a_positive_amount_for_a_deposit() {
		when(transactions.lastBalance()).thenReturn(BigDecimal.valueOf(50L));
		account.deposit(BigDecimal.valueOf(100L));
		verify(transactions, atLeastOnce()).add(any(Transaction.class));
	}

}
