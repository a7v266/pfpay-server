package ru.pfpay.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import ru.pfpay.domain.Person;
import ru.pfpay.utils.NumberUtils;

import java.io.IOException;

public class PersonDeserializer extends JsonDeserializer<Person> {

    @Override
    public Person deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {

        return Person.create(NumberUtils.getLong(jsonParser.getNumberValue()));
    }
}
