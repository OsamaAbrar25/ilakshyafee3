package com.example.ilakshyafee3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FeeConfirmationActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fee_confirmation);
        textView = findViewById(R.id.textView);


        ArrayList<FeeInfo> arrayList = new ArrayList<>();
        ArrayList<String> installmentList = new ArrayList<>();
        ArrayList<Integer> dueList = new ArrayList<>(), feeList = new ArrayList<>();
        arrayList = getIntent().getParcelableArrayListExtra("arrayList");
        if (arrayList != null) {

            Toast.makeText(FeeConfirmationActivity.this, ""+arrayList.get(0).getDueAmount(), Toast.LENGTH_SHORT).show();
            textView.setText(""+arrayList.get(0).getInstallmentName());
        }
    }
}
