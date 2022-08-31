package hr.lyra.utils;

import lombok.experimental.UtilityClass;

import java.nio.file.Path;
import java.nio.file.Paths;

@UtilityClass
public class FileUtils {

    public final String DIRECTORY = "data\\";
    public final String TXT_NAME = DIRECTORY + "notams.txt";
    public final Path PDF = Paths.get(DIRECTORY + "notams.pdf");
    public final Path TXT = Paths.get(TXT_NAME);
}
