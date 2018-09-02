package ru.pfpay.utils;


import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class DecimalFormatter {

    private static final String EMPTY_STRING = "";
    private static final char SPACE = ' ';
    private static final char COMMA = ',';
    private static final char DOT = '.';

    private static final String DECIMAL_PATTERN = "0.00000000";

    private static final String MONEY_PATTERN = "#,##0.00";
    private static final String VOLUME_PATTERN = "#,##0.00000000";

    private static final ThreadLocal<DecimalFormat> DECIMAL_FORMATTER = ThreadLocal.withInitial(
            () -> createDecimalFormat(DECIMAL_PATTERN, DOT));

    private static final ThreadLocal<DecimalFormat> MONEY_FORMATTER = ThreadLocal.withInitial(
            () -> createDecimalFormat(MONEY_PATTERN, COMMA, SPACE));

    private static final ThreadLocal<DecimalFormat> VOLUME_FORMATTER = ThreadLocal.withInitial(
            () -> createDecimalFormat(VOLUME_PATTERN, COMMA, SPACE));


    public static String format(BigDecimal value) {
        if (value == null) {
            return EMPTY_STRING;
        }
        return DECIMAL_FORMATTER.get().format(value);
    }


    public static String formatMoney(BigDecimal value) {
        if (value == null) {
            return EMPTY_STRING;
        }
        return MONEY_FORMATTER.get().format(value);
    }

    public static String formatVolume(BigDecimal value) {
        if (value == null) {
            return EMPTY_STRING;
        }
        return VOLUME_FORMATTER.get().format(value);
    }


    public static DecimalFormat createDecimalFormat(String pattern, char decimalSeparator) {

        DecimalFormatSymbols decimalFormatSymbols = DecimalFormatSymbols.getInstance();
        decimalFormatSymbols.setDecimalSeparator(decimalSeparator);

        DecimalFormat decimalFormat = new DecimalFormat(pattern, decimalFormatSymbols);
        decimalFormat.setParseBigDecimal(true);
        //decimalFormat.setMaximumFractionDigits(8);
        //decimalFormat.setMinimumFractionDigits(0);

        return decimalFormat;
    }

    public static DecimalFormat createDecimalFormat(String pattern, char decimalSeparator, char groupingSeparator) {

        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setGroupingSeparator(groupingSeparator);
        decimalFormatSymbols.setDecimalSeparator(decimalSeparator);

        DecimalFormat decimalFormat = new DecimalFormat(pattern, decimalFormatSymbols);
        decimalFormat.setParseBigDecimal(true);

        return decimalFormat;
    }

    public static String format(BigDecimal value, String pattern) {

        return format(value, new DecimalFormat(pattern));
    }

    public static String format(BigDecimal value, DecimalFormat decimalFormat) {
        if (value == null) {
            return null;
        }
        if (decimalFormat == null) {
            return null;
        }
        return decimalFormat.format(value);
    }
}
