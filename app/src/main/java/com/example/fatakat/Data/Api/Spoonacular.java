package com.example.fatakat.Data.Api;

import com.example.fatakat.Data.Models.Bad;
import com.example.fatakat.Data.Models.Good;
import com.example.fatakat.Data.Models.Result;

import java.io.IOException;
import java.net.URL;

public class Spoonacular {
    String API_KEY = "8af9998c64f247e8b377dee8aba404fb";
    public Result getRecipes(String query){
        String url = "https://api.spoonacular.com/recipes/complexSearch?query="+query+"&addRecipeInformation=true&fillIngredients=true&apiKey="+API_KEY;
        try {
            return new Requester(url).getRequest();
        } catch (IOException e) {
            return new Bad(404,e.getMessage());
        }
    }
}
