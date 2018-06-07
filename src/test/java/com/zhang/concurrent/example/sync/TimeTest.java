package com.zhang.concurrent.example.sync;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * @author jiwei.zhang
 * @DATE 2018-06-07 下午 17:29
 */
public class TimeTest {

    private static String TIME_PREFIX = "2018-01-01 ";

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static final SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");

    private static List<Date> getDeptTimeBetween(String depTime, int minutes) throws ParseException {
        Date date = sdf.parse(TIME_PREFIX + depTime);
        Calendar before = Calendar.getInstance(TimeZone.getTimeZone("CTT"));
        before.setTime(date);
        before.add(Calendar.MINUTE, -1 * minutes);

        Calendar last = Calendar.getInstance(TimeZone.getTimeZone("CTT"));
        last.setTime(date);
        last.add(Calendar.MINUTE, minutes);

        Calendar time00 = Calendar.getInstance(TimeZone.getTimeZone("CTT"));
        time00.set(2018, 0, 1, 0, 0);

        Calendar time59 = Calendar.getInstance(TimeZone.getTimeZone("CTT"));
        time59.set(2018, 0, 1, 23, 59);

        if (last.getTime().after(time59.getTime())) {
            last.setTime(time59.getTime());
        }
        if (before.getTime().before(time00.getTime())) {
            before.setTime(time00.getTime());
        }

        return Arrays.asList(before.getTime(),last.getTime());
    }


    public static void main(String[] args) throws ParseException {
        System.out.println("-------------");
        List<Date> localTimes1 = getDeptTimeBetween("00:02",20);
        localTimes1.forEach(date-> System.out.println(sdfTime.format(date)));
        System.out.println("-------------");
        List<Date> localTimes2 = getDeptTimeBetween("10:18",20);
        localTimes2.forEach(date-> System.out.println(sdfTime.format(date)));
        System.out.println("---------------");
        List<Date> localTimes3 = getDeptTimeBetween("23:51",20);
        localTimes3.forEach(date-> System.out.println(sdfTime.format(date)));
    }
}
