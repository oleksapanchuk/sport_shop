package dev.oleksa.sportshop.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtils {
    public static Date getDate(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date = dateFormat.parse(dateString);
            System.out.println("Parsed Date: " + date);
            return date;
        } catch (ParseException e) {
            throw new ParseException(e.getMessage(), e.getErrorOffset());
        }
    }
}
