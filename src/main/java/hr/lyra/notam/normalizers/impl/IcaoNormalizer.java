package hr.lyra.notam.normalizers.impl;

import hr.lyra.notam.Notam;
import hr.lyra.notam.normalizers.Normalizer;

import java.util.regex.Pattern;

public class IcaoNormalizer extends Normalizer {

    private static final Pattern PATTERN = Pattern.compile("(?<=A[)]).*?(?=B[)])");

    @Override
    protected Pattern pattern() {
        return PATTERN;
    }

    @Override
    protected void result(Notam notam, String entry) {
        notam.setIcao(entry.trim());
    }
}
