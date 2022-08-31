package hr.lyra;

import hr.lyra.notam.scrapper.NotamScrapperThread;

public class Scrapper {

    public static void main(String[] args) {
        new Manager();

        NotamScrapperThread thread = new NotamScrapperThread();
        thread.start();

        // TODO: remove
//        NotamNormalizer notamNormalizer = new NotamNormalizer();
//        notamNormalizer.normalize();
    }
}
