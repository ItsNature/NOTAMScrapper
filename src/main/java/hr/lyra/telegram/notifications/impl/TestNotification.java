package hr.lyra.telegram.notifications.impl;

import hr.lyra.notam.Notam;
import hr.lyra.telegram.notifications.Notification;

import java.util.function.Predicate;

public class TestNotification extends Notification {

    @Override
    protected Predicate<Notam> trigger() {
        return notam -> true;
    }

    @Override
    protected String message(Notam notam) {
        return "New notam detected " + notam.toString();
    }
}
