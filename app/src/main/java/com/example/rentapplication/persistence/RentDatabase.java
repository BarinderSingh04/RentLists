package com.example.rentapplication.persistence;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Rent.class}, version = 1)
public abstract class RentDatabase extends RoomDatabase {

    private static RentDatabase databaseInstance;

    public static synchronized RentDatabase getInstance(Context context) {

        if (databaseInstance == null) {
            databaseInstance = Room.databaseBuilder(context.getApplicationContext(),
                    RentDatabase.class, "rent_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return databaseInstance;
    }

    public abstract RentDAO rentDAO();

}
