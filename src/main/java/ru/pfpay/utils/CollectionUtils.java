package ru.pfpay.utils;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class CollectionUtils {

    public static <T> Set<T> createHashSet() {
        return new HashSet<>();
    }

    public static <T> Set<T> createHashSet(Collection<T> collection) {
        return new HashSet<>(collection);
    }

    public static <T> Set<T> createTreeSet() {
        return new TreeSet<>();
    }

    public static <T> Set<T> createLinkedHashSet() {
        return new LinkedHashSet<>();
    }

    public static <T> List<T> createArrayList() {
        return new ArrayList<>();
    }

    public static <T> List<T> createLinkedList() {
        return new LinkedList<>();
    }

    public static <T> Set<T> createHashSet(T... items) {
        Set<T> set = createHashSet();
        put(set, items);
        return set;
    }

    public static <T> Set<T> createHashSet(Collection<T>... collections) {
        Set<T> set = createHashSet();
        if (ArrayUtils.isNotEmpty(collections)) {
            for (Collection<T> collection : collections) {
                put(set, collection);
            }
        }
        return set;
    }

    public static <T> Set<T> createHashSet(T item, Collection<T> items) {
        Set<T> set = createHashSet();
        put(set, item);
        put(set, items);
        return set;
    }

    public static <T> Set<T> createTreeSet(T... items) {
        Set<T> set = createTreeSet();
        put(set, items);
        return set;
    }

    public static <T> Set<T> createLinkedHashSet(T... items) {
        Set<T> set = createLinkedHashSet();
        put(set, items);
        return set;
    }

    public static <T> List<T> createArrayList(T... items) {
        List<T> list = createArrayList();
        put(list, items);
        return list;
    }

    public static <T> List<T> createLinkedList(T... values) {
        List<T> list = createLinkedList();
        put(list, values);
        return list;
    }

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.size() == 0;
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return collection != null && collection.size() > 0;
    }

    public static <T> T getFirst(Collection<T> collection) {
        if (isEmpty(collection)) {
            return null;
        }
        return collection.iterator().next();
    }

    public static <T> void put(Collection<T> collection, T item) {
        if (ObjectUtils.isNotEmpty(item)) {
            collection.add(item);
        }
    }

    public static <T> void put(Collection<T> collection, Collection<T> items) {
        if (CollectionUtils.isNotEmpty(items)) {
            items.forEach(item -> put(collection, item));
        }
    }

    public static <T> void put(Collection<T> collection, T... items) {
        if (ArrayUtils.isNotEmpty(items)) {
            for (T item : items) {
                put(collection, item);
            }
        }
    }

    public static <T> void iterate(Collection<T> collection, Consumer<T> consumer) {
        if (isNotEmpty(collection)) {
            collection.forEach(consumer);
        }
    }

    public static <T> Stream<T> stream(Collection<T> collection) {
        if (isEmpty(collection)) {
            return Stream.empty();
        }
        return collection.stream();
    }


    public static <T> void remove(T item, Collection<T> collection) {
        if (item != null && CollectionUtils.isNotEmpty(collection)) {
            collection.remove(item);
        }
    }
}
