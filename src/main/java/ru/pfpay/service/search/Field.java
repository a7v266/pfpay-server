package ru.pfpay.service.search;


import ru.pfpay.config.Format;
import ru.pfpay.utils.StringJoiner;

public class Field {

    public static String path(String... names) {
        return StringJoiner.on(Format.DOT).skipEmpty().join(names);
    }
}
