package com.kata.bankaccount.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.kata.bankaccount.utils.DateFormat;

@RunWith(MockitoJUnitRunner.class)
public class DateFormatTest {

	private DateFormat dateFormat;
	@Before
	public void initialize() {
		dateFormat = new DateFormat();
	}

	@Test
	public void return_date_with_format_ddMMyyyy() {
		Date date = new Date();
		
		assertThat(dateFormat.formatDateToString(date), is(equalTo("27/03/2019")));
	}

}
