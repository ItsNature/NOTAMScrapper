package hr.lyra.utils;

import hr.lyra.exceptions.InvalidDateTimeException;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class TimeUtils {

    private final ZoneId TIMEZONE = ZoneId.of("UTC");
    private final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyMMddHHmm");

    public ZonedDateTime getDateAndTimeFrom(String input) throws InvalidDateTimeException {
        // Example input = '2208311900' (year/month/day/hours/minutes)
        if(input.length() != 10) {
            throw new InvalidDateTimeException(input);
        }

        try {
            Long.parseLong(input);
        } catch(NumberFormatException e) {
            throw new InvalidDateTimeException(input);
        }

        return LocalDateTime.parse(input, DATE_FORMAT).atZone(TIMEZONE);
    }
}
