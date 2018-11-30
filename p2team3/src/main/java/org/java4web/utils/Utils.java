package org.java4web.utils;

import org.java4web.exceptions.CreateRecordException;
import org.java4web.exceptions.ExceptionMessages;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {
    public static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    public static Date date = new Date();

    public static Date dateFormatParse(String dateText) {
        try {
            return dateFormat.parse(dateText);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String dateFormatParse() {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
       return dateFormat.format(date);

    }
    public static String dateFormatParse(Date date) {

        return dateFormat.format(date);

    }


}
