package hr.lyra.notam.scrapper;

import java.util.concurrent.TimeUnit;

public class NotamScrapperThread extends Thread {

    private static final long FETCH_RATE = TimeUnit.MINUTES.toMillis(1);

    private final NotamScrapper scrapper;

    public NotamScrapperThread() {
        this.scrapper = new NotamScrapper();
        this.scrapper.createDataDirectory();

        this.setName("notam-scrapper");
        this.setUncaughtExceptionHandler((t, e) -> e.printStackTrace());
    }

    @Override
    public void run() {
        for(;;) {
            try {
                this.scrapper.fetchNotams();

                Thread.sleep(FETCH_RATE);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}
