package com.sandro.venta.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.sandro.venta.api.model.SellerResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by t430 on 25/10/2015.
 */
public class SalesMan implements Parcelable {

    private int id;
    private int controlId;
    private String imei;
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
        this.imei = in.readString();
        this.controlId = in.readInt();
        this.id = in.readInt();
    }

    public int getControlId() {
        return controlId;
    }

    public void setControlId(int controlId) {
        this.controlId = controlId;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
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
        parcel.writeString(getImei());
        parcel.writeInt(getControlId());
        parcel.writeInt(getId());
    }

    public static final Parcelable.Creator<SalesMan> CREATOR = new Parcelable.Creator<SalesMan>() {
        public SalesMan createFromParcel(Parcel in) {
            return new SalesMan(in);
        }

        public SalesMan[] newArray(int size) {
            return new SalesMan[size];
        }
    };

    public static SalesMan toSalesMan(SellerResponse seller){
        SalesMan salesMan = new SalesMan();
        salesMan.setId(seller.getId());
        salesMan.setCodSeller(seller.getCODVEN());
        salesMan.setControlId(seller.getControlId());
        salesMan.setImei(seller.getIMEI());
        salesMan.setName(seller.getNOMVEN());
        salesMan.setUser(seller.getCODVEN());
        salesMan.setPass("123");
        return salesMan;
    }

    public static List<SalesMan> toSalesManList(List<SellerResponse> sellerList){
        if(sellerList==null) return null;

        List<SalesMan> salesManList = new ArrayList<>();
        for (SellerResponse seller: sellerList) {
            SalesMan salesMan = new SalesMan();
            salesMan.setId(seller.getId());
            salesMan.setCodSeller(seller.getCODVEN());
            salesMan.setControlId(seller.getControlId());
            salesMan.setImei(seller.getIMEI());
            salesMan.setName(seller.getNOMVEN());
            salesMan.setUser(seller.getCODVEN());
            salesMan.setPass("123");
            salesManList.add(salesMan);
        }

        return salesManList;
    }


}
