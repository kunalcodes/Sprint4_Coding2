package com.example.covid_tracker_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements ItemClickListener {

    private List<ResponseModel> modelList;
    private RecyclerView recyclerView;
    private ModelAdapter modelAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initViews();
        callApi();
    }

    private void callApi() {
        ApiService apiService = Network.getRetrofitInstance().create(ApiService.class);
        Call<List<ResponseModel>> call = apiService.getPost("daily.json");
        call.enqueue(new Callback<List<ResponseModel>>() {
            @Override
            public void onResponse(Call<List<ResponseModel>> call, Response<List<ResponseModel>> response) {
                if (response.body() != null) {
                    modelList = response.body();
                    setRecyclerViewAdapter();
                }
            }

            @Override
            public void onFailure(Call<List<ResponseModel>> call, Throwable t) {

            }
        });
    }

    private void setRecyclerViewAdapter() {
        modelAdapter = new ModelAdapter(modelList, HomeActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HomeActivity.this);
        recyclerView.setAdapter(modelAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
    }

    @Override
    public void onItemClicked(int position) {
        modelList.remove(position);
        modelAdapter.notifyDataSetChanged();

//        String uri = "https://www.masaischool.com/";
//        Intent openMasai = new Intent("android.intent.action.MASAI");
//        openMasai.putExtra("uri", uri);
    }
}