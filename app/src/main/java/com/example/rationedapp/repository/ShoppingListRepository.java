package com.example.rationedapp.repository;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;

import com.example.rationedapp.dao.PantryItemDao;
import com.example.rationedapp.dao.ShoppingListDao;
import com.example.rationedapp.database.PantryItemDatabase;
import com.example.rationedapp.database.ShoppingListDatabase;
import com.example.rationedapp.entity.PantryItem;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class ShoppingListRepository {
    private ShoppingListDao shoppingListDao;
    private LiveData<List<PantryItem>> allShoppingListItems;
    public ShoppingListRepository(Application application){
        ShoppingListDatabase db = ShoppingListDatabase.getInstance(application);
        shoppingListDao =db.shoppingListDao();
        allShoppingListItems= shoppingListDao.getAll();
    }
    // Room executes this query on a separate thread
    public LiveData<List<PantryItem>> getAllPantryItems() {
        return allShoppingListItems;
    }
    public void insert(final PantryItem pantryItem){
        ShoppingListDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                shoppingListDao.insert(pantryItem);
            }
        });
    }
    public void deleteAll(){
        ShoppingListDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                shoppingListDao.deleteAll();
            }
        });
    }
    public void delete(final PantryItem pantryItem){
        ShoppingListDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                shoppingListDao.delete(pantryItem);
            }
        });
    }
    public void updatePantryItem(final PantryItem pantryItem){
        ShoppingListDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                shoppingListDao.updatePantryItem(pantryItem);
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public CompletableFuture<PantryItem> findByIDFuture(final int PantryItemId) {
        return CompletableFuture.supplyAsync(new Supplier<PantryItem>() {
            @Override
            public PantryItem get() {
                return shoppingListDao.findByID(PantryItemId);
            }
        }, ShoppingListDatabase.databaseWriteExecutor);
    }
}
