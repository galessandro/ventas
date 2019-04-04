package com.sandro.venta.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.sandro.venta.api.model.CustomerResponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private String codSeller;
    private Date dateReg;

    public Client() {
    }

    public Client(int codClient, String firstName, String lastName,
                  String address, String ruc, String dni,
                  String codSeller) {
        this.codClient = codClient;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.ruc = ruc;
        this.dni = dni;
        this.codSeller = codSeller;
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
        this.codSeller = in.readString();
    }

    public String getCodSeller() {
        return codSeller;
    }

    public void setCodSeller(String codSeller) {
        this.codSeller = codSeller;
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
        parcel.writeString(getCodSeller());
    }

    public static final Parcelable.Creator<Client> CREATOR = new Parcelable.Creator<Client>() {
        public Client createFromParcel(Parcel in) {
            return new Client(in);
        }

        public Client[] newArray(int size) {
            return new Client[size];
        }
    };

    public static List<Client> toClientList(List<CustomerResponse> customerResponseList) {
        if (customerResponseList == null) return null;
        List<Client> clientList = new ArrayList<>();
        for (CustomerResponse customerResponse: customerResponseList) {
            Client client = new Client();
            client.setId(customerResponse.getId());
            client.setAddress(customerResponse.getUBIGEOCOMPLETO());
            client.setId(customerResponse.getId());
            client.setDni(customerResponse.getDNI());
            client.setRuc(customerResponse.getRUC());
            client.setFirstName(customerResponse.getNOMBRE());
            client.setLastName(customerResponse.getNOMBRE());
            client.setCodSeller(customerResponse.getCODVEN());
            client.setCodClient(Integer.parseInt(customerResponse.getCODCLI()));
            clientList.add(client);
        }
        return clientList;
    }
}
