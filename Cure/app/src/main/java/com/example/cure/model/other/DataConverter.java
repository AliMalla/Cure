package com.example.cure.model.other;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * A class for converting a certain data from one format to another
 *
 * @author Ali Alkhaled
 */
public class DataConverter {

    /**
     * A method to convert a string to a Calendar object
     * @param dateString string to be converted in format yyyymmdd
     * @return date
     */
    public static Calendar stringToDate(String dateString){
        final int year = Integer.parseInt(dateString.substring(0, 4));
        final int month = Integer.parseInt(dateString.substring(4, 6));
        final int day = Integer.parseInt(dateString.substring(6));

        Calendar date = new GregorianCalendar(year, month, day);
        return date;
    }


    /**
     * A method to convert a Calendar object (date) into a string
     * @param date the date to be converted
     * @return string in format yyyymmdd
     */
    public static String dateToString(Calendar date){
        final int year = date.get(Calendar.YEAR);
        final int month = date.get(Calendar.MONTH);
        final int day = date.get(Calendar.DATE);

        String mo = ""+month;
        String da = ""+ day;
        if (month < 10)
            mo = ""+0+month;
        if (day < 10)
            da = ""+0+ day;

        String dateStr = ""+year;
        StringBuilder res = new StringBuilder(dateStr);
        res.append(mo);
        res.append(da);

        return res.toString();
    }
}
