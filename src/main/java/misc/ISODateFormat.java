package misc;

import java.text.SimpleDateFormat;

public class ISODateFormat extends SimpleDateFormat {
    public ISODateFormat() {
        super("yyyy-MM-dd'T'HH:mm:ss XXX");
    }
}
