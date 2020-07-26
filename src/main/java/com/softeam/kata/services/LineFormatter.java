package com.softeam.kata.services;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.softeam.kata.model.Transaction;

public class LineFormatter {

	public String format(Transaction line) {
		return line.getType() + " | " + line.getTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " | "
				+ new DecimalFormat("0.00" , new DecimalFormatSymbols(Locale.US)).format(line.getAmount()) + " | "
				+ new DecimalFormat("0.00" , new DecimalFormatSymbols(Locale.US)).format(line.getBalance());
	}
}
