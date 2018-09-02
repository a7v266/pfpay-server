package ru.pfpay.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import ru.pfpay.domain.UserGroup;
import ru.pfpay.utils.NumberUtils;

import java.io.IOException;

public class UserGroupDeserializer extends JsonDeserializer<UserGroup> {

    @Override
    public UserGroup deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {

        return UserGroup.create(NumberUtils.getLong(jsonParser.getNumberValue()));
    }
}
