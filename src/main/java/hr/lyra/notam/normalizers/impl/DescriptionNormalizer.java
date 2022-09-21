package hr.lyra.notam.normalizers.impl;

import hr.lyra.notam.Notam;
import hr.lyra.notam.normalizers.Normalizer;

import java.util.function.BiPredicate;
import java.util.regex.Pattern;

public class DescriptionNormalizer extends Normalizer {

    private static final Pattern OTHER_START = Pattern.compile("^F[)].*");

    @Override
    protected BiPredicate<Integer, String> condition(Notam notam) {
        return (index, entry) -> !HeaderNormalizer.DESCRIPTION_START.matcher(entry).find() &&
            !OTHER_START.matcher(entry).find() && !notam.getDescription().isEmpty();
    }

    @Override
    protected void result(Notam notam, String entry) {
        notam.getDescription().add(entry.trim());
    }
}
