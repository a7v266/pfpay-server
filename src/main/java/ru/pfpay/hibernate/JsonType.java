package ru.pfpay.hibernate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;
import ru.pfpay.json.JsonUtils;

import java.io.IOException;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;


public abstract class JsonType implements UserType {

    private static final int[] SQL_TYPES = {Types.LONGVARCHAR};

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        if (x == y) {
            return true;
        } else if (x == null || y == null) {
            return false;
        } else {
            return x.equals(y);
        }
    }

    @Override
    public int hashCode(Object x) throws HibernateException {
        return null == x ? 0 : x.hashCode();
    }

    @Override
    public boolean isMutable() {
        return true;
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner) throws HibernateException, SQLException {
        String content = rs.getString(names[0]);
        if (content != null) {
            return convertJsonToObject(content);
        }
        return null;
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session) throws HibernateException, SQLException {
        if (value == null) {
            st.setString(index, null);
        } else {
            st.setString(index, convertObjectToJson(value));
        }
    }

    public Object convertJsonToObject(String content) {
        try {
            ObjectMapper mapper = JsonUtils.getObjectMapper();
            JavaType type = createJavaType(mapper);
            return mapper.readValue(content, type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String convertObjectToJson(Object object) {
        try {
            ObjectMapper mapper = JsonUtils.getObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            return mapper.writeValueAsString(object);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object deepCopy(Object value) throws HibernateException {
        String json = convertObjectToJson(value);
        return convertJsonToObject(json);
    }

    @Override
    public Object replace(Object original, Object target, Object owner)
            throws HibernateException {
        return deepCopy(original);
    }

    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        return (Serializable) deepCopy(value);
    }

    @Override
    public Object assemble(Serializable cached, Object owner)
            throws HibernateException {
        return deepCopy(cached);
    }

    public JavaType createJavaType(ObjectMapper mapper) {
        return mapper.getTypeFactory().constructType(returnedClass());
    }


    @Override
    public int[] sqlTypes() {
        return SQL_TYPES;
    }
}