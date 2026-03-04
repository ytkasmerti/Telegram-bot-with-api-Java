package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;

import java.io.IOException;

public class Utils {

    public static <T> void getInfo(String url, String apiKey, CloseableHttpClient client, ObjectMapper mapper, Class<T[]> clazz, long chatId, InformerBot bot) throws Exception {
        CloseableHttpResponse response = null;
        CloseableHttpResponse imageResponse = null;

        try {
            HttpGet request = new HttpGet(url);
            request.setHeader("X-Api-Key", apiKey);
            response = client.execute(request);
            T[] items = mapper.readValue(response.getEntity().getContent(), clazz);
            if (items.length == 0) {
                System.out.println("Порода не найденa");
                bot.sendMessage(chatId, "Не удалось найти такого животного или такую команду.");
            }
            T item = items[0];
            String fileName = item.getClass().getField("name").get(item).toString();
            String jsonInfo = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(item);
            bot.sendMessage(chatId, "Информация о " + fileName + ":\n" + jsonInfo);

            String imageLink = null;
            try {
                java.lang.reflect.Field field = item.getClass().getField("image_link");
                imageLink = field.get(item).toString();
            } catch (NoSuchFieldException e) {
                System.out.println("У этого животного нет поля image_link");
            }

            if (imageLink != null && !imageLink.isEmpty()) {
                imageResponse = client.execute(new HttpGet(imageLink));
                bot.sendPhoto(chatId, imageResponse.getEntity().getContent(), fileName + ".jpg");
                System.out.println("Изображение отправлено");
            }
        } catch (IOException e) {
            System.out.println("Сервер не отвечает");
            bot.sendMessage(chatId, "Сервер не отвечает. Повторите попытку позже.");
        } finally {
            if (response != null) response.close();
            if (imageResponse != null) imageResponse.close();
        }
    }
}