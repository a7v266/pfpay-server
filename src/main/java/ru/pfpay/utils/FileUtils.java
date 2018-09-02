package ru.pfpay.utils;

import ru.pfpay.config.Format;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class FileUtils {

    private static final String INVALID_FILENAME_SYMBOLS = "[/:\\\\*|?\"]";


    private static final String TXT_FORMAT = "%s.txt";
    private static final String ZIP_FORMAT = "%s.zip";

    private static final String YEAR = "{year}";
    private static final String MONTH = "{month}";
    private static final String DAY = "{day}";
    private static final String HOUR = "{hour}";
    private static final String MINUTE = "{minute}";
    private static final String SECOND = "{second}";

    public static String createFileName(String fileNameTemplate) {

        String fileName = fileNameTemplate;

        LocalDateTime now = DateUtils.createLocalDateTime(Instant.now());

        fileName = StringUtils.replace(fileName, YEAR, StringUtils.toString(now.getYear()));
        fileName = StringUtils.replace(fileName, MONTH, StringUtils.asTwoDigits(StringUtils.toString(now.getMonthValue())));
        fileName = StringUtils.replace(fileName, DAY, StringUtils.asTwoDigits(StringUtils.toString(now.getDayOfMonth())));
        fileName = StringUtils.replace(fileName, HOUR, StringUtils.asTwoDigits(StringUtils.toString(now.getHour())));
        fileName = StringUtils.replace(fileName, MINUTE, StringUtils.asTwoDigits(StringUtils.toString(now.getMinute())));
        fileName = StringUtils.replace(fileName, SECOND, StringUtils.asTwoDigits(StringUtils.toString(now.getSecond())));

        return removeInvalidSymbols(fileName);
    }

    public static String addTxtExtension(String fileName) {

        return StringUtils.format(TXT_FORMAT, fileName);
    }

    public static String addZipExtension(String fileName) {

        return StringUtils.format(ZIP_FORMAT, fileName);
    }


    public static InputStream getInputStream(String path) {

        ClassLoader classLoader = FileUtils.class.getClassLoader();

        return classLoader.getResourceAsStream(path);
    }

    public static Reader createReader(String resourcePath, Charset charset) {

        InputStream inputStream = getInputStream(resourcePath);

        return new InputStreamReader(inputStream, charset);
    }


    public static String readFile(String fileName) throws IOException {

        StringBuilder builder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(createReader(fileName, StandardCharsets.UTF_8))) {

            String line;

            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append(Format.NEW_LINE);
            }
        }

        return builder.toString();
    }

    public static List<String> listFiles(String path) throws IOException, URISyntaxException {

        URL url = Thread.currentThread().getContextClassLoader().getResource(path);

        File file = new File(url.toURI());

        if (file.isDirectory()) {

            List<String> fileList = new LinkedList<>();

            for (String fileName : file.list()) {
                fileList.add(path.concat(fileName));
            }

            return fileList;
        }

        return Collections.emptyList();
    }

    private static String replaceInvalidSymbols(String fileName, String replacement) {
        return fileName.replaceAll(INVALID_FILENAME_SYMBOLS, replacement);
    }

    public static String removeInvalidSymbols(String fileName) {
        return replaceInvalidSymbols(fileName, Format.SPACE);
    }

    public static boolean exist(String filePath) {

        File file = new File(filePath);

        return file.exists();
    }

    public static boolean notExist(String filePath) {

        return !exist(filePath);
    }
}
