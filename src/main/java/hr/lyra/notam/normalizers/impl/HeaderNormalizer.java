package hr.lyra.notam.normalizers.impl;

import hr.lyra.notam.Notam;
import hr.lyra.notam.normalizers.Normalizer;

import java.util.function.BiPredicate;
import java.util.regex.Pattern;

public class HeaderNormalizer extends Normalizer {

    private static final Pattern PATTERN = Pattern.compile("(?<=E[)]).*");
    public static final Pattern DESCRIPTION_START = Pattern.compile("^E[)].*");

    @Override
    protected Pattern pattern() {
        return PATTERN;
    }

    @Override
    protected BiPredicate<Integer, String> condition(Notam notam) {
        return (index, entry) -> DESCRIPTION_START.matcher(entry).find() && notam.getDescription().isEmpty();
    }

    @Override
    protected void result(Notam notam, String entry) {
        notam.getDescription().add(entry.trim());
    }
}
