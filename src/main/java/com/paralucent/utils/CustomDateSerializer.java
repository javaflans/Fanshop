package com.paralucent.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * use case add TIMESTAMP for @ResponseBody method on Controller use @JsonSerialize(using = CustomDateSerializer.class)
 * 
 * @author flans
 */
public class CustomDateSerializer
	extends
	JsonSerializer<Date> {

    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider arg2) throws IOException, JsonProcessingException {
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	String formattedDate = formatter.format(value);
	gen.writeString(formattedDate);
    }
}