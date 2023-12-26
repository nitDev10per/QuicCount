package com.example.myapplication;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class contectModel2 implements Parcelable {
    boolean ok=false;
    String no;
    String date;
    String time;
    String count;
    String rate;
    contectModel2(  String no, String date, String time, String count, String rate){
        this.count=count;
        this.date=date;
        this.no=no;
        this.rate=rate;
        this.time=time;

    }
    public void setOk(boolean ok){
        this.ok=ok;
    }

    protected contectModel2(Parcel in) {
        no = in.readString();
        date = in.readString();
        time = in.readString();
        count = in.readString();
        rate = in.readString();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ok=in.readBoolean();
        }
    }

    public static final Creator<contectModel2> CREATOR = new Creator<contectModel2>() {
        @Override
        public contectModel2 createFromParcel(Parcel in) {
            return new contectModel2(in);
        }

        @Override
        public contectModel2[] newArray(int size) {
            return new contectModel2[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(no);
        parcel.writeString(date);
        parcel.writeString(time);
        parcel.writeString(count);
        parcel.writeString(rate);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            parcel.writeBoolean(ok);
        }
    }
}
