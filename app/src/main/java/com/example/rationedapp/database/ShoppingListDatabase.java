package com.example.rationedapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.rationedapp.dao.PantryItemDao;
import com.example.rationedapp.dao.ShoppingListDao;
import com.example.rationedapp.entity.PantryItem;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {PantryItem.class}, version = 1, exportSchema = false)
public abstract class ShoppingListDatabase extends RoomDatabase {
    public abstract ShoppingListDao shoppingListDao();
    private static ShoppingListDatabase INSTANCE;
    //we create an ExecutorService with a fixed thread pool so we can later run
    //database operations asynchronously on a background thread.
    private static final int NUMBER_OF_THREADS = 4;

    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    //A synchronized method in a multi threaded environment means that two threads
    //are not allowed to access data at the same time
    public static synchronized ShoppingListDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ShoppingListDatabase.class, "ShoppingListDatabase").fallbackToDestructiveMigration().build();
        }
        return INSTANCE;
    }
}
