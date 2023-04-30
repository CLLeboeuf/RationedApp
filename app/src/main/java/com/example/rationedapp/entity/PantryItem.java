package com.example.rationedapp.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PantryItem {
    @PrimaryKey(autoGenerate = true)
    public int uid;
    @ColumnInfo(name = "name")
    @NonNull
    public String name;
    public String category;
    public String measurementType;
    public double measurementAmount;
    public PantryItem( @NonNull String name, @NonNull String category, String measurementType, double measurementAmount ) {
        this.name=name;
        this.category=category;
        this.measurementType = measurementType;
        this.measurementAmount = measurementAmount;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMeasurementType() {
        return measurementType;
    }

    public void setMeasurementType(String measurementType) {
        this.measurementType = measurementType;
    }

    public double getMeasurementAmount() {
        return measurementAmount;
    }

    public void setMeasurementAmount(double measurementAmount) {
        this.measurementAmount = measurementAmount;
    }
}
