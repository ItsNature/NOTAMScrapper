package hr.lyra.notam.normalizers.impl;

import hr.lyra.notam.Notam;
import hr.lyra.notam.normalizers.Normalizer;

import java.util.function.BiPredicate;

public class IdNormalizer extends Normalizer {

    @Override
    protected BiPredicate<Integer, String> condition() {
        return (index, entry) -> index == 0;
    }

    @Override
    protected void result(Notam notam, String entry) {
        notam.setId(entry.substring(1));
    }
}
