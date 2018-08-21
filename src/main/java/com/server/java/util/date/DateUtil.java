package com.server.java.util.date;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author qiwenshuai
 * @note
 * @since 18-8-21 09:30 by jdk 1.8
 */
public class DateUtil {

    /**
     * 锁对象
     */
    private static final Object lockObj = new Object();

    /**
     * 存放不同的日期模板格式的sdf的Map
     */
    private static Map<String, ThreadLocal<SimpleDateFormat>> sdfMap = new HashMap<>();


    private static Logger logger = LoggerFactory.getLogger(DateUtil.class);


    //缺省日期格式
    private static final String DATE_DEFAULT_FORMAT = "yyyy-MM-dd";
    //全日期格式
    private static final String DATE_FULL_FORMAT = "yyyy-MM-dd HH:mm:ss";
    //全日期格式无秒
    private static final String DATE_NO_SS_FORMAT = "yyyy-MM-dd HH:mm";

    /**
     * 返回一个ThreadLocal的sdf,每个线程只会new一次sdf
     *
     * @param pattern
     * @return
     */
    private static SimpleDateFormat getSdf(String pattern) {
        ThreadLocal<SimpleDateFormat> tl = sdfMap.get(pattern);
        // 此处的双重判断和同步是为了防止sdfMap这个单例被多次put重复的sdf
        if (tl == null) {
            synchronized (lockObj) {
                tl = sdfMap.get(pattern);
                if (tl == null) {
                    // 只有Map中还没有这个pattern的sdf才会生成新的sdf并放入map
                    logger.info("put new sdf of pattern " + pattern + " to map");
                    // 这里是关键,使用ThreadLocal<SimpleDateFormat>替代原来直接new SimpleDateFormat
                    tl = ThreadLocal.withInitial(() -> {
                        logger.info("thread: " + Thread.currentThread() + " init pattern: " + pattern);
                        return new SimpleDateFormat(pattern);
                    });
                    sdfMap.put(pattern, tl);
                }
            }
        }
        return tl.get();
    }


    /*
     *时间转字符串
     */
    public static String dateToString(Date date, String pattern) {
        if (pattern == null || "".equals(pattern)) {
            pattern = DATE_FULL_FORMAT;
        }
        if (date != null) {
            return getSdf(pattern).format(date);
        } else {
            return null;
        }
    }

    public static String dateToString(Date date) {
        return dateToString(date, null);
    }

    /*
     *字符串转时间
     */
    public static Date stringToDate(String date, String pattern) throws ParseException {
        if (pattern == null || "".equals(pattern)) {
            pattern = DATE_FULL_FORMAT;
        }
        return getSdf(pattern).parse(date);
    }


    /*
     *获取当天日期
     */
    public static String getToday(String pattern) {
        return dateToString(new Timestamp(System.currentTimeMillis()), pattern);
    }


    public static void main(String[] args) {
    }
}
