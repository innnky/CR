package xyz.innky.graduationproject.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {
    static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    static DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String  date2string(Date date){
        return dateFormat.format(date);
    }
    public static Date string2date(String st){
        try {
            return dateFormat.parse(st);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String datetime2string(Date date){
        return dateTimeFormat.format(date);
    }
    public static Date string2datetime(String datetime){
        try {
            return dateTimeFormat.parse(datetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
