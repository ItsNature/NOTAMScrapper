package hr.lyra.notam.normalizers.impl;

import hr.lyra.notam.Notam;
import hr.lyra.notam.normalizers.Normalizer;

import java.util.function.BiPredicate;
import java.util.regex.Pattern;

public class ScheduleNormalizer extends Normalizer {

    private static final Pattern PATTERN = Pattern.compile("(?<=D[)]).*");
    private static final Pattern SCHEDULE_START = Pattern.compile("^D[)].*");

    @Override
    protected Pattern pattern() {
        return PATTERN;
    }

    @Override
    protected BiPredicate<Integer, String> condition() {
        return (index, entry) -> SCHEDULE_START.matcher(entry).find();
    }

    @Override
    protected void result(Notam notam, String entry) {
        notam.setSchedule(entry.trim());
    }
}
