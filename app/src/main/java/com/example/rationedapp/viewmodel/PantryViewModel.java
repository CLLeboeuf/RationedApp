package com.example.rationedapp.viewmodel;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.rationedapp.entity.PantryItem;
import com.example.rationedapp.repository.PantryRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class PantryViewModel extends AndroidViewModel {
    private PantryRepository pRepository;
    private LiveData<List<PantryItem>> allPantryItems;

    public PantryViewModel(Application application) {
        super(application);
        pRepository = new PantryRepository(application);
        allPantryItems = pRepository.getAllPantryItems();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public CompletableFuture<PantryItem> findByIDFuture(final int customerId) {
        return pRepository.findByIDFuture(customerId);
    }

    public LiveData<List<PantryItem>> getAllPantryItems() {
        return allPantryItems;
    }

    public void insert(PantryItem pantryItem) {
        pRepository.insert(pantryItem);
    }

    public void deleteAll() {
        pRepository.deleteAll();
    }

    public void delete(PantryItem pantryItem) {pRepository.delete(pantryItem);}

    public void update(PantryItem pantryItem) {
        pRepository.updatePantryItem(pantryItem);
    }
}
