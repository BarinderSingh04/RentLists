package com.example.rentapplication.repository;

import com.example.rentapplication.persistence.Rent;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface RentDataSource {

    Flowable<List<Rent>> getRent();

    Completable insertOrUpdate(Rent rent);

    void deleteAllRents();

    void delete(Rent rent);

}
