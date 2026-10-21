package com.paralucent.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ValueSerializer;

/**
 * use case add TIMESTAMP for @ResponseBody method on Controller use @JsonSerialize(using = CustomDateTimeSerializer.class)
 *
 * @author flans
 */
public class CustomDateTimeSerializer
	extends
	ValueSerializer<Date> {

    @Override
    public void serialize(Date value, JsonGenerator gen, SerializationContext ctxt) throws JacksonException {
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String formattedDate = formatter.format(value);
	gen.writeString(formattedDate);
    }
}
