package com.example.flaggame;

public class Country {
    private String code;
    private String name;
    private int image;

    public Country(String code, String name, int image) {
        this.code = code;
        this.name = name;
        this.image = image;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }
}
