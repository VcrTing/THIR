package com.example.utils.basic;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class QTypedUtil {

    public static boolean isString(Object obj) {
        if (obj == null) return false;
        return obj.getClass().equals(String.class);
    }

    public static boolean isNoNull(Object obj) {
        return (obj == null);
    }

    public static String serStr(Object obj, Object def) { return (obj == null) ? def.toString() : obj.toString(); }
    public static String serStr(BigDecimal src) { return serStr(src, "0.00"); }

    /**
    * 数字类
    * @params
    * @return
    */

    public static <T> T serNumber(Object src, T def, Class<T> numberClass) {
        try {
            if (src == null) return def;
            Method method = numberClass.getDeclaredMethod("valueOf", String.class);
            Object res = method.invoke(null, src.toString());
            return (T) res;
        } catch (Exception ignored) {  } return def;
    }

    public static Long serLong(Object src, Long def) {
        return serNumber(src, def, Long.class);
    }
    public static Long serLong(Object src) {
        return serLong(src, null);
    }

    public static Integer serInt(Object src, Integer def) {
        return serNumber(src, def, Integer.class);
    }
    public static Integer serInt(Object src) { return serInt(src, null); }

    public static Short serShort(Object src, Short def) {
        return serNumber(src, def, Short.class);
    }
    public static Short serShort(Object src) {
        return serShort(src, null);
    }

    /**
    * 多重 不为空
    * @params
    * @return
    */
    public static Boolean has(Object ...src) {
        for (Object o: src) { if (o == null) return false; }
        return true;
    }

    public static Boolean hasLong(Long ...src) {
        for (Long o: src) { if (o == null || serLong(o) == null) return false; }
        return true;
    }

    public static Boolean isLong(Object src) {
        try {
            Long.valueOf(src.toString()); return true;
        } catch (Exception ignored) {  } return false;
    }

    /**
    * 时间
    * @params
    * @return
    */
    static String DEF_FMT = "yyyy-MM-dd";
    static String DEF_FMT_LONG = DEF_FMT + " HH:mm:ss";
    public static Date serDate(Object dateString, boolean isExactDate) {
        try {
            String src = dateString.toString().trim();
            return new SimpleDateFormat(isExactDate ? DEF_FMT_LONG : DEF_FMT).parse(src); }
        catch (Exception ignored) { } return null;
    }
    public static String serStr(Date date, boolean isExactDate) {
        try {
            return new SimpleDateFormat(isExactDate ? DEF_FMT_LONG : DEF_FMT).format(date); }
        catch (Exception ignored) { } return null;
    }
}
