package hr.lyra.telegram.notifications;

import hr.lyra.notam.Notam;

import java.util.function.Predicate;

public abstract class Notification {
    
    protected abstract Predicate<Notam> trigger();
    protected abstract String message(Notam notam);
}
