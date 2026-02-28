package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        //Api Key обновлять с https://api-ninjas.com/profile
        String apiKey = "xoWHUYmGbNxvUhxsCdkGQSGumHIoiZd12kukNKGb";

        Scanner scanner = new Scanner(System.in);
        ObjectMapper mapper = new ObjectMapper();
        CloseableHttpClient client = HttpClients.createDefault();


        System.out.println("Информацию о ком Вы хотите получить: кошка, собака или животное?");
        String typeName = scanner.nextLine().toLowerCase().trim();
        System.out.println("Введите название животного или породы на английском языке (например:\"cheetah\", \"abyssinian\", \"golden retriever\")");
        String breedName = scanner.nextLine();
        new File("images").mkdirs();
        new File("info").mkdirs();

        String url;
        switch (typeName) {
            case "кошка":
                url = "https://api.api-ninjas.com/v1/cats?name=" + breedName.toLowerCase().replace(" ", "%20");
                HttpGet catRequest = new HttpGet(url);
                catRequest.setHeader("X-Api-Key", apiKey);
                CloseableHttpResponse catResponse = client.execute(catRequest);
                CatInfo[] cats = mapper.readValue(catResponse.getEntity().getContent(), CatInfo[].class);
                if (cats.length == 0) {
                    System.out.println("Порода не найдена!");
                    return;
                }
                CatInfo catInfo = cats[0];
                mapper.writerWithDefaultPrettyPrinter().writeValue(new File("info/" + catInfo.name + ".json"), catInfo);
                HttpGet catImageRequest = new HttpGet(catInfo.image_link);
                CloseableHttpResponse catImage = client.execute(catImageRequest);
                String[] splitterCatImageLink = catInfo.image_link.split("/");
                FileOutputStream fos1 = new FileOutputStream("images/" + splitterCatImageLink[splitterCatImageLink.length - 1]);
                catImage.getEntity().writeTo(fos1);
                break;

            case "собака":
                url = "https://api.api-ninjas.com/v1/dogs?name=" + breedName.toLowerCase().replace(" ", "%20");
                HttpGet dogRequest = new HttpGet(url);
                dogRequest.setHeader("X-Api-Key", apiKey);
                CloseableHttpResponse dogResponse = client.execute(dogRequest);
                DogInfo[] dogs = mapper.readValue(dogResponse.getEntity().getContent(), DogInfo[].class);
                if (dogs.length == 0) {
                    System.out.println("Порода не найдена!");
                    return;
                }
                DogInfo dogInfo = dogs[0];
                mapper.writerWithDefaultPrettyPrinter().writeValue(new File("info/" + dogInfo.name + ".json"), dogInfo);
                HttpGet dogImageRequest = new HttpGet(dogInfo.image_link);
                CloseableHttpResponse dogImage = client.execute(dogImageRequest);
                String[] splitterDogImageLink = dogInfo.image_link.split("/");
                FileOutputStream fos2 = new FileOutputStream("images/" + splitterDogImageLink[splitterDogImageLink.length - 1]);
                dogImage.getEntity().writeTo(fos2);
                break;

            case "животное":
                url = "https://api.api-ninjas.com/v1/animals?name=" + breedName.toLowerCase().replace(" ", "%20");
                HttpGet animalRequest = new HttpGet(url);
                animalRequest.setHeader("X-Api-Key", apiKey);
                CloseableHttpResponse animalResponse = client.execute(animalRequest);
                AnimalInfo[] animals = mapper.readValue(animalResponse.getEntity().getContent(), AnimalInfo[].class);
                if (animals.length == 0) {
                    System.out.println("Порода не найдена!");
                    return;
                }
                AnimalInfo animalInfo = animals[0];
                mapper.writerWithDefaultPrettyPrinter().writeValue(new File("info/" + animalInfo.name + ".json"), animalInfo);
                break;
            default:
                System.out.println("Неверный ввод.");
        }
    }
}