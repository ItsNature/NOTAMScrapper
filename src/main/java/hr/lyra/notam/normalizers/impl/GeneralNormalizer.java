package hr.lyra.notam.normalizers.impl;

import hr.lyra.notam.Notam;
import hr.lyra.notam.normalizers.Normalizer;

import java.util.regex.Pattern;

public class GeneralNormalizer extends Normalizer {

    private static final Pattern PATTERN = Pattern.compile("(?<=Q[)] ).*");

    @Override
    protected Pattern pattern() {
        return PATTERN;
    }

    @Override
    protected void result(Notam notam, String entry) {
        var generalInformation = entry.split("/");
        var extra = generalInformation[7];
        var radiusIndex = extra.length() - 3;

        notam
            .setFir(generalInformation[0])
            .setTraffic(generalInformation[2])
            .setPurpose(generalInformation[3].trim())
            .setScope(generalInformation[4].trim())
            .setMinimumAltitude(generalInformation[5])
            .setMaximumAltitude(generalInformation[6])
            .setPosition(extra.substring(0, radiusIndex))
            .setRadius(extra.substring(radiusIndex));
    }
}
