package in.siva.util;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class LocalDateTimeAdapter implements JsonSerializer<LocalDateTime> {

	public JsonElement serialize(LocalDateTime localDate, Type type,
			JsonSerializationContext jsonSerializationContext) {
		return new JsonPrimitive(localDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
	}
}
