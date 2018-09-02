package ru.pfpay.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import ru.pfpay.domain.Contract;
import ru.pfpay.domain.UserGroup;
import ru.pfpay.utils.NumberUtils;

import java.io.IOException;

public class ContractDeserializer extends JsonDeserializer<Contract> {

    @Override
    public Contract deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {

        return Contract.create(NumberUtils.getLong(jsonParser.getNumberValue()));
    }
}
