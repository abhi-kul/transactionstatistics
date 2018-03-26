package com.transaction.statistics.annotation;

public class CastsUtils {
    public static <T> Class<T> cast(Class<?> target) {
        return (Class<T>) target;
    }
}
