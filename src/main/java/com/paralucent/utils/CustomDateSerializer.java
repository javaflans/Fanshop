package com.paralucent.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ValueSerializer;

/**
 * use case add TIMESTAMP for @ResponseBody method on Controller use @JsonSerialize(using = CustomDateSerializer.class)
 *
 * @author flans
 */
public class CustomDateSerializer
	extends
	ValueSerializer<Date> {

    @Override
    public void serialize(Date value, JsonGenerator gen, SerializationContext ctxt) throws JacksonException {
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	String formattedDate = formatter.format(value);
	gen.writeString(formattedDate);
    }
}
