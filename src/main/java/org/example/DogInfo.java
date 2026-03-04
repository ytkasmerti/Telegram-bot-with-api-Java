package org.example;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DogInfo {
    @JsonIgnore
    public String image_link;
    public String good_with_children;
    public String good_with_other_dogs;
    public String shedding;
    public String grooming;
    public String drooling;
    public String coat_length;
    public String good_with_strangers;
    public String playfulness;
    public String protectiveness;
    public String trainability;
    public String energy;
    public String barking;
    public String min_life_expectancy;
    public String max_life_expectancy;
    public String max_height_male;
    public String max_height_female;
    public String max_weight_male;
    public String max_weight_female;
    public String min_height_male;
    public String min_height_female;
    public String min_weight_male;
    public String min_weight_female;
    public String name;

    public DogInfo(@JsonProperty("image_link") String image_link,
                   @JsonProperty("good_with_children") String good_with_children,
                   @JsonProperty("good_with_other_dogs") String good_with_other_dogs,
                   @JsonProperty("shedding") String shedding,
                   @JsonProperty("grooming") String grooming,
                   @JsonProperty("drooling") String drooling,
                   @JsonProperty("coat_length") String coat_length,
                   @JsonProperty("good_with_strangers") String good_with_strangers,
                   @JsonProperty("playfulness") String playfulness,
                   @JsonProperty("protectiveness") String protectiveness,
                   @JsonProperty("trainability") String trainability,
                   @JsonProperty("energy") String energy,
                   @JsonProperty("barking") String barking,
                   @JsonProperty("min_life_expectancy") String min_life_expectancy,
                   @JsonProperty("max_life_expectancy") String max_life_expectancy,
                   @JsonProperty("max_height_male") String max_height_male,
                   @JsonProperty("max_height_female") String max_height_female,
                   @JsonProperty("max_weight_male") String max_weight_male,
                   @JsonProperty("max_weight_female") String max_weight_female,
                   @JsonProperty("min_height_male") String min_height_male,
                   @JsonProperty("min_height_female") String min_height_female,
                   @JsonProperty("min_weight_male") String min_weight_male,
                   @JsonProperty("min_weight_female") String min_weight_female,
                   @JsonProperty("name") String name) {
        this.image_link = image_link;
        this.good_with_children = good_with_children;
        this.good_with_other_dogs = good_with_other_dogs;
        this.shedding = shedding;
        this.grooming = grooming;
        this.drooling = drooling;
        this.coat_length = coat_length;
        this.good_with_strangers = good_with_strangers;
        this.playfulness = playfulness;
        this.protectiveness = protectiveness;
        this.trainability = trainability;
        this.energy = energy;
        this.barking = barking;
        this.min_life_expectancy = min_life_expectancy;
        this.max_life_expectancy = max_life_expectancy;
        this.max_height_male = max_height_male;
        this.max_height_female = max_height_female;
        this.max_weight_male = max_weight_male;
        this.max_weight_female = max_weight_female;
        this.min_height_male = min_height_male;
        this.min_height_female = min_height_female;
        this.min_weight_male = min_weight_male;
        this.min_weight_female = min_weight_female;
        this.name = name;
    }

    public static String getTranslate() {
        return ("Информация о собаках:\n" +
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
