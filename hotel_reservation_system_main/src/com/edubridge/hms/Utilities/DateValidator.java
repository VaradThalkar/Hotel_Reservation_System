package com.edubridge.hms.Utilities;

import java.text.ParseException;

public interface DateValidator {
	boolean isValid(String dateStr);
	boolean currentDate(String date);
	boolean compareDates(String d1,String d2);
}
