package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Utils {

    public static <T> void getInfo(String url, String apiKey, CloseableHttpClient client, ObjectMapper mapper, Class<T[]> clazz) throws Exception {
        CloseableHttpResponse response = null;
        CloseableHttpResponse imageResponse = null;
        FileOutputStream fos = null;

        try {
            HttpGet request = new HttpGet(url);
            request.setHeader("X-Api-Key", apiKey);
            response = client.execute(request);
            T[] items = mapper.readValue(response.getEntity().getContent(), clazz);
            if (items.length == 0) {
                System.out.println("Порода не найдена!");
                return;
            }
            T item = items[0];

            String fileName = item.getClass().getField("name").get(item).toString();
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("info/" + fileName + ".json"), item);
            System.out.println("JSON файл сохранен");

            String imageLink = item.getClass().getField("image_link").get(item).toString();
            if (imageLink != null && !imageLink.isEmpty()) {
                HttpGet imageRequest = new HttpGet(imageLink);
                imageResponse = client.execute(imageRequest);
                fos = new FileOutputStream("images/" + fileName + ".jpg");
                imageResponse.getEntity().writeTo(fos);
                System.out.println("Изображение сохранено");
                imageResponse.close();
            }
        } catch (IOException e) {
            System.out.println("Сервер не отвечает");
        } finally {
            if (response != null) response.close();
            if (fos != null) fos.close();
            if (imageResponse!=null) imageResponse.close();
        }
    }
}