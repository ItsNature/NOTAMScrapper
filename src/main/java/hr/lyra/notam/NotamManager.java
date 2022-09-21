package hr.lyra.notam;

import hr.lyra.Manager;
import hr.lyra.Scrapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotamManager {

    private final Map<String, Notam> notams;

    public NotamManager() {
        this.notams = new HashMap<>();
    }

    public void updateNotams(List<Notam> notams) {
        var notificationManager = Manager.getInstance().getNotificationManager();
        var empty = this.notams.isEmpty();

        notams.forEach(notam -> {
            var id = notam.getId();

            if(!empty && !this.notams.containsKey(id)) { // TODO: check if notam has changed?
                notificationManager.checkNotifications(notam);
            }

            this.notams.put(id, notam);
        });

        Scrapper.log("Updated NOTAMs");
    }
}
