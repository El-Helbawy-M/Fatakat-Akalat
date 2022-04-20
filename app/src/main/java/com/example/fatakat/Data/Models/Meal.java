package com.example.fatakat.Data.Models;

import java.io.Serializable;

public class Meal implements Serializable {
    public String title,descrabtion,imagePath;
    public String[] ingredients;
    public double price;
    public Meal(String title,String descrabtion,String imagePath,String[] ingredients,double price){
        this.title = title;
        this.descrabtion = descrabtion;
        this.imagePath = imagePath;
        this.ingredients = ingredients;
        this.price = price;
    };


}
