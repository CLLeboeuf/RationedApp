package com.example.rationedapp.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


@Entity
public class Recipe {

    @PrimaryKey(autoGenerate = true)
    public int uid;
    @ColumnInfo(name = "name")
    @NonNull
    public String name;
    @ColumnInfo(name = "description")
    public String description;

    public Recipe (String name, String description){
        this.name = name;
        this.description = description;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
