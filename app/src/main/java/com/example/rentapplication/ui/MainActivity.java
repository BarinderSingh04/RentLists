package com.example.rentapplication.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.rentapplication.Inject;
import com.example.rentapplication.R;
import com.example.rentapplication.adapter.RentAdapter;
import com.example.rentapplication.persistence.Rent;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    Button btn1, btn2;
    RecyclerView recyclerView;
    RentAdapter adapter;
    TextInputEditText tv_rent;
    private RentViewModel viewModel;
    private ViewModelFactory viewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.add);
        btn2 = findViewById(R.id.remove);
        recyclerView = findViewById(R.id.recycler);
        tv_rent = findViewById(R.id.tv_rent);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);

        viewModelFactory = Inject.provideViewModelFactory(MainActivity.this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(RentViewModel.class);
        setItemTouchHelper();

    }

    @Override
    protected void onStart() {
        super.onStart();
        compositeDisposable.add(viewModel.getRent().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(rents -> {
                    setAdapter(rents);
                }));
    }

    private void setAdapter(List<Rent> rents) {

        adapter = new RentAdapter(MainActivity.this, rents);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(adapter);

    }

    private void setItemTouchHelper() {
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                compositeDisposable.add(Completable.fromAction(() ->
                        viewModel.delete(adapter.getRent(viewHolder.getAdapterPosition())))
                        .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(() -> onDelete()));
            }

        }).attachToRecyclerView(recyclerView);
    }

    @Override
    protected void onStop() {
        super.onStop();
        compositeDisposable.clear();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.add:
                compositeDisposable.add(viewModel.insertRent(tv_rent.getText().toString()).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread()).subscribe(() -> onInsert()));
                break;

            case R.id.remove:
                compositeDisposable.add(Completable.fromAction(() ->
                        viewModel.deleteAll()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(() -> onDelete()));
                break;

        }
    }

    public void onDelete() {
        Toast.makeText(MainActivity.this, "deleted successful", Toast.LENGTH_SHORT).show();
    }

    public void onInsert() {
        Toast.makeText(MainActivity.this, "added successfully...", Toast.LENGTH_SHORT).show();
    }

}
