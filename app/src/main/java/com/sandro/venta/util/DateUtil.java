package com.sandro.venta.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Creado por chambito on 05/11/2015.
 */
public class DateUtil {

    public static SimpleDateFormat dateTimeFormat = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm", Locale.getDefault());
    public static SimpleDateFormat dateFormat = new SimpleDateFormat(
            "yyyy-MM-dd", Locale.getDefault());
    public static SimpleDateFormat dateSimpleFormat = new SimpleDateFormat(
            "yyyyMMdd", Locale.getDefault());
    public static SimpleDateFormat timeFormat = new SimpleDateFormat(
            "HH:mm:ss", Locale.getDefault());
    private static Calendar calendar = Calendar.getInstance();


    public static Date getDateTime(String date){
        try {
            return dateTimeFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getFormatDate(Date date){
        return dateFormat.format(date);
    }

    public static String getFormatDate(Date date, SimpleDateFormat format){
        return format.format(date);
    }

    public static Date getDate(String date){
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getCurrentDate() {
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String getCurrentDateTime() {
        Date date = new Date();
        return dateTimeFormat.format(date);
    }

    public static int getCurrentYear() {
         return calendar.get(Calendar.YEAR);
    }

    public static int getCurrentMonth() {
        return calendar.get(Calendar.MONTH);
    }

    public static int getCurrentDay() {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static String getDateFromDialogDatePicker(int year, int month, int day){
        Calendar calendarPick = Calendar.getInstance();
        calendarPick.set(year, month, day);
        return dateFormat.format(calendarPick.getTime());
    }
}
