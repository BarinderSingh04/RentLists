package com.example.rentapplication.persistence;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "rent_table")
public class Rent {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String e_units;
    private String rent;
    private String e_bill;

    @Ignore
    public Rent(String name, String e_units, String rent, String e_bill) {
        this.name = name;
        this.e_units = e_units;
        this.rent = rent;
        this.e_bill = e_bill;
    }

    public Rent(int id, String name, String e_units, String rent, String e_bill) {
        this.id = id;
        this.name = name;
        this.e_units = e_units;
        this.rent = rent;
        this.e_bill = e_bill;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getE_units() {
        return e_units;
    }

    public String getRent() {
        return rent;
    }

    public String getE_bill() {
        return e_bill;
    }
}
