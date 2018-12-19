package com.ljb.utils;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/10/16<br>
 * 描述: <br>
 */
public class ExceptionUtils {

    public static String getStackMsg(Exception e) {

        StringBuffer sb = new StringBuffer();
        StackTraceElement[] stackArray = e.getStackTrace();
        for (int i = 0; i < stackArray.length; i++) {
            StackTraceElement element = stackArray[i];
            sb.append(element.toString() + "\n");
        }
        return sb.toString();
    }

    public static String getStackMsg(Throwable e) {

        StringBuffer sb = new StringBuffer();
        StackTraceElement[] stackArray = e.getStackTrace();
        for (int i = 0; i < stackArray.length; i++) {
            StackTraceElement element = stackArray[i];
            sb.append(element.toString() + "\n");
        }
        return sb.toString();
    }

    public static String getStackMsg(StackTraceElement[] stackTraceElements) {

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < stackTraceElements.length; i++) {
            StackTraceElement element = stackTraceElements[i];
            sb.append(element.toString() + "\n");
        }
        return sb.toString();
    }
}
