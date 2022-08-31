package hr.lyra.telegram;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;

public class TelegramBotManager {

    private final TelegramBot bot;
    private final String chatId;

    public TelegramBotManager() {
        var apiKey = System.getenv("TELEGRAM_BOT_API_KEY");
        this.bot = new TelegramBot(apiKey);

        this.chatId = System.getenv("TELEGRAM_BOT_CHAT_ID");
    }

    public void sendMessage(String message) {
        this.bot.execute(new SendMessage(this.chatId, message));
    }
}
