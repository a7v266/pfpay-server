package ru.pfpay.hibernate;

import org.hibernate.ScrollableResults;

import java.util.Iterator;

public class EntityScroll<T> implements Scroll<T> {

    private ScrollableResults results;

    public EntityScroll() {
    }

    public EntityScroll(ScrollableResults results) {
        this.results = results;
    }

    @Override
    public Iterator<T> iterator() {
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
    public T next() {
        if (results == null) {
            return null;
        }
        return (T) results.get(0);
    }

    @Override
    public void close() {
        if (results != null) {
            results.close();
        }
    }
}
