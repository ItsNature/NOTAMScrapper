package hr.lyra;

import hr.lyra.notam.scrapper.NotamScrapperThread;

public class Scrapper {

    /**
     * TODOs
     * Store notams into postgres
     * Write a readme file
     * Notifications: LDTR, Slunj, LDTRAU?
     * Google Maps API integration
     * 1 min refresh rate
     * HR8XX take off and landing alerts
     * NATO QRA squawk alerts
     * Rename app
     */

    public static void main(String[] args) {
        new Manager();

        NotamScrapperThread thread = new NotamScrapperThread();
        thread.start();

        // TODO: remove
//        NotamNormalizer notamNormalizer = new NotamNormalizer();
//        notamNormalizer.normalize();
    }
}
