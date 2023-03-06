package com.edubridge.hms.Utilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateValidatorUsingDateFormat implements DateValidator {
	private String dateFormat;

	public DateValidatorUsingDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	@Override
	public boolean isValid(String dateStr) {
		DateFormat sdf = new SimpleDateFormat(this.dateFormat);
		sdf.setLenient(false);
		try {
			sdf.parse(dateStr);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	public boolean currentDate(String date) {
		Date enteredDate = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			enteredDate = sdf.parse(date);
		} catch (Exception ex) {
			// enteredDate will be null if date="287686";
		}
		Date currentDate = new Date();
		if (enteredDate.after(currentDate)) {
			return true;
		} else {
			System.out.println("InValid Date!");
			return false;
		}
	}

	public boolean compareDates(String d1, String d2) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// dates to be compare
		try {
			Date date1 = sdf.parse(d1);
			Date date2 = sdf.parse(d2);

			if (date1.compareTo(date2) < 0) {
				return true;
			} else if (date1.compareTo(date2) > 0) {
				System.out.println();
				return false;
			} else if (date1.compareTo(date2) == 0) {
				System.out.println("Both dates cannot be same");
				return false;
			}
		} catch (ParseException e) {
			System.out.println(e);
		}
		return false;
	}
}
