package com.example.ilakshyafee3;

import android.content.Context;
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
    private ArrayList<CheckBox> checkboxPosArrayList = new ArrayList<>();
    ArrayList<FeeInfo> arrayList = new ArrayList<>();
    private Context context;


    public FeeAdapter(List<FeeInfo> list, Context context) {
        this.list = list;
        this.context = context;
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
        int fee = list.get(position).getFeeAmount();
        int due = list.get(position).getDueAmount();
        String studentCode = list.get(position).getStudentCode();
        String schoolCode = list.get(position).getSchoolCode();
        String installmentName = list.get(position).getInstallmentName();
        holder.textView_fee.setText(Integer.toString(fee));
        holder.textView_due.setText(Integer.toString(due));
        holder.checkBox.setText(installmentName);


        holder.checkBox.setTag(Integer.toString(position));


        checkboxPosArrayList.add(holder.checkBox);

        if (position > 0) {
            holder.checkBox.setEnabled(false);
        }
        else {
            holder.checkBox.setEnabled(true);
        }


        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                FeeInfo feeInfo = new FeeInfo(installmentName, fee, due, studentCode, schoolCode);
                if (buttonView.isChecked()) {

                    FeeInfo feeInfo1 = list.get(position);
                        if (position < checkboxPosArrayList.size()-1 && feeInfo1.getDueAmount() > 0) {
                                checkboxPosArrayList.get(position + 1).setEnabled(true);
                        }

                    if (list != null) {
                        arrayList.add(feeInfo);
                    }
                    buttonView.setChecked(true);
                }
                else {

                    if (position < checkboxPosArrayList.size()-1) {
                        int i;
                        for (i=position+1;i<checkboxPosArrayList.size();i++) {
                            checkboxPosArrayList.get(i).setChecked(false);
                            checkboxPosArrayList.get(i).setEnabled(false);
                        }
                    }

                    if (list != null) {
                    arrayList.remove(position);
                    }
                    buttonView.setChecked(false);
                }
            }
        });

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

    public ArrayList<FeeInfo> getCheckedData() {
        return arrayList;
    }
}
