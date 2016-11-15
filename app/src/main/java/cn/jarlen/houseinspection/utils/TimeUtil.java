package cn.jarlen.houseinspection.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * DESCRIBE:
 * Created by hjl on 2016/11/15.
 */

public class TimeUtil {

    public static String getDateByFormat(long timeMillis, SimpleDateFormat dateFormat) {
        Date time = new Date(timeMillis);
        String dateString = dateFormat.format(time);
        return dateString;
    }
}
