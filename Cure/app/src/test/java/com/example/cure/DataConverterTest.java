package com.example.cure;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.cure.model.other.DataConverter;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class DataConverterTest {


    @Test
    public void dateToStringTest() {
        Calendar expected = new GregorianCalendar(2022, 03, 01);
        Calendar res = DataConverter.stringToDate("20220301");

        assertEquals(expected, res);
    }

    @Test
    public void stringToDateTest(){
        Calendar calendar = new GregorianCalendar(2021, 9, 4);

        String expected = "20210913";
        String res = DataConverter.dateToString(calendar);

        assertEquals(expected, res);
    }
}