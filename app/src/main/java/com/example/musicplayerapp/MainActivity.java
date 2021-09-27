package com.example.musicplayerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText etSearch;
    private Button mBtnSearch;
    private RecyclerView recyclerView;
    private ArrayList<ResultsDTO> resultsList;
    private ArrayList<ResponseDTO> responseList;


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

        mBtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callApi();

            }
        });
    }

    private void callApi(){
        ApiService apiService = Network.getInstance().create(ApiService.class);
        String trackName= etSearch.getText().toString();
        apiService.getSong(trackName).enqueue(new Callback<ResponseDTO>() {
            @Override
            public void onResponse(Call<ResponseDTO> call, Response<ResponseDTO> response) {

                setRecyclerView();
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

}