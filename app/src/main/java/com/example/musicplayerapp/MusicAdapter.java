package com.example.musicplayerapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MusicAdapter extends RecyclerView.Adapter<MusicViewHolder> {

    ArrayList<ResultsDTO> resultsList;
    public MusicAdapter( ArrayList<ResultsDTO> resultsList){
        this.resultsList= resultsList;
    }

    @NonNull
    @Override
    public MusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent, false);
        return new MusicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicViewHolder holder, int position) {
        holder.setData(resultsList.get(position));

    }

    @Override
    public int getItemCount() {
        return resultsList.size();
    }
}
