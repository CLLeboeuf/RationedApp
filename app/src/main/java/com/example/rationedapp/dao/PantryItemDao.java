package com.example.rationedapp.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.rationedapp.entity.PantryItem;

import java.util.List;

@Dao
public interface PantryItemDao {
    @Query("SELECT * FROM PantryItem ORDER BY name ASC")
    LiveData<List<PantryItem>> getAll();
    @Query("SELECT * FROM pantryitem WHERE uid = :pantryItemId LIMIT 1")
    PantryItem findByID(int pantryItemId);
    @Insert
    void insert(PantryItem pantryItem);
    @Delete
    void delete(PantryItem pantryItem);
    @Update
    void updatePantryItem(PantryItem pantryItem);
    @Query("DELETE FROM pantryItem")
    void deleteAll();
    @Query("DELETE FROM PantryItem WHERE uid = :id")
    void deleteById(int id);
    @Query("DELETE FROM PantryItem WHERE name = :name")
    void deleteByName(String name);
}

