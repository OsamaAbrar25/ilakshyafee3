package com.example.ilakshyafee3;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class FeeInfo implements Parcelable {
    private String installmentName;
    private int feeAmount;
    private int dueAmount;

    public FeeInfo(String installmentName, int feeAmount, int dueAmount) {
        this.installmentName = installmentName;
        this.feeAmount = feeAmount;
        this.dueAmount = dueAmount;
    }

    public FeeInfo(Parcel in) {
        this.installmentName = in.readString();
        this.feeAmount = in.readInt();
        this.dueAmount = in.readInt();
    }

    public String getInstallmentName() {
        return installmentName;
    }

    public void setInstallmentName(String installmentName) {
        this.installmentName = installmentName;
    }

    public int getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(int feeAmount) {
        this.feeAmount = feeAmount;
    }

    public int getDueAmount() {
        return dueAmount;
    }

    public void setDueAmount(int dueAmount) {
        this.dueAmount = dueAmount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(installmentName);
        dest.writeInt(dueAmount);
        dest.writeInt(feeAmount);
    }
    public static final Parcelable.Creator<FeeInfo> CREATOR = new Parcelable.Creator<FeeInfo>() {
        public FeeInfo createFromParcel(Parcel in) {
            return new FeeInfo(in);
        }

        public FeeInfo[] newArray(int size) {
            return new FeeInfo[size];

        }
    };
}
