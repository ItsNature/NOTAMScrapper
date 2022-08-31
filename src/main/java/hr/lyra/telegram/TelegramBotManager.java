package hr.lyra.telegram;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.Getter;

public class TelegramBotManager {

    @Getter private static TelegramBotManager instance;

    private final TelegramBot bot;
    private final String chatId;

    public TelegramBotManager() {
        instance = this;

        String apiKey = System.getenv("TELEGRAM_BOT_API_KEY");
        this.bot = new TelegramBot(apiKey);

        this.chatId = System.getenv("TELEGRAM_BOT_CHAT_ID");
    }

    public void sendMessage(String message) {
        this.bot.execute(new SendMessage(this.chatId, message));
    }
}
