package com.kata.bankaccount.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {

	public String formatDateToString(Date date) {
		String pattern = "dd/MM/yyyy";
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		
		return df.format(date);
	}
	
}
