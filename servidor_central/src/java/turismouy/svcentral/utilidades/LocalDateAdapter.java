package turismouy.svcentral.utilidades;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    @Override
    public LocalDate unmarshal(String value) throws Exception {
        return (value != null) ? LocalDate.parse(value, FORMATTER) : null;
    }

    @Override
    public String marshal(LocalDate value) throws Exception {
        return (value != null) ? value.format(FORMATTER) : null;
    }
}