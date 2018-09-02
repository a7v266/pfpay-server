package ru.pfpay.utils;

import com.google.common.net.PercentEscaper;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

public class HttpUtils {

    public static final String CONTENT_DISPOSITION = "Content-Disposition";

    public static final String CONTENT_DISPOSITION_FORMAT = "attachment; filename*=UTF-8''%s";

    public static final String CONTENT_TYPE_XLSX = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    public static final String CONTENT_TYPE_ZIP = "application/zip";

    private static final PercentEscaper PERCENT_ESCAPER = new PercentEscaper(".", false);

    public static void responseToFile(HttpServletResponse response, String fileName) {
        setCharacterEncoding(response, StandardCharsets.UTF_8.name());
        setContentType(response, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        setContentDisposition(response, fileName);
    }

    public static void responseToExcel(HttpServletResponse response, String fileName) {
        setCharacterEncoding(response, StandardCharsets.UTF_8.name());
        setContentType(response, CONTENT_TYPE_XLSX);
        setContentDisposition(response, fileName);
    }

    private static void setContentDisposition(HttpServletResponse response, String fileName) {
        response.setHeader(CONTENT_DISPOSITION, createContentDispositionHeader(fileName));
    }

    private static String createContentDispositionHeader(String fileName) {
        return "attachment; filename*=UTF-8''" + PERCENT_ESCAPER.escape(fileName);
    }

    private static void setContentType(HttpServletResponse response, String contentType) {
        response.setContentType(contentType);
    }

    private static void setCharacterEncoding(HttpServletResponse response, String characterEncoding) {
        response.setCharacterEncoding(characterEncoding);
    }
}