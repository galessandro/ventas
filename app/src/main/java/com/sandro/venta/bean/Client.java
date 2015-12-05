package com.sandro.venta.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Creado por gg on 25/10/2015.
 */
public class Client implements Parcelable {

    private int id;
    private int codClient;
    private String firstName;
    private String lastName;
    private String address;
    private String ruc;
    private String dni;
    private Date dateReg;

    public Client() {
    }

    public Client(int codClient, String firstName, String lastName, String address, String ruc, String dni) {
        this.codClient = codClient;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.ruc = ruc;
        this.dni = dni;
    }

    private Client(Parcel in) {
        super();
        this.id = in.readInt();
        this.codClient = in.readInt();
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.address = in.readString();
        this.ruc = in.readString();
        this.dni = in.readString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBusinessName() {
        return lastName + " " + firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getCodClient() {
        return codClient;
    }

    public void setCodClient(int codClient) {
        this.codClient = codClient;
    }

    public Date getDateReg() {
        return dateReg;
    }

    public void setDateReg(Date dateReg) {
        this.dateReg = dateReg;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(getId());
        parcel.writeInt(getCodClient());
        parcel.writeString(getFirstName());
        parcel.writeString(getLastName());
        parcel.writeString(getAddress());
        parcel.writeString(getRuc());
        parcel.writeString(getDni());
    }

    public static final Parcelable.Creator<Client> CREATOR = new Parcelable.Creator<Client>() {
        public Client createFromParcel(Parcel in) {
            return new Client(in);
        }

        public Client[] newArray(int size) {
            return new Client[size];
        }
    };
}
