package com.softeam.kata.test.operation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.softeam.kata.model.Transaction;
import com.softeam.kata.services.LineFormatter;
@ExtendWith(MockitoExtension.class)
class LineTemplate {

	@Test
    public void format_a_statement_line() {

        LineFormatter lineFormatter = new LineFormatter();

        LocalDateTime transactionTime = LocalDateTime.of(2020, 7, 10, 15, 0);
        BigDecimal transactionAmount = BigDecimal.valueOf(100L);
        Transaction transaction = new Transaction(
                Transaction.Type.DEPOSIT, transactionTime, transactionAmount, transactionAmount);

        assertEquals("DEPOSIT | 10/07/2020 | 100.00 | 100.00", lineFormatter.format(transaction));
    }

}
