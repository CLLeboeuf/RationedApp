package com.example.rationedapp.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.rationedapp.entity.Recipe;

import java.util.List;

@Dao
public interface RecipeDao {
    @Query("SELECT * FROM Recipe ORDER BY name ASC")
    LiveData<List<Recipe>> getAll();
    @Query("SELECT * FROM Recipe WHERE uid = :mealId LIMIT 1")
    Recipe findByID(int mealId);
    @Insert
    void insert(Recipe recipe);
    @Delete
    void delete(Recipe recipe);
    @Update
    void updateMeal(Recipe recipe);
    @Query("DELETE FROM Recipe")
    void deleteAll();
}

