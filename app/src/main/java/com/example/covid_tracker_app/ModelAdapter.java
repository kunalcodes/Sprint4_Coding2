package com.example.covid_tracker_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ModelAdapter extends RecyclerView.Adapter<ModelViewHolder> {

    private List<ResponseModel> modelList;
    private ItemClickListener itemClickListener;

    public ModelAdapter(List<ResponseModel> modelList, ItemClickListener itemClickListener){
        this.itemClickListener =itemClickListener;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public ModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ModelViewHolder(view,itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ModelViewHolder holder, int position) {
        ResponseModel responseModel = modelList.get(position);
        holder.setData(responseModel);
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
