package hr.lyra.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Messages {

    public final String[] NOTAM_FETCH_FAILED = new String[] {
        "❌ Failed to fetch NOTAMs ❌",
        "➡ Error Code: {code}"
    };

    public String toString(String[] message) {
        return String.join("\n", message);
    }
}
