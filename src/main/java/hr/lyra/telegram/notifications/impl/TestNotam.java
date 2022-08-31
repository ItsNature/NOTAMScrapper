package hr.lyra.telegram.notifications.impl;

import hr.lyra.notam.Notam;
import hr.lyra.telegram.notifications.Notification;

import java.util.function.Predicate;

public class TestNotam extends Notification {

    @Override
    protected Predicate<Notam> condition() {
        return notam -> true;
    }

    @Override
    protected String message() {
        return "New notam detected";
    }
}
