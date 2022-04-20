package com.example.fatakat.Data.Models;

public class CartItem {
    public String title,image;
    public Long itemNumber;
    public double price;
    public CartItem(String title,String image,Long itemNumber,double price){
        this.title=title;
        this.itemNumber = itemNumber;
        this.price = price;
        this.image = image;
    }
}
