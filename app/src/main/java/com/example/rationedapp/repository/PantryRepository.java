package com.example.rationedapp.repository;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;

import com.example.rationedapp.dao.PantryItemDao;
import com.example.rationedapp.database.PantryItemDatabase;
import com.example.rationedapp.entity.PantryItem;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class PantryRepository {
    private PantryItemDao pantryItemDao;
    private LiveData<List<PantryItem>> allPantryItems;
    public PantryRepository(Application application){
        PantryItemDatabase db = PantryItemDatabase.getInstance(application);
        pantryItemDao =db.pantryItemDao();
        allPantryItems= pantryItemDao.getAll();
    }
    // Room executes this query on a separate thread
    public LiveData<List<PantryItem>> getAllPantryItems() {
        return allPantryItems;
    }
    public void insert(final PantryItem pantryItem){
        PantryItemDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                pantryItemDao.insert(pantryItem);
            }
        });
    }
    public void deleteAll(){
        PantryItemDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                pantryItemDao.deleteAll();
            }
        });
    }
    public void delete(final PantryItem pantryItem){
        PantryItemDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                pantryItemDao.delete(pantryItem);
            }
        });
    }
    public void updatePantryItem(final PantryItem pantryItem){
        PantryItemDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                pantryItemDao.updatePantryItem(pantryItem);
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public CompletableFuture<PantryItem> findByIDFuture(final int PantryItemId) {
        return CompletableFuture.supplyAsync(new Supplier<PantryItem>() {
            @Override
            public PantryItem get() {
                return pantryItemDao.findByID(PantryItemId);
            }
        }, PantryItemDatabase.databaseWriteExecutor);
    }
}
