package hr.lyra.notam.normalizers;

import hr.lyra.notam.Notam;
import hr.lyra.notam.normalizers.impl.IcaoNormalizer;
import hr.lyra.notam.normalizers.impl.StartTimeNormalizer;
import hr.lyra.notam.normalizers.impl.EndTimeNormalizer;
import hr.lyra.notam.normalizers.impl.ScheduleNormalizer;
import hr.lyra.notam.normalizers.impl.IdNormalizer;
import hr.lyra.notam.normalizers.impl.GeneralNormalizer;

import java.util.List;

public class NormalizerManager {

    private final List<Normalizer> normalizers;

    public NormalizerManager() {
        this.normalizers = List.of(new IdNormalizer(), new GeneralNormalizer(), new IcaoNormalizer(), new StartTimeNormalizer(),
            new EndTimeNormalizer(), new ScheduleNormalizer());
    }

    public void normalize(Notam notam, String entry, int index) {
        this.normalizers.forEach(normalizer -> {
            var condition = normalizer.condition();
            var pattern = normalizer.pattern();

            // Special case for regex
            if(pattern != null) {
                var matcher = pattern.matcher(entry);

                if(matcher.find() && (condition == null || condition.test(index, entry))) {
                    var group = matcher.group();
                    normalizer.result(notam, group);
                }

                return;
            }

            // If pattern is null then we are sure condition exists
            if(condition.test(index, entry)) {
                normalizer.result(notam, entry);
            }
        });
    }
}
