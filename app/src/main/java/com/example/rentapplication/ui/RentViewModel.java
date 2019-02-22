package com.example.rentapplication.ui;

import com.example.rentapplication.persistence.Rent;
import com.example.rentapplication.repository.RentDataSource;

import java.util.List;

import androidx.lifecycle.ViewModel;
import io.reactivex.Completable;
import io.reactivex.Flowable;

public class RentViewModel extends ViewModel {

    private RentDataSource rentDataSource;
    private Rent rent;

    public RentViewModel(RentDataSource rentDataSource) {
        this.rentDataSource = rentDataSource;
    }

    public Flowable<List<Rent>> getRent() {
        return rentDataSource.getRent();
    }

    public Completable insertRent(String info) {
        rent = rent == null
                ? new Rent(info, "fgf", "fjf", "jhgg")
                : new Rent(rent.getId(), info, "fgf", "fjf", "jhgg");
        return rentDataSource.insertOrUpdate(rent);
    }

    public void deleteAll() {
        rentDataSource.deleteAllRents();
    }

    public void delete(Rent rent) {
        rentDataSource.delete(rent);
    }
}
