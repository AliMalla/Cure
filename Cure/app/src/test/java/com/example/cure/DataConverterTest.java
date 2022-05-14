package com.example.cure;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.cure.model.other.DataConverter;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * A test class for DataConverter.java
 *
 * @author Ali Alkhaled
 */
public class DataConverterTest {


    @Test
    public void stringToDateTest() {
        Calendar expected = new GregorianCalendar(2022, 03, 01);
        Calendar res = DataConverter.stringToDate("20220301");

        assertEquals(expected, res);
    }

    @Test
    public void dateToStringTest() {
        Calendar calendar = new GregorianCalendar(2021, 9, 4);

        String expected = "20210904";
        String res = DataConverter.dateToString(calendar);

        assertEquals(expected, res);
    }

    @Test
    public void stringToDateTestComplex(){
        Calendar expected = new GregorianCalendar(2020, 8, 9);
        Calendar res = DataConverter.stringToDate("20200809");

        assertEquals(expected, res);
    }


    @Test
    public void dateToStringTestComplex(){
        Calendar calendar = new GregorianCalendar(2019, 04, 03);

        String expected = "20190403";
        String res = DataConverter.dateToString(calendar);

        assertEquals(expected, res);

    }
}