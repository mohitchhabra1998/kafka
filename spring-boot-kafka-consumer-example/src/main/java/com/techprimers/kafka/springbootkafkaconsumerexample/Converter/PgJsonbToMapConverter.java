package com.techprimers.kafka.springbootkafkaconsumerexample.Converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;
import org.postgresql.util.PGobject;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Logger;

@Converter
public class PgJsonbToMapConverter implements AttributeConverter<Map<String, ? extends Object>, PGobject> {
   // private static final Logger LOGGER = Logger.getLogger(PgJsonbToMapConverter.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public PGobject convertToDatabaseColumn(Map<String, ? extends Object> map) {
        PGobject po = new PGobject();
        po.setType("jsonb");

        try {
            po.setValue(map == null ? null : MAPPER.writeValueAsString(map));
        } catch (SQLException | JsonProcessingException ex) {
            System.out.println("Invalid 1");
            throw new IllegalStateException(ex);
        }
        return po;
    }

    @Override
    public Map<String, ? extends Object> convertToEntityAttribute(PGobject dbData) {
        if (dbData == null || dbData.getValue() == null) {
            return null;
        }
        try {
            return MAPPER.readValue(dbData.getValue(), new TypeReference<Map<String, Object>>() {
            });
        } catch (IOException ex) {
            System.out.println("Invalid 2");
            return null;
        }
    }

}
