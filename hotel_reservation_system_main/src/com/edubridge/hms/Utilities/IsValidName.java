package com.edubridge.hms.Utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IsValidName {
	public static boolean isValidname(String name)
    {
        String regex = "^[A-Z][a-z]{2,}(?: [A-Z][a-z]*)*$";
        Pattern p = Pattern.compile(regex);
        if (name == null) {
            return false;
        }
        Matcher m = p.matcher(name);
        return m.matches();
    }
}
