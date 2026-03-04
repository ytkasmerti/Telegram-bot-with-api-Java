package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Scanner;

public class InformerBot extends TelegramLongPollingBot {
    private String currentUrl = "";
    private String fullUrl;
    private String name;
    private String token;

    public InformerBot(String name, String token) {
        this.token = token;
        this.name = name;
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(this);
        } catch (TelegramApiException e) {
            System.out.println("Не удалось зарегистрировать бота.");
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            long chatId = update.getMessage().getChatId();
            String text = update.getMessage().getText();

            switch (text) {
                case "/start": sendMessage(chatId, "Я пришлю тебе информацию о животном или породе кошки или собаки с картинкой.");
                break;
                case "/help": sendMessage(chatId, "Выбери: кошка, собака или другое животное?");
                break;
                case "кошка": {
                    currentUrl = "https://api.api-ninjas.com/v1/cats?name=";
                    sendMessage(chatId, "Введи название породы на английском. \n Например: \"Abyssinian\", \"american bobtail\"");
                    break;
                }
                case "собака": {
                    currentUrl = "https://api.api-ninjas.com/v1/dogs?name=";
                    sendMessage(chatId, "Введи название породы на английском. \n Например: \"Golden retriever\", \"bulldog\"");
                    break;
                }
                case "животное": {
                    currentUrl = "https://api.api-ninjas.com/v1/animals?name=";
                    sendMessage(chatId, "Введи название породы на английском. \n Например: \"Cheetah\", \"cow\".");
                    break;
                }
                default: {
                    fullUrl = currentUrl + text.toLowerCase().replace(" ", "%20");
                    sendMessage(chatId, fullUrl);
                }
            }
        }
    }

    public void sendMessage(long chatId, String text){
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return name;
    }

    @Override
    public String getBotToken() {
        return token;
    }
}
