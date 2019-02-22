package com.example.rentapplication;

import android.content.Context;

import com.example.rentapplication.persistence.RentDatabase;
import com.example.rentapplication.repository.RentDataSource;
import com.example.rentapplication.repository.RentRepository;
import com.example.rentapplication.ui.ViewModelFactory;

public class Inject {

    public static RentDataSource provideRentDataSource(Context context) {
        RentDatabase database = RentDatabase.getInstance(context);
        return new RentRepository(database.rentDAO());
    }

    public static ViewModelFactory provideViewModelFactory(Context context) {
        RentDataSource rentDataSource = provideRentDataSource(context);
        return new ViewModelFactory(rentDataSource);
    }

}
