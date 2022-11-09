package com.example.pokedex;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pokemon {
    private int number;
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("url")
    @Expose
    private String url;

    private String type;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumber() {
        String[] urlSplit = url.split("/");
        return Integer.parseInt(urlSplit[urlSplit.length - 1]);
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPresentation() {
        return "¡" + getName() + "!";
    }
}
