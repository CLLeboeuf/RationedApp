package com.example.rationedapp.retrofit;

import com.example.rationedapp.entity.Recipe;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResponse {
    //No need to map all keys, only those the fields you need
    @SerializedName("Recipe")
    public List<Recipe> recipes;
    public List<Recipe> getRecipes() {
        return recipes;
    }
    public void setItems(List<Recipe> items) {
        this.recipes = recipes;
    }
    }
