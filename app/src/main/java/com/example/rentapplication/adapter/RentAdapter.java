package com.example.rentapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rentapplication.persistence.Rent;
import com.example.rentapplication.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RentAdapter extends RecyclerView.Adapter<RentAdapter.ViewHolder> {

    Context context;
    List<Rent> rentList;

    public RentAdapter(Context context, List<Rent> rentList) {
        this.context = context;
        this.rentList = rentList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.rent_item, parent, false);
        return new ViewHolder(v);
    }

    public Rent getRent(int position) {
        return rentList.get(position);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_item.setText(rentList.get(position).getId() + " " + rentList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return rentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_item = itemView.findViewById(R.id.tv_user);
        }
    }
}
