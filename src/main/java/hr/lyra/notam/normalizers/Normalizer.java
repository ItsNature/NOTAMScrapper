package hr.lyra.notam.normalizers;

import hr.lyra.notam.Notam;

import java.util.function.BiPredicate;
import java.util.regex.Pattern;

public abstract class Normalizer {

    protected Pattern pattern() {
        return null;
    }

    protected BiPredicate<Integer, String> condition(Notam notam) {
        return null;
    }

    protected abstract void result(Notam notam, String entry);
}
