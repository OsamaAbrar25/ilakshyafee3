package com.example.ilakshyafee3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ConfirmationAdapter extends RecyclerView.Adapter<ConfirmationAdapter.ConfirmationHolder> {
    private ArrayList<FeeInfo> arrayList;

    public ConfirmationAdapter(ArrayList<FeeInfo> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ConfirmationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.fee_confirmation_holder, parent, false);
        return new ConfirmationHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConfirmationHolder holder, int position) {
        holder.textView_month.setText(arrayList.get(position).getInstallmentName());
        holder.textView_due2.setText(Integer.toString(arrayList.get(position).getDueAmount()));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ConfirmationHolder extends RecyclerView.ViewHolder {
        TextView textView_month, textView_due2;
        public ConfirmationHolder(@NonNull View itemView) {
            super(itemView);
            textView_month = itemView.findViewById(R.id.textView_month);
            textView_due2 = itemView.findViewById(R.id.textView_due2);
        }
    }
}
