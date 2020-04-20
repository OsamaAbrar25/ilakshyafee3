package com.example.ilakshyafee3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FeeAdapter feeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView2);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplication());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ilakshyatest.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        FeeApi feeApi = retrofit.create(FeeApi.class);
        Call<List<FeeInfo>> call = feeApi.getData();
        call.enqueue(new Callback<List<FeeInfo>>() {
            @Override
            public void onResponse(Call<List<FeeInfo>> call, Response<List<FeeInfo>> response) {
                Log.e("json", response.toString());
                feeAdapter = new FeeAdapter(response.body());
                recyclerView.setAdapter(feeAdapter);
            }

            @Override
            public void onFailure(Call<List<FeeInfo>> call, Throwable t) {

            }
        });
    }
}
