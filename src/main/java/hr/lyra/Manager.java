package hr.lyra;

import hr.lyra.notam.NotamManager;
import hr.lyra.notam.normalizers.NormalizerManager;
import hr.lyra.telegram.TelegramBotManager;
import hr.lyra.telegram.notifications.NotificationManager;
import lombok.Getter;

@Getter
public class Manager {

    @Getter private static Manager instance;

    //private final DatabaseManager databaseManager;
    private final NormalizerManager normalizerManager;
    private final NotamManager notamManager;
    private final NotificationManager notificationManager;
    private final TelegramBotManager telegramBotManager;

    public Manager() {
        instance = this;

        //this.databaseManager = new DatabaseManager();
        this.normalizerManager = new NormalizerManager();
        this.notamManager = new NotamManager();
        this.notificationManager = new NotificationManager();
        this.telegramBotManager = new TelegramBotManager();
    }
}
