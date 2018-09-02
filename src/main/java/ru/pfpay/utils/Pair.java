package ru.pfpay.utils;

import java.util.function.Consumer;

public class Pair<FIRST, SECOND> {

    private final FIRST first;
    private final SECOND second;

    public Pair(FIRST first, SECOND second) {
        this.first = first;
        this.second = second;
    }

    public FIRST getFirst() {
        return first;
    }

    public SECOND getSecond() {
        return second;
    }


    public Pair<FIRST, SECOND> handleFirst(Consumer<FIRST> consumer) {
        consumer.accept(first);
        return this;
    }

    public Pair<FIRST, SECOND> handleSecond(Consumer<SECOND> consumer) {
        consumer.accept(second);
        return this;
    }

    public Pair<SECOND, FIRST> swap() {
        return new Pair<>(second, first);
    }

    @Override
    public String toString() {
        return "[" + first + ", " + second + "]";
    }

    public static <A, B> Pair<A, B> pair(A a, B b) {
        return new Pair<>(a, b);
    }
}