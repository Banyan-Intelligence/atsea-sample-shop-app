package com.docker.atsea.util;

public class RequestContextHolder {
    private static final ThreadLocal<Long> requestTime = new ThreadLocal<>();

    public static void setRequestTime() {
        requestTime.set(System.currentTimeMillis());
    }

    public static long getRequestTime() {
        return requestTime.get();
    }

    public static void clearRequestTime() {
        requestTime.remove();
    }
}
