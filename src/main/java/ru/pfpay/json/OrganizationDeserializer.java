package ru.pfpay.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import ru.pfpay.domain.Organization;
import ru.pfpay.utils.NumberUtils;

import java.io.IOException;

public class OrganizationDeserializer extends JsonDeserializer<Organization> {

    @Override
    public Organization deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {

        return Organization.create(NumberUtils.getLong(jsonParser.getNumberValue()));
    }
}
