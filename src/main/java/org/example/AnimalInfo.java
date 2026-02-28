package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class AnimalInfo {
    public String name;
    public Map<String, String> taxonomy;
    public String[] locations;
    public Map<String, String>characteristics;

    public AnimalInfo(@JsonProperty("name") String name,
                      @JsonProperty("taxonomy") Map<String, String> taxonomy,
                      @JsonProperty("locations") String[] locations,
                      @JsonProperty("characteristics") Map<String, String> characteristics) {
        this.name = name;
        this.taxonomy = taxonomy;
        this.locations = locations;
        this.characteristics = characteristics;
    }
}
