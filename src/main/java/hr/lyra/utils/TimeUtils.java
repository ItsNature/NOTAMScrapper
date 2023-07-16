package hr.lyra.utils;

import hr.lyra.exceptions.InvalidDateTimeException;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

@UtilityClass
public class TimeUtils {

    private final ZoneId TIMEZONE = ZoneId.of("UTC");
    private final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyMMddHHmm");

    public ZonedDateTime getDateAndTimeFrom(String input) throws InvalidDateTimeException {
        // Example input = '2208311900' (year/month/day/hours/minutes)

        // New inputs can have timezones in them
        // 2306092359 EST or 2306162359EST
        input = input.replaceAll("\\s", "");

        var length = input.length();
        if(length < 10) {
            throw new InvalidDateTimeException(input);
        }

        var timezone = TIMEZONE;
        if(length > 10) {
            try {
                // ZoneId doesn't support old TimeZone id's,
                // so first we use the old API, then convert it to new.
                timezone = TimeZone.getTimeZone(input.substring(10)).toZoneId();
                input = input.substring(0, 10);
            } catch (Exception e) {
                throw new InvalidDateTimeException(e.getMessage());
            }
        }

        try {
            Long.parseLong(input);
        } catch(NumberFormatException e) {
            throw new InvalidDateTimeException(input);
        }

        return LocalDateTime.parse(input, DATE_FORMAT).atZone(timezone);
    }
}
