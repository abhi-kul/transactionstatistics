package com.transaction.statistics.exception;

public class InvalidTimestampException extends IllegalArgumentException {
    public InvalidTimestampException(String message) {
        super(message);
    }

    public static void check(boolean condition, String message, Object... args) {
        if (!condition) {
            throw new InvalidTimestampException(String.format(message, args));
        }
    }
}
