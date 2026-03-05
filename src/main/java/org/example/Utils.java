package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;

import java.io.IOException;

public class Utils {

    public static <T> void getInfo(String url,
                                   String apiKey,
                                   CloseableHttpClient client,
                                   ObjectMapper mapper,
                                   Class<T[]> classObject,
                                   long chatId,
                                   InformerBot bot) throws Exception {

        CloseableHttpResponse response = null;
        CloseableHttpResponse imageResponse = null;

        try {
            HttpGet request = new HttpGet(url);
            request.setHeader("X-Api-Key", apiKey);
            response = client.execute(request);
            T[] items = mapper.readValue(response.getEntity().getContent(), classObject);
            if (items.length == 0) {
                bot.sendMessage(chatId, "Не удалось найти такого животного или такую команду.");
            }
            T item = items[0];

            String fileName = item.getClass().getField("name").get(item).toString();
            String imageLink = null;

            try {
                java.lang.reflect.Field field = item.getClass().getField("image_link");
                imageLink = field.get(item).toString();
            } catch (NoSuchFieldException e) {
                System.out.println("Из чата " + chatId + ". У животного " + fileName + " нет изображения.");
            }

            if (imageLink != null && !imageLink.isEmpty()) {
                imageResponse = client.execute(new HttpGet(imageLink));
                bot.sendPhoto(chatId, imageResponse.getEntity().getContent(), fileName + ".jpg");
                System.out.println("Из чата " + chatId + ". Изображение отправлено.");
            }

            String jsonInfo = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(item);
            bot.sendMessage(chatId, "Информация о " + fileName + ":\n" + jsonInfo);
            System.out.println("Из чата " + chatId + ". Информация отправлена.");

        } catch (IOException e) {
            System.out.println("Из чата " + chatId + ". Сервер не отвечает.");
            bot.sendMessage(chatId, "Сервер не отвечает. Попробуйте еще раз или повторите попытку позже.");
        } finally {
            if (response != null) response.close();
            if (imageResponse != null) imageResponse.close();
        }
    }
}