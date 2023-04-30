package com.example.rationedapp.viewmodel;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.rationedapp.entity.Recipe;
import com.example.rationedapp.repository.RecipeRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class RecipeViewModel extends AndroidViewModel {
    private RecipeRepository recipeRepository;
    private LiveData<List<Recipe>> allRecipes;

    public RecipeViewModel(Application application) {
        super(application);
        recipeRepository = new RecipeRepository(application);
        allRecipes = recipeRepository.getAllRecipes();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public CompletableFuture<Recipe> findByIDFuture(final int customerId) {
        return recipeRepository.findByIDFuture(customerId);
    }

    public LiveData<List<Recipe>> getAllRecipes() {
        return allRecipes;
    }

    public void insert(Recipe recipe) {
        recipeRepository.insert(recipe);
    }

    public void deleteAll() {
        recipeRepository.deleteAll();
    }

    public void delete(Recipe recipe) {recipeRepository.delete(recipe);}

    public void update(Recipe recipe) {
        recipeRepository.updateRecipe(recipe);
    }
}
