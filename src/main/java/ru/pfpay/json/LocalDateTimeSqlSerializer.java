package ru.pfpay.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import ru.pfpay.utils.DateFormatter;

import java.io.IOException;
import java.time.LocalDateTime;

public class LocalDateTimeSqlSerializer extends JsonSerializer<LocalDateTime> {

    @Override
    public void serialize(LocalDateTime value, JsonGenerator generator, SerializerProvider provider) throws IOException {
        generator.writeString(DateFormatter.format(value, DateFormatter.PATTERN_SQL_DATE_TIME));
    }
}
