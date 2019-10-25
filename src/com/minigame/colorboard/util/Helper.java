package com.minigame.colorboard.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Helper {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");

    public static int random(int min, int max) {

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public static void logIt(boolean isDebug, String message) {
        if (isDebug) {
            logIt(message);
        }
    }

    public static void logIt(String message) {
        String messagePrefix = String.format("[%1$-23s] %2$-15s %3$-50s- ", dateFormat.format(new Date()), Thread.currentThread().getName(), getCallingMethod());

        System.out.println(String.format("%s%s", messagePrefix, message));
    }

    private static String getCallingMethod() {
        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
        StackTraceElement e = stacktrace[3];  //2 is the caller of this method, 3 is the caller of that method
        String className = e.getClassName();
        int lastIndex = className.lastIndexOf('.');
        if (lastIndex >= 0) {
            className = className.substring(lastIndex + 1);
        }
        String methodName = e.getMethodName();
        return className + "." + methodName;
    }


}
