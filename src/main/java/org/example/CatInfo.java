package org.example;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CatInfo {
    public String length;
    public String origin;
    @JsonIgnore
    public String image_link;
    public String family_friendly;
    public String shedding;
    public String general_health;
    public String playfulness;
    public String children_friendly;
    public String grooming;
    public String intelligence;
    public String other_pets_friendly;
    public String min_weight;
    public String max_weight;
    public String min_life_expectancy;
    public String max_life_expectancy;
    public String name;


    public CatInfo(@JsonProperty("length") String length,
                   @JsonProperty("origin") String origin,
                   @JsonProperty("image_link") String image_link,
                   @JsonProperty("family_friendly") String family_friendly,
                   @JsonProperty("shedding") String shedding,
                   @JsonProperty("general_health") String general_health,
                   @JsonProperty("playfulness") String playfulness,
                   @JsonProperty("children_friendly") String children_friendly,
                   @JsonProperty("grooming") String grooming,
                   @JsonProperty("intelligence") String intelligence,
                   @JsonProperty("other_pets_friendly") String other_pets_friendly,
                   @JsonProperty("min_weight") String min_weight,
                   @JsonProperty("max_weight") String max_weight,
                   @JsonProperty("min_life_expectancy") String min_life_expectancy,
                   @JsonProperty("max_life_expectancy") String max_life_expectancy,
                   @JsonProperty("name") String name) {
        this.length = length;
        this.origin = origin;
        this.image_link = image_link;
        this.family_friendly = family_friendly;
        this.shedding = shedding;
        this.general_health = general_health;
        this.playfulness = playfulness;
        this.children_friendly = children_friendly;
        this.grooming = grooming;
        this.intelligence = intelligence;
        this.other_pets_friendly = other_pets_friendly;
        this.min_weight = min_weight;
        this.max_weight = max_weight;
        this.min_life_expectancy = min_life_expectancy;
        this.max_life_expectancy = max_life_expectancy;
        this.name = name;
    }

    public static String translateInfo() {
        return ("Информация о котах:\n" +
                "length - длина\n" +
                "origin - происхождение\n" +
                "family_friendly - дружелюбность к семье\n" +
                "shedding - линька\n" +
                "general_health - общее здоровье\n" +
                "playfulness - игривость\n" +
                "children_friendly - дружелюбность к детям\n" +
                "grooming - уход\n" +
                "intelligence - интеллект\n" +
                "other_pets_friendly - дружелюбность к другим питомцам\n" +
                "min_weight - минимальный вес\n" +
                "max_weight - максимальный вес\n" +
                "min_life_expectancy - минимальная продолжительность жизни\n" +
                "max_life_expectancy - максимальная продолжительность жизни\n" +
                "name - название породы");
    }
}
