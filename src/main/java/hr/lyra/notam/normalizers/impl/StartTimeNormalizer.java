package hr.lyra.notam.normalizers.impl;

import hr.lyra.exceptions.InvalidDateTimeException;
import hr.lyra.notam.Notam;
import hr.lyra.notam.normalizers.Normalizer;
import hr.lyra.utils.TimeUtils;

import java.util.regex.Pattern;

public class StartTimeNormalizer extends Normalizer {

    private static final Pattern PATTERN = Pattern.compile("(?<=B[)] ).*?(?= C[)])");

    @Override
    protected Pattern pattern() {
        return PATTERN;
    }

    @Override
    protected void result(Notam notam, String entry) {
        try {
            var dateTime = TimeUtils.getDateAndTimeFrom(entry);
            notam.setStartTime(dateTime);
        } catch(InvalidDateTimeException e) {
            e.printStackTrace();
        }
    }
}
