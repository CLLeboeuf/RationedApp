package com.example.rationedapp.viewmodel;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.rationedapp.entity.PantryItem;
import com.example.rationedapp.repository.ShoppingListRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ShoppingListViewModel extends AndroidViewModel {
    private ShoppingListRepository slRepository;
    private LiveData<List<PantryItem>> allPantryItems;

    public ShoppingListViewModel(Application application) {
        super(application);
        slRepository = new ShoppingListRepository(application);
        allPantryItems = slRepository.getAllPantryItems();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public CompletableFuture<PantryItem> findByIDFuture(final int customerId) {
        return slRepository.findByIDFuture(customerId);
    }

    public LiveData<List<PantryItem>> getAllPantryItems() {
        return allPantryItems;
    }

    public void insert(PantryItem pantryItem) {
        slRepository.insert(pantryItem);
    }

    public void deleteAll() {
        slRepository.deleteAll();
    }

    public void delete(PantryItem pantryItem) {slRepository.delete(pantryItem);}

    public void update(PantryItem pantryItem) {
        slRepository.updatePantryItem(pantryItem);
    }
}
