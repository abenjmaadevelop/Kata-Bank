package com.softeam.kata.services;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.softeam.kata.model.Transaction;

public class LineFormatter {

	private static final String DATE_PATTERN = "dd/MM/yyyy";
	private static final String SEPARATOR = " | ";

	public String format(Transaction line) {
		return line.getType() + SEPARATOR + dateFormat(line.getTime()) + SEPARATOR
				+ decimalFormat(line.getAmount()) + SEPARATOR
				+ decimalFormat(line.getBalance());
	}
	
	private String dateFormat(LocalDateTime date) {
		return date.format(DateTimeFormatter.ofPattern(DATE_PATTERN));
	} 
	
	private String decimalFormat(BigDecimal amount) {
		return new DecimalFormat("0.00" , new DecimalFormatSymbols(Locale.US)).format(amount);
	}
}
