package com.example.ilakshyafee3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FeeAdapter extends RecyclerView.Adapter<FeeAdapter.FeeHolder> {
    private List<FeeInfo> list;
    private boolean ch[];
    private ArrayList<Integer> arrayList;


    public FeeAdapter(List<FeeInfo> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public FeeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.fee_holder, parent, false);
        return new FeeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeeHolder holder, int position) {
        holder.textView_fee.setText(Integer.toString(list.get(position).getFeeAmount()));
        holder.textView_due.setText(Integer.toString(list.get(position).getDueAmount()));
        holder.checkBox.setText(list.get(position).getInstallmentName());

        holder.checkBox.setTag(Integer.toString(position));
        //arrayList.add(holder.checkBox);
        if (holder.checkBox.isChecked()) {
            arrayList.add((Integer) holder.checkBox.getTag(position));
        }
        /*if (position > 0) {
            FeeInfo feeInfo = list.get(position-1);
            if (feeInfo.getDueAmount() > 0) {
                if (holder.checkBox.isSelected()) {
                    holder.checkBox.setEnabled(true);
                }
                else {
                    holder.checkBox.setEnabled(false);
                }
            }
            else {
                holder.checkBox.setEnabled(true);
            }
        }*/
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });

        /*ch = new boolean[list.size()];
        //ch[position]=false;
        if (list.get(position).getDueAmount() != 0 && !holder.checkBox.isChecked() && position<list.size()-1) {
            ch[position+1] = false;

            //holder.checkBox
        }
        holder.checkBox.setEnabled(ch[position]);
        //if (holder.checkBox.isChecked()) {}
        */

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class FeeHolder extends RecyclerView.ViewHolder {
        TextView textView_fee, textView_due;
        CheckBox checkBox;
        public FeeHolder(@NonNull View itemView) {
            super(itemView);
            textView_fee = itemView.findViewById(R.id.textView_fee);
            textView_due = itemView.findViewById(R.id.textView_due);
            checkBox = itemView.findViewById(R.id.checkBox);

        }
    }
}
