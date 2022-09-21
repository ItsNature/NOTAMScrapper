package hr.lyra.notam.scrapper;

import hr.lyra.Manager;
import hr.lyra.notam.Notam;
import hr.lyra.utils.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

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

    private static final Pattern NOTAM_START = Pattern.compile("^[(].*NOTAM");

    public void normalize() {
        var notams = this.createNotams();
        var notamManager = Manager.getInstance().getNotamManager();

        notamManager.updateNotams(notams);
    }

    // Good luck
    public List<Notam> createNotams() {
        var normalizerManager = Manager.getInstance().getNormalizerManager();
        var data = this.separateNotamData();
        var notams = new ArrayList<Notam>();

        for(var fixedNotam : data) {
            var notam = new Notam();
            var index = 0;

            for(var entry : fixedNotam) {
                normalizerManager.normalize(notam, entry, index++);
            }

            notams.add(notam);
        }

        //notams.forEach(System.out::println);
        return notams;
    }

    public List<List<String>> separateNotamData() {
        var data = this.removeTrash();
        var notams = new ArrayList<List<String>>();

        var start = false;
        var index = 0;

        var startIndex = 0;
        var endIndex = 0;

        for(var entry : data) {
            var notamStart = NOTAM_START.matcher(entry).find();

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
