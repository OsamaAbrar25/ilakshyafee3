package com.example.ilakshyafee3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FeeAdapter feeAdapter;
    private Button button;
    ArrayList<FeeInfo> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView2);
        button = findViewById(R.id.button);

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
                feeAdapter = new FeeAdapter(response.body(), MainActivity.this);
                feeAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(feeAdapter);
                arrayList = feeAdapter.getCheckedData();

                Toast.makeText(MainActivity.this, ""+arrayList, Toast.LENGTH_SHORT).show();
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (arrayList.size()!=0) {
                            Intent intent = new Intent(MainActivity.this, FeeConfirmationActivity.class);
                            intent.putParcelableArrayListExtra("arrayList", arrayList);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Please select atleast one", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

            @Override
            public void onFailure(Call<List<FeeInfo>> call, Throwable t) {

            }
        });


    }
}
