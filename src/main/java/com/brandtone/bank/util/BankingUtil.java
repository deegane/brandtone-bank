package com.brandtone.bank.util;

import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;

public class BankingUtil {
	
	// Date for Mini Statement
	public static final Date MINI_STATEMENT_START_DATE;
	static {
		Calendar cal = Calendar.getInstance();
		DateTime statementDate = new DateTime(cal).minusMonths(1);
		MINI_STATEMENT_START_DATE = statementDate.toDate();
	}

}
