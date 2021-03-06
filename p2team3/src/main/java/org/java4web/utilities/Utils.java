package org.java4web.utilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static final String dateTimeFormat = "yyyy/MM/dd HH:mm";
    public static final DateFormat dateFormat = new SimpleDateFormat(dateTimeFormat);

    public static Date dateFormatParse(String dateText) {
        if(dateText==null || dateText.isEmpty()){
            return null;
        }

        try {
            return dateFormat.parse(dateText);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date getToday(){
        return new Date();
    }
}
