package com.example.rentapplication.ui;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.rentapplication.repository.RentDataSource;

/**
 * Factory for ViewModels
 */
public class ViewModelFactory implements ViewModelProvider.Factory {

    private final RentDataSource rentDataSource;

    public ViewModelFactory(RentDataSource rentDataSource) {
        this.rentDataSource = rentDataSource;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(RentViewModel.class)) {
            return (T) new RentViewModel(rentDataSource);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}