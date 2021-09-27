package com.example.musicplayerapp;

import android.media.Image;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.concurrent.RecursiveAction;

public class MusicViewHolder extends RecyclerView.ViewHolder {
    private TextView mTvSongName, mTvArtist;
    private ImageView mIvSongImg;
    public MusicViewHolder(@NonNull View itemView) {
        super(itemView);
        initView(itemView);
    }

    private void initView(View view){
        mTvArtist = view.findViewById(R.id.tvArtistName);
        mTvSongName = view.findViewById(R.id.tvSongName);
        mIvSongImg = view.findViewById(R.id.ivSongImg);
    }
    public void setData(ResultsDTO resultsDTO){
        mTvSongName.setText(resultsDTO.getCollectionName());
        mTvArtist.setText(resultsDTO.getArtistName());
        Glide.with(mIvSongImg).load(mIvSongImg.getDrawable()).into(mIvSongImg);
    }
}
