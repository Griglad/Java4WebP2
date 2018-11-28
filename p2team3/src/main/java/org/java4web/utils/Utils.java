package org.java4web.utils;

import org.java4web.exceptions.CreateRecordException;
import org.java4web.exceptions.ExceptionMessages;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    public static Date dateFormatParse(String dateText) {
        try {
            return dateFormat.parse(dateText);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
