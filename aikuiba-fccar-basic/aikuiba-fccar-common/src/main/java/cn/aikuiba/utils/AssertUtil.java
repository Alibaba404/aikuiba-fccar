package cn.aikuiba.utils;


import cn.aikuiba.constants.Constants;
import cn.aikuiba.constants.ErrorCode;
import cn.aikuiba.exception.GlobalException;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//断言工具
public class AssertUtil {

    //手机的正则表达式
    public static void isPhone(String phone, ErrorCode errorCode) {
        Pattern CHINA_PATTERN_PHONE = Pattern.compile(Constants.CHINA_PHONE_REGEX);
        Matcher m = CHINA_PATTERN_PHONE.matcher(phone);
        if (!m.matches()) {
            throw GlobalException.me(errorCode);
        }
    }


    //字符串不为空
    public static void isNotEmpty(String text, ErrorCode errorCode) {
        if (text == null || text.trim().length() == 0) {
            throw GlobalException.me(errorCode);
        }
    }

    //列表不为空
    public static void isNotEmpty(List list, ErrorCode errorCode) {
        if (list == null || list.size() == 0) {
            throw GlobalException.me(errorCode);
        }
    }

    //对象为空
    public static void isNull(Object obj, ErrorCode errorCode) {
        if (obj != null) {
            throw GlobalException.me(errorCode);
        }
    }

    //对象不为空
    public static void isNotNull(Object obj, ErrorCode errorCode) {
        if (obj == null) {
            throw GlobalException.me(errorCode);
        }
    }

    //断言为false
    public static void isFalse(boolean isFalse, ErrorCode errorCode) {
        if (isFalse) {
            throw GlobalException.me(errorCode);
        }
    }

    //断言为false
    public static void isTrue(boolean isTrue, ErrorCode errorCode) {
        if (!isTrue) {
            throw GlobalException.me(errorCode);
        }
    }

    //int值比较
    public static void isEquals(int s1, int s2, ErrorCode errorCode) {
        if (s1 != s2) {
            throw GlobalException.me(errorCode);
        }
    }

    //是否包含
    public static void isIn(Integer s1, List<Integer> s2, ErrorCode errorCode) {
        if (!s2.contains(s1)) {
            throw GlobalException.me(errorCode);
        }
    }

    //字符串比较
    public static void isEquals(String s1, String s2, ErrorCode errorCode) {
        isNotEmpty(s1, ErrorCode.PARAM_STRING_EMPTY);
        isNotEmpty(s2, ErrorCode.PARAM_STRING_EMPTY);
        if (!s1.equals(s2)) {
            throw GlobalException.me(errorCode);
        }
    }

    //字符串去空格比较
    public static void isEqualsTrim(String s1, String s2, ErrorCode errorCode) {
        isNotEmpty(s1, ErrorCode.PARAM_STRING_EMPTY);
        isNotEmpty(s2, ErrorCode.PARAM_STRING_EMPTY);
        if (!s1.trim().equals(s2.trim())) {
            throw GlobalException.me(errorCode);
        }
    }

    //字符串忽略大小写比较
    public static void isEqualsIgnoreCase(String s1, String s2, ErrorCode errorCode) {
        isNotEmpty(s1, ErrorCode.PARAM_STRING_EMPTY);
        isNotEmpty(s2, ErrorCode.PARAM_STRING_EMPTY);
        if (!s1.trim().equalsIgnoreCase(s2.trim())) {
            throw GlobalException.me(errorCode);
        }
    }

}
