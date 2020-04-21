package com.example.ilakshyafee3;

import android.os.Parcel;
import android.os.Parcelable;

public class FeeInfo implements Parcelable {
    private String installmentName;
    private int feeAmount;
    private int dueAmount;
    private String studentCode;
    private String schoolCode;

    public FeeInfo(String installmentName, int feeAmount, int dueAmount, String studentCode, String schoolCode) {
        this.installmentName = installmentName;
        this.feeAmount = feeAmount;
        this.dueAmount = dueAmount;
        this.studentCode = studentCode;
        this.schoolCode = schoolCode;
    }

    public FeeInfo(Parcel in) {
        this.installmentName = in.readString();
        this.feeAmount = in.readInt();
        this.dueAmount = in.readInt();
        this.studentCode = in.readString();
        this.schoolCode = in.readString();
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

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(installmentName);
        dest.writeInt(feeAmount);
        dest.writeInt(dueAmount);
        dest.writeString(studentCode);
        dest.writeString(schoolCode);
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
