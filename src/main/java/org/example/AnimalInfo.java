package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class AnimalInfo {
    public String name;
    public Map<String, String> taxonomy;
    public String[] locations;
    public Map<String, String> characteristics;

    public AnimalInfo(@JsonProperty("name") String name,
                      @JsonProperty("taxonomy") Map<String, String> taxonomy,
                      @JsonProperty("locations") String[] locations,
                      @JsonProperty("characteristics") Map<String, String> characteristics) {
        this.name = name;
        this.taxonomy = taxonomy;
        this.locations = locations;
        this.characteristics = characteristics;
    }

    public static String getTranslate() {
        return ("Информация о животных:\n" +
                "kingdom - царство\n" +
                "phylum - тип\n" +
                "class - класс\n" +
                "order - отряд\n" +
                "family - семейство\n" +
                "genus - род\n" +
                "scientific_name - научное название\n" +
                "locations - места обитания\n" +
                "main_prey - основная добыча\n" +
                "distinctive_feature - отличительная особенность\n" +
                "habitat - среда обитания\n" +
                "predators - хищники\n" +
                "diet - рацион\n" +
                "average_litter_size - средний размер потомства\n" +
                "lifestyle - образ жизни\n" +
                "favorite_food - любимая еда\n" +
                "type - тип\n" +
                "slogan - девиз\n" +
                "color - окрас\n" +
                "skin_type - тип кожи\n" +
                "top_speed - максимальная скорость\n" +
                "lifespan - продолжительность жизни\n" +
                "weight - вес\n" +
                "length - длина");
    }
}
