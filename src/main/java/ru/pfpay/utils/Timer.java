package ru.pfpay.utils;

public class Timer {

    private long start = System.currentTimeMillis();
    private Long stop;

    private static final String STRING_FORMAT = "elapsed time: %d ms";

    public void stop() {
        stop = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        if (stop == null) {
            stop();
        }

        long elapsed = stop - start;

        return String.format(STRING_FORMAT, elapsed);
    }
}
