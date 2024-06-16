package com.example.trabalhosemestral.model;

import androidx.annotation.NonNull;

import java.util.Objects;

public abstract class Crop {
    private int id;
    private int days;
    private String name;
    private int price;

    private String season;

    public Crop(int id, String name, int days, int price, String season){
        this.id = id;
        this.name = name;
        this.days = days;
        this.price = price;
        this.season = season;
    }

    public Crop(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id;
    }
    public int getDays(){
        return days;
    }
    public void setDays(int days){
        this.days = days;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getPrice(){
        return price;
    }

    public void setPrice(int price){
        this.price = price;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public abstract void preserve();

    @NonNull
    @Override
    public String toString() {
        return "Crop{" +
                "days=" + days +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

}
