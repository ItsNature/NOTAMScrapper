package hr.lyra;

import hr.lyra.exceptions.InvalidDateTimeException;
import hr.lyra.utils.TimeUtils;

import java.text.ParseException;
import java.time.ZonedDateTime;

public class Scrapper {

    public static void main(String[] args) {
//        NotamScrapperThread thread = new NotamScrapperThread();
//        thread.start();
//
//        new TelegramBotManager();
//
//        // TODO: remove
//        NotamNormalizer notamNormalizer = new NotamNormalizer();
//        notamNormalizer.createNotams();

        try {
            ZonedDateTime date = TimeUtils.getDateAndTimeFrom("2208311900");
            System.out.println(date);
        } catch (InvalidDateTimeException ignored) {

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
