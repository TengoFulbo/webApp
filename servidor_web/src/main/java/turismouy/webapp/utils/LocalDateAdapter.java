package turismouy.webapp.utils;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateAdapter extends TypeAdapter<LocalDate> {
private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public void write(JsonWriter out, LocalDate value) throws IOException {
        out.value(formatter.format(value)); // Serializa LocalDate en formato "dd-MM-yyyy."
    }

    @Override
    public LocalDate read(JsonReader in) throws IOException {
        return LocalDate.parse(in.nextString(), formatter); // Deserializa la cadena en formato "dd-MM-yyyy" a LocalDate.
    }
}

// public class LocalDateAdapter {
    
// }
