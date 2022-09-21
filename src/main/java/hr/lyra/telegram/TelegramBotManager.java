package hr.lyra.telegram;

import com.pengrad.telegrambot.Callback;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;

import java.io.IOException;

public class TelegramBotManager {

    private final TelegramBot bot;
    private final String chatId;

    public TelegramBotManager() {
        var apiKey = System.getenv("TELEGRAM_BOT_API_KEY");
        this.bot = new TelegramBot(apiKey);

        this.chatId = System.getenv("TELEGRAM_BOT_CHAT_ID");
    }

    public void sendMessage(String message) {
        this.bot.execute(new SendMessage(this.chatId, message), new Callback<SendMessage, SendResponse>() {

            @Override
            public void onResponse(SendMessage message, SendResponse response) { }

            @Override
            public void onFailure(SendMessage message, IOException e) {
                e.printStackTrace();
            }
        });

        System.out.println(message); // TODO
    }
}
