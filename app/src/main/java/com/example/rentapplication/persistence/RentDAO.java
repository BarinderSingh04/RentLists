package com.example.rentapplication.persistence;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface RentDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(Rent rent);

    @Update
    void update(Rent rent);

    @Delete
    void delete(Rent rent);

    @Query("DELETE FROM rent_table")
    void deleteAll();

    @Query("SELECT * FROM rent_table")
    Flowable<List<Rent>> getAllRents();

}
