package com.example.rentapplication.repository;

import com.example.rentapplication.persistence.Rent;
import com.example.rentapplication.persistence.RentDAO;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class RentRepository implements RentDataSource {

    private RentDAO rentDAO;

    public RentRepository(RentDAO rentDAO) {
        this.rentDAO = rentDAO;
    }

    @Override
    public Flowable<List<Rent>> getRent() {
        return rentDAO.getAllRents();
    }

    @Override
    public Completable insertOrUpdate(Rent rent) {
        return rentDAO.insert(rent);
    }

    @Override
    public void deleteAllRents() {
        rentDAO.deleteAll();
    }

    @Override
    public void delete(Rent rent) {
        rentDAO.delete(rent);
    }
}
