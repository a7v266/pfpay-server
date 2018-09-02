package ru.pfpay.hibernate;

import org.hibernate.ScrollableResults;

import java.util.Iterator;

public class ObjectArrayScroll implements Scroll<Object[]> {

    private ScrollableResults results;

    public ObjectArrayScroll() {
    }

    public ObjectArrayScroll(ScrollableResults results) {
        this.results = results;
    }

    @Override
    public Iterator<Object[]> iterator() {
        return this;
    }

    @Override
    public boolean hasNext() {
        if (results == null) {
            return false;
        }
        return results.next();
    }

    @Override
    public Object[] next() {
        if (results == null) {
            return null;
        }
        return results.get();
    }

    @Override
    public void close() {
        if (results != null) {
            results.close();
        }
    }
}
