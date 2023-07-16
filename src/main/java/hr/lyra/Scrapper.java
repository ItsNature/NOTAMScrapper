package hr.lyra;

import hr.lyra.notam.scrapper.NotamNormalizer;
import hr.lyra.notam.scrapper.NotamScrapperThread;

import java.util.logging.Logger;

public class Scrapper {

    /**
     * TODOs
     * Store notams into postgres
     * Write a readme file
     * Notifications: LDTR, Slunj, LDTRAU, LDTSAU?
     * Google Maps API integration
     * HR8XX take off and landing alerts
     * NATO QRA squawk alerts
     * Rename app
     * Fix font warnings
     */

    private static final Logger LOGGER = Logger.getLogger("Scrapper");

    public static void main(String[] args) {
        new Manager();
        new NotamScrapperThread().start();

        // TODO: remove
        //new NotamNormalizer().normalize();
    }

    public static void log(String message) {
        LOGGER.info(message);
    }
}
