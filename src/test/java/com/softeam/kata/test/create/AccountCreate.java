package com.softeam.kata.test.create;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.softeam.kata.model.InMemoryTransactions;
import com.softeam.kata.model.Transaction;
import com.softeam.kata.services.Account;

@ExtendWith(MockitoExtension.class)
public class AccountCreate {

	@Mock
	private InMemoryTransactions transactions;

	@Test
	public void create_an_account_with_a_positive_initial_amount() {
		BigDecimal initAmount = BigDecimal.valueOf(100L);
		new Account(initAmount, transactions);
		verify(transactions).add(any(Transaction.class));
	}
	
	@Test
    public void create_an_account_with_a_negative_initial_amount() {
        BigDecimal initAmount = BigDecimal.valueOf(-100L);
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Account(initAmount, transactions));
    }

}