package com.example.ilakshyafee3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FeeConfirmationActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ConfirmationAdapter confirmationAdapter;
    TextView textView_net, textView_net_amt, textView_scode, textView_name, textView_head;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fee_confirmation);

        textView_net = findViewById(R.id.textView_net);
        textView_net_amt = findViewById(R.id.textView_net_amt);
        textView_scode = findViewById(R.id.textView_scode);
        textView_head = findViewById(R.id.textView_head);
        textView_name = findViewById(R.id.textView_name);
        recyclerView = findViewById(R.id.recyclerView2);
        imageView = findViewById(R.id.imageView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplication());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        ArrayList<FeeInfo> arrayList = new ArrayList<>();
        arrayList = getIntent().getParcelableArrayListExtra("arrayList");
        if (arrayList != null) {
            confirmationAdapter = new ConfirmationAdapter(arrayList);
            recyclerView.setAdapter(confirmationAdapter);

            //Toast.makeText(FeeConfirmationActivity.this, ""+arrayList.get(0).getDueAmount(), Toast.LENGTH_SHORT).show();
            int i, amt_payable = 0;
            for (i=0;i<arrayList.size();i++) {
                amt_payable = amt_payable + arrayList.get(i).getDueAmount();
            }
            textView_net_amt.setText("Rs. ".concat(Integer.toString(amt_payable)));
            textView_name.setText(arrayList.get(0).getStudentCode());
            textView_scode.setText(arrayList.get(0).getSchoolCode());
        }

    }
}
