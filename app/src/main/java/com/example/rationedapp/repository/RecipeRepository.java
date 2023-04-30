package com.example.rationedapp.repository;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;

import com.example.rationedapp.dao.RecipeDao;
import com.example.rationedapp.database.RecipeDatabase;
import com.example.rationedapp.entity.Recipe;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class RecipeRepository {
    private RecipeDao recipeDao;
    private LiveData<List<Recipe>> allMeals;
    public RecipeRepository(Application application){
        RecipeDatabase db = RecipeDatabase.getInstance(application);
        recipeDao =db.recipeDao();
        allMeals= recipeDao.getAll();
    }
    // Room executes this query on a separate thread
    public LiveData<List<Recipe>> getAllRecipes() {
        return allMeals;
    }
    public void insert(final Recipe Recipe){
        RecipeDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                recipeDao.insert(Recipe);
            }
        });
    }
    public void deleteAll(){
        RecipeDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                recipeDao.deleteAll();
            }
        });
    }
    public void delete(final Recipe Recipe){
        RecipeDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                recipeDao.delete(Recipe);
            }
        });
    }
    public void updateRecipe(final Recipe recipe){
        RecipeDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                recipeDao.updateMeal(recipe);
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public CompletableFuture<Recipe> findByIDFuture(final int MealId) {
        return CompletableFuture.supplyAsync(new Supplier<Recipe>() {
            @Override
            public Recipe get() {
                return recipeDao.findByID(MealId);
            }
        }, RecipeDatabase.databaseWriteExecutor);
    }
}
