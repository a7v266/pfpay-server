package ru.pfpay.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.pfpay.config.Format;
import ru.pfpay.utils.StringUtils;

import java.io.IOException;
import java.util.List;


public class JsonUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);

    private static volatile ObjectMapper OBJECT_MAPPER;


    public static ObjectMapper getObjectMapper() {

        if (OBJECT_MAPPER == null) {

            synchronized (JsonUtils.class) {

                if (OBJECT_MAPPER == null) {

                    OBJECT_MAPPER = new ObjectMapper();

                    OBJECT_MAPPER.disable(
                            MapperFeature.AUTO_DETECT_CREATORS,
                            MapperFeature.AUTO_DETECT_FIELDS,
                            MapperFeature.AUTO_DETECT_GETTERS,
                            MapperFeature.AUTO_DETECT_IS_GETTERS);

                    OBJECT_MAPPER.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

                    OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
                }
            }
        }

        return OBJECT_MAPPER;
    }


    public static String serialize(Object object) {
        if (object == null) {
            return null;
        }
        try {
            return getObjectMapper().writeValueAsString(object);

        } catch (JsonProcessingException exception) {

            LOGGER.error(exception.getMessage(), exception);
        }

        return Format.EMPTY_STRING;
    }


    public static <T> T deserialize(String source, Class<T> clazz) {

        if (StringUtils.isEmpty(source)) {
            return null;
        }

        try {
            return getObjectMapper().readValue(source, clazz);

        } catch (IOException exception) {

            LOGGER.error(exception.getMessage(), exception);
        }

        return null;
    }

    public static <T> List<T> deserializeList(String source, Class<T> clazz) {

        if (StringUtils.isEmpty(source)) {
            return null;
        }

        try {

            ObjectMapper objectMapper = getObjectMapper();

            return objectMapper.readValue(source, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));

        } catch (IOException exception) {

            LOGGER.error(exception.getMessage(), exception);
        }

        return null;
    }


    public static void put(ObjectNode node, String fieldName, Integer fieldValue) {
        if (fieldValue == null) {
            return;
        }
        node.put(fieldName, fieldValue);
    }

    public static void put(ObjectNode node, String fieldName, String fieldValue) {
        if (fieldValue == null || fieldValue.isEmpty()) {
            return;
        }
        node.put(fieldName, fieldValue);
    }

    public static Integer getInteger(ObjectNode node, String fieldName) {
        JsonNode value = node.get(fieldName);
        if (value != null) {
            return value.intValue();
        }
        return null;
    }

    public static String getString(ObjectNode node, String fieldName) {
        JsonNode value = node.get(fieldName);
        if (value != null) {
            return value.textValue();
        }
        return null;
    }
}
