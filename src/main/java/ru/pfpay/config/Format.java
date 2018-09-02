package ru.pfpay.config;

import java.math.BigDecimal;

public class Format {


    public static final BigDecimal BYTE_PER_MEGABYTE = new BigDecimal("1048576");
    public static final BigDecimal BYTE_PER_GIGABYTE = new BigDecimal("1073741824");
    public static final BigDecimal MEGABYTE_PER_GIGABYTE = new BigDecimal("1024");
    public static final BigDecimal MINUTE_PER_HOUR = new BigDecimal("60");
    public static final BigDecimal SECOND_PER_MINUTE = new BigDecimal("60");

    public static final BigDecimal MAX_DECIMAL = new BigDecimal("1000000000");
    public static final BigDecimal TAX = new BigDecimal("0.18");

    public static final int LENGTH_BANK_ACCOUNT_NUMBER = 20;
    public static final int LENGTH_BIK = 9;
    public static final int LENGTH_CONTRACT_CODE = 255;
    public static final int LENGTH_CONTRACT_NUMBER = 255;
    public static final int LENGTH_INN = 12;
    public static final int LENGTH_KPP = 9;
    public static final int LENGTH_NAME = 255;
    public static final int LENGTH_CODE = 50;
    public static final int LENGTH_NUMBER = 5;
    public static final int LENGTH_OGRN = 15;
    public static final int LENGTH_OKPO = 10;
    public static final int LENGTH_POSTCODE = 6;
    public static final int LENGTH_SWIFT = 11;
    public static final int LENGTH_TARIFF_DESCRIPTION = 300;
    public static final int LENGTH_TEXT = 255;
    public static final int LENGTH_IBAN = 34;
    public static final int LENGTH_ERROR = 1000;
    public static final int LENGTH_COMMODITY_NAME = 300;
    public static final int LENGTH_PARAMETER = 300;
    public static final int LENGTH_NAME_TEMPLATE = 300;

    public static final int MAX_VOLUME = 1000000;

    public static final long MAX_MONTH = 12;
    public static final long MIN_MONTH = 1;
    public static final long MIN_YEAR = 2000;
    public static final long MAX_COLUMN_WIDTH = 65280;

    public static final String DASH = "-";
    public static final String COLON = ":";
    public static final String COLON_SPACE = ": ";
    public static final String COMMA = ",";
    public static final String COMMA_SPACE = ", ";
    public static final String DATE_PATTERN = "dd.MM.yyyy";
    public static final String DATE_TIME_PATTERN = "dd.MM.yyyy HH:mm";
    public static final String DOT = ".";
    public static final String EMPTY_STRING = "";
    public static final String EXPORT_TO_1C_XML_DATE_FORMAT = "yyyy-MM-dd";
    public static final String HTML_BR = "<br/>";
    public static final String NEW_LINE = "\n";
    public static final String ONE = "1";

    public static final String PATTERN_BANK_ACCOUNT_NUMBER = "^\\d{20}$";
    public static final String PATTERN_BANK_CODE = "^\\d{9}$";
    public static final String PATTERN_DATE = "^(0[1-9]|[12][0-9]|3[01])[\\.](0[1-9]|1[012])[\\.](19|20)\\d\\d$";
    public static final String PATTERN_EMAIL = "^[^@]+@[^@]+\\.[^@]+$";
    public static final String PATTERN_INN = "^\\d{10}|\\d{12}$";
    public static final String PATTERN_INN_LEGAL = "^\\d{10}$";
    public static final String PATTERN_INN_PERSONAL = "^\\d{12}$";
    public static final String PATTERN_IPA = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
    public static final String PATTERN_KPP = "^\\d{9}$";
    public static final String PATTERN_OGRN = "^\\d{13}$";
    public static final String PATTERN_OKPO = "^\\d{8}|\\d{10}$";
    public static final String PATTERN_PHONE_NUMBER = "^\\+?[\\d\\-\\(\\)]{1,30}$";
    public static final String PATTERN_PHONE_RESOURCE = "\\d{3,20}";
    public static final String PATTERN_POSTCODE = "^\\d{6}$";
    public static final String PATTERN_PORT = "^\\d{4,5}$";
    public static final String PATTERN_SNILS = "^\\d{11}$";

    public static final String OPEN_BRACE = "(";
    public static final String CLOSED_BRACE = ")";

    public static final String PERCENT = "%";
    public static final String REGEX_DOT = "\\.";
    public static final String SEMICOLON = ";";
    public static final String SLASH = "/";
    public static final String SPACE = " ";
    public static final String UNDERSCORE = "_";
    public static final String UTF_8 = "UTF-8";
    public static final String WINDOWS_1251 = "windows-1251";
    public static final String X = "x";
    public static final String XML_VERSION = "1.0";
    public static final String ZERO = "0";
    public static final String XLSX = ".xlsx";
}
