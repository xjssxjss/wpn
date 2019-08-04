package com.sean.util;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringUtil {

    /**
     * 正则表达式：验证邮箱
     */
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    /**
     * 判断字符串是否为空
     *
     * @param src
     * @return
     */
    public static boolean isEmpty(String src) {
        return null == src || "".equals(src.trim());
    }

    /**
     * 对String需要进行模糊查询参数进行封装
     * @param param
     * @return
     */
    public static String likeParam(String param){
        if(isEmpty(param)){
            return null;
        }
        return param = "%" + param.trim() + "%";
    }

    /**
     * 对Boolean需要进行模糊查询参数进行封装
     * @param object
     * @return
     */
    public static Integer booleanParam(Object object){
        if(object instanceof String){
            if(isEmpty(object.toString())){
                return null;
            } else {
                return Integer.parseInt(object.toString());
            }
        }
        return 0;
    }

    /**
     * 对下拉框需要查询参数进行封装
     * @param object
     * @return
     */
    public static Integer selectParam(Object object){
        if(null == object){
            return null;
        } else {
            //说明是空字符串
            if("".equalsIgnoreCase(object.toString().trim())){
                return null;
            } else {
                return Integer.parseInt(object.toString());
            }
        }
    }

    /**
     * 对多选下拉框需要查询参数进行封装
     * @param object
     * @return
     */
    public static List mulSelectParam(Object object){
        if(object instanceof List){
            List listSelect = ((List) object);
            if(null != listSelect && listSelect.size()>0){
                return listSelect;
            } else {
                return null;
            }
        }
        return null;
    }

    public static String toStringExcludeNull(Object obj) {
        return obj == null ? "" : obj.toString();
    }
    public static String toStringTrimExcludeNull(Object obj) {
        return obj == null ? "" : obj.toString().trim();
    }
    /**
     * 判断字符串是否为空
     *
     * @param src
     * @return
     */
    public static boolean isEmptyWithTrim(String src) {
        return src == null || "".equals(src.trim());
    }

    /**
     * 将字符串前补0到指定长度
     *
     * @param value
     * @param length
     * @return
     */
    public static String fillZero(String value, int length) {
        if (value == null) {
            return null;
        } else {
            while (value.length() < length) {
                value = "0" + value;
            }
        }
        return value;
    }

    /**
     * 截取指定长度的字符串
     *
     * @param str
     * @param length
     * @return
     */
    public static String truncate(String str, int length) {
        if (!isEmpty(str) && str.length() > length) {
            return str.substring(0, length);
        }
        return str;
    }

    /**
     * 将首字母大写
     *
     * @param str
     * @return
     */
    public static String upcaseFirstLetter(String str) {
        if (str == null) {
            return null;
        }
        String firstChar = str.substring(0, 1);
        return firstChar.toUpperCase() + str.substring(1, str.length());
    }

    /**
     * 按指定长度format数字long
     *
     * @param num
     * @param length
     * @return
     */
    public static String formatLong(long num, int length) {
        String number = Long.toString(num);
        while (number.length() < length) {
            number = "0" + number;
        }
        return number;
    }

    /**
     * 判断字符串是否为半角英文或数字
     *
     * @param str
     * @return
     */
    public static boolean isAllHalfWidth(String str) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int ascii = chars[i];
            if ((ascii >= 48 && ascii <= 57)        //0-9
                    || (ascii >= 65 && ascii <= 90)        //A-Z
                    || (ascii >= 97 && ascii <= 122)) {    //a-z
            } else {
                return false;
            }
        }
        return true;
    }
    /**
     * 判断字符串是否整数
     *
     * @param str
     * @return
     */
    public static boolean isNum(String str) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int ascii = chars[i];
            if ((ascii >= 48 && ascii <= 57))        //0-9)
            {
            } else {
                return false;
            }
        }
        return true;
    }
    /**
     * 判断字符串是否小数
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("\\d+\\.\\d+$|-\\d+\\.\\d+$");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }
    public static String toString(String str) {
        return str == null ? "" : str;
    }

    public static String toString(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    /**
     * 将字符串数组按指定分隔符转为字符串
     *
     * @param arr
     * @param seperator
     * @return
     */
    public static String convertArrayToString(String[] arr, String seperator) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            sb.append(seperator + arr[i]);
        }
        if (sb.length() > 0)
            sb.deleteCharAt(0);
        return sb.toString();
    }

    /**
     * Return the string of exception
     *
     * @param t
     * @return
     * @throws IOException
     */
    public static String exceptionToString(Throwable t) throws IOException {
        if (t == null) return null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            t.printStackTrace(new PrintStream(baos));
        } finally {
            baos.close();
        }

        String sb = baos.toString();
        if (sb.length() > 5000) {
            return sb.substring(0, 5000);
        }
        return sb;
    }

    /**
     * 将字符串按指定的分隔符分隔成数组
     *
     * @param str
     * @param separator
     * @return
     */
    public static String[] split(String str, String separator) {
        return StringUtils.split(str, separator);
    }

    /**
     * 获取字符串被特定字符分割后的最后一个元素
     */
    public static String getLastSperator(String str, String sperator) {
        String ss[] = split(str, sperator);
        return ss[ss.length - 1];
    }

    /**
     * 判断传入的字符串是否可以被解析为数字
     */
    public static boolean isNumberString(String s) {
        try {
            Double.valueOf(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String cutLastSperator(String s, String sperator) {
        return s.substring(0, s.length() - getLastSperator(s, sperator).length() - sperator.length());
    }

    /**
     * 截取字符串中以除某个字符串结尾的所有字符
     */
    public static String cutSperatorLast(String s, String sperator) {
        if (s == null || s.length() == 0)
            return s;
        if (s.substring(s.length() - sperator.length()).equals(sperator)) {
            return cutSperatorLast(s.substring(0, s.length() - 1), sperator);
        }
        return s;
    }

    public static String toUtf8String(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 0 && c <= 255) {
                sb.append(c);
            } else {
                byte[] b;
                try {
                    b = Character.toString(c).getBytes("utf-8");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    b = new byte[0];
                }
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0)
                        k += 256;
                    sb.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }

    /**
     * 根据不同浏览器将文件名中的汉字转为UTF8编码的串,以便下载时能正确显示另存的文件名.
     *
     * @param s 原文件名
     * @return 重新编码后的文件名
     */
    public static String toUtf8String(HttpServletRequest request, String s) {
        String agent = request.getHeader("User-Agent");
        try {
            boolean isFireFox = (agent != null && agent.toLowerCase().indexOf("firefox") != -1);
            if (isFireFox) {
//                s = new String(s.getBytes("UTF-8"), "ISO8859-1");
            } else {
                s = StringUtil.toUtf8String(s);
                if ((agent != null && agent.indexOf("MSIE") != -1)) {
                    if (s.length() > 150) {
                        // 根据request的locale 得出可能的编码
                        s = new String(s.getBytes("UTF-8"), "ISO8859-1");
                    }
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return s;
    }

    /**
     * 把Json字符串中属性值为空字符串的值替换成null
     * 例如："bespeakTakeTime": "", 替换成 "bespeakTakeTime": null,
     *
     * @param sourceString
     * @param properties
     * @return
     */
    public static String replaceByProperties(String sourceString, String properties) {
        String targetString = "";
        String[] stringArray=properties.split(",");
        for (int i = 0; i < stringArray.length; i++) {
            String s = stringArray[i];
            String sourceSubStr = "\"" + s + "\":\"\",";
            String targetSubStr = "\"" + s + "\": null,";
            targetString = sourceString.replace(sourceSubStr, targetSubStr);
        }
        return targetString;
    }

    /**
     * 通过指定字符替换为目标字符
     * @param sourceStr
     * @param oldChar
     * @param targetChar
     * @return
     */
    public static String replaceStr(String sourceStr,String oldChar , String targetChar){
        if(!isEmpty(sourceStr)){
            return sourceStr.replaceAll(oldChar , targetChar);
        }
        return null;
    }

    /**
     * 把String List转成逗号分隔的String
     *
     * @param stringList
     * @return
     */
    public static String listToString(List<Object> stringList) {
        if (stringList == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        boolean flag = false;

        for (Object str : stringList) {
            if(str instanceof Integer){
                if (flag) {
                    result.append(",");
                } else {
                    flag = true;
                }
            } else {
                if (flag) {
                    result.append(",");
                } else {
                    flag = true;
                }
            }
            result.append(str);
        }
        return result.toString();
    }

    /**
     * 校验邮箱
     *
     * @param email
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isEmail(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }

    /**
     * 校验邮箱
     *
     * @param emailMulti
     * @return 逗号分隔的多个邮件地址都校验通过返回true，否则返回false
     */
    public static boolean isEmailMulti(String emailMulti) {
        String[] emailAttr = emailMulti.split(",");
        boolean isEmail = true;
        for (String email : emailAttr) {
            if (!isEmail(email)) {
                isEmail = false;
            }
        }
        return isEmail;
    }

}
