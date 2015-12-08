package com.sandro.venta.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by t430 on 25/10/2015.
 */
public class SalesMan implements Parcelable {

    private int id;
    private String codSeller;
    private String name;
    private String user;
    private String pass;

    public SalesMan() {

    }

    private SalesMan(Parcel in) {
        super();
        this.codSeller = in.readString();
        this.name = in.readString();
        this.user = in.readString();
        this.pass = in.readString();
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodSeller() {
        return codSeller;
    }

    public void setCodSeller(String codSeller) {
        this.codSeller = codSeller;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(getCodSeller());
        parcel.writeString(getName());
        parcel.writeString(getUser());
        parcel.writeString(getPass());
    }

    public static final Parcelable.Creator<SalesMan> CREATOR = new Parcelable.Creator<SalesMan>() {
        public SalesMan createFromParcel(Parcel in) {
            return new SalesMan(in);
        }

        public SalesMan[] newArray(int size) {
            return new SalesMan[size];
        }
    };
}
