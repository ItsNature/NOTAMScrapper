package hr.lyra.scrapper;

import hr.lyra.notam.Notam;
import hr.lyra.utils.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * Notam example:
 *
 * A6690/22 NOTAMN
 * Q) LDZO/QRTCA/IV/BO /W /000/024/4533N01618E014
 * A) LDZO B) 2208310800 C) 2208311400
 * E) TEMPORARY RESERVED AREA LDTR8 ACTIVATED.
 * F) GND G) 1000FT AGL
 */

public class NotamNormalizer {

    private static final Predicate<String> NOTAM_START_PREDICATE = entry ->
        entry.startsWith("(") && entry.contains("NOTAM");

    public void createNotams() {
        var data = this.separateNotamData();
        var notams = new ArrayList<>();

        for(List<String> fixedNotam : data) {
            var notam = new Notam();
            var index = 0;

            for(String entry : fixedNotam) {
                //System.out.println(entry);

                switch(index) {
                    case 0 -> notam.setId(entry.substring(1));
                }

                var parts = entry.split("\\) ");
                //Arrays.stream(parts).forEach(System.out::println);

                index++;
            }

            notams.add(notam);
        }

        //notams.forEach(System.out::println);
    }

    public List<List<String>> separateNotamData() {
        var data = this.removeTrash();
        var notams = new ArrayList<List<String>>();

        var start = false;
        var index = 0;

        var startIndex = 0;
        var endIndex = 0;

        for(String entry : data) {
            var notamStart = NOTAM_START_PREDICATE.test(entry);

            index++;

            if(!start && notamStart) {
                start = true;
                startIndex = index - 1;
                continue;
            }

            if(start && notamStart) {
                endIndex = index - 1;
                start = false;

                var notamData = data.subList(startIndex, endIndex);
                notams.add(notamData);
            }
        }

        return notams;
    }

    public List<String> removeTrash() {
        try {
            var data = Files.readAllLines(FileUtils.TXT);
            var iterator = data.iterator();
            var index = 4; // Skip header entries

            while(iterator.hasNext()) {
                var line = iterator.next();

                if(line.isBlank()) { // Remove blanks
                    iterator.remove();
                } else if(index != 0) { // Remove additional page header entries
                    index--;
                    iterator.remove();
                } else if(line.startsWith("http")) { // Remove page header
                    iterator.remove();
                    index = 2;
                }
            }

            return data;
        } catch(IOException e) {
            return Collections.emptyList();
        }
    }
}
