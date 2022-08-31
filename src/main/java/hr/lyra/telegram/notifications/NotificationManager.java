package hr.lyra.telegram.notifications;

import hr.lyra.Manager;
import hr.lyra.notam.Notam;
import hr.lyra.telegram.notifications.impl.TestNotam;

import java.util.Set;

public class NotificationManager {

    private final Set<Notification> notifications;

    public NotificationManager() {
        this.notifications = Set.of(new TestNotam());
    }

    public void checkNotifications(Notam notam) {
        var telegramBotManager = Manager.getInstance().getTelegramBotManager();

        this.notifications.stream()
            .filter(notification -> notification.condition().test(notam))
            .forEach(notification -> telegramBotManager.sendMessage(notification.message()));
    }
}
