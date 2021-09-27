package com.example.musicplayerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText etSearch;
    private Button mBtnSearch, mBtnPause, mBtnPlay, mBtnResume,mBtnDelete,mBtnStop;
    private RecyclerView recyclerView;
    private ArrayList<ResultsDTO> resultsList;
    private ArrayList<ResultsDTO> responseList;
    private MusicService musicService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView(){
        etSearch= findViewById(R.id.etSearchSong);
        mBtnSearch = findViewById(R.id.btnSearch);
        recyclerView = findViewById(R.id.recyclerView);
        mBtnDelete=  findViewById(R.id.btnDelete);
        mBtnPause = findViewById(R.id.btnPause);
        mBtnResume = findViewById(R.id.btnResume);
        mBtnPlay = findViewById(R.id.btnPlay);
        mBtnStop = findViewById(R.id.btnStop);


        mBtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callApi();

            }
        });

        startMusicService();
        mBtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (musicService!=null)
                    musicService.play();
            }
        });
        mBtnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (musicService!=null)
                    musicService.pause();
            }
        });
        mBtnResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (musicService!=null)
                musicService.resume();
            }
        });
        mBtnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (musicService!=null)
                musicService.stop();
            }
        });
    }

    private void callApi(){
        ApiService apiService = Network.getInstance().create(ApiService.class);
        String trackName= etSearch.getText().toString();
        apiService.getSong(trackName).enqueue(new Callback<ResponseDTO>() {
            @Override
            public void onResponse(Call<ResponseDTO> call, Response<ResponseDTO> response) {
                if (response.body()!=null){
                    resultsList.addAll(response.body());
                    setRecyclerView();
                }

            }

            @Override
            public void onFailure(Call<ResponseDTO> call, Throwable t) {

            }
        });

    }
    private void setRecyclerView(){
        MusicAdapter adapter = new MusicAdapter(resultsList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicService.ServiceBinder serviceBinder = (MusicService.ServiceBinder)service;
            musicService = serviceBinder.getMusicService();

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private void startMusicService(){
        Intent intent = new Intent(MainActivity.this,MusicService.class);
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }

}