package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.InputStream;

public class InformerBot extends TelegramLongPollingBot {
    private String currentUrl = "";
    private String fullUrl;
    private String name;
    private String token;
    private String apiKey = "xoWHUYmGbNxvUhxsCdkGQSGumHIoiZd12kukNKGb"; // apiKey обновлять с https://api-ninjas.com/profile
    private ObjectMapper mapper = new ObjectMapper();
    private CloseableHttpClient client = HttpClients.createDefault();
    private boolean isStarted = false;

    public InformerBot(String name, String token) {
        this.token = token;
        this.name = name;
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(this);
            System.out.println("Бот " + name + " зарегистрирован.");
        } catch (TelegramApiException e) {
            System.out.println("Не удалось зарегистрировать бота " + name);
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            long chatId = update.getMessage().getChatId();
            String text = update.getMessage().getText();

            switch (text.toLowerCase()) {
                case "start": {
                    sendMessage(chatId, "Я пришлю тебе картинку и информацию породе кошки или собаки или информацию о другом животном.\n" +
                            "Выбери: кошка, собака или другое животное?");
                    isStarted = true;
                    break;
                }
                case "help":
                    sendMessage(chatId, "Для начала работы с ботом введи команду \"start\"\n" +
                            "Для помощи с переводом информации о котах введи команду \"catInfo\"\n" +
                            "Для помощи с переводом информации о собаках введи команду \"dogInfo\"\n" +
                            "Для помощи с переводом информации о других животных введи команду\"animalInfo\"");
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
                case "catinfo": {
                    sendMessage(chatId, CatInfo.translateInfo());
                    break;
                }
                case "doginfo": {
                    sendMessage(chatId, DogInfo.translateInfo());
                    break;
                }
                case "animalinfo": {
                    sendMessage(chatId,AnimalInfo.translateInfo());
                    break;
                }
                default: {
                    if (isStarted) {
                        fullUrl = currentUrl + text.toLowerCase().replace(" ", "%20");
                        try {
                            if (currentUrl.contains("cats")) {
                                Utils.getInfo(fullUrl, apiKey, client, mapper, CatInfo[].class, chatId, this);
                            } else if (currentUrl.contains("dogs")) {
                                Utils.getInfo(fullUrl, apiKey, client, mapper, DogInfo[].class, chatId, this);
                            } else if (currentUrl.contains("animals")) {
                                Utils.getInfo(fullUrl, apiKey, client, mapper, AnimalInfo[].class, chatId, this);
                            }
                        } catch (IndexOutOfBoundsException e){
                            System.out.println("Из чата " + chatId + ". Порода или команда " + text.toLowerCase() + " не найдена.");
                        }
                        catch (Exception e) {
                            System.out.println("Из чата " + chatId + ". Ошибка: " + e.getMessage());
                        }
                    }
                    if (!isStarted) {
                        sendMessage(chatId, "Для начала работы введи команду \"start\"");
                    }
                }
            }
        }
    }

    public void sendMessage(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendPhoto(long chatId, InputStream photoStream, String fileName) {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatId);
        sendPhoto.setPhoto(new InputFile(photoStream, fileName));
        try {
            execute(sendPhoto);
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