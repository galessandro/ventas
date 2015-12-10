package com.sandro.venta.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Creado por ggranados on 25/10/2015.
 */
public class Order implements Parcelable{

    public static int PAYMENT_TYPE_CASH = 1;
    public static int PAYMENT_TYPE_CREDIT = 2;
    public static String PAYMENT_TYPE_DESC_CASH = "Contado";
    public static String PAYMENT_TYPE_DESC_CREDIT = "Credito";
    private int codSale;
    private int codOrder;
    private Date dateOrder;
    private Client client;
    private SalesMan seller;
    private Date dateDelivery;
    private List<Item> items;
    private Date dateReg;
    private int paymentType;

    public Order (){
        items = new ArrayList<>();
    }

    private Order(Parcel in) {
        super();
        this.codSale = in.readInt();
        this.codOrder = in.readInt();
        this.dateOrder = new Date(in.readLong());
        this.client = in.readParcelable(Client.class.getClassLoader());
        this.seller = in.readParcelable(SalesMan.class.getClassLoader());
        this.dateDelivery = new Date(in.readLong());
        items = new ArrayList<>();
        in.readTypedList(items, Item.CREATOR);
        this.paymentType = in.readInt();
    }

    public int getCodSale() {
        return codSale;
    }

    public void setCodSale(int codSale) {
        this.codSale = codSale;
    }

    public int getCodOrder() {
        return codOrder;
    }

    public void setCodOrder(int codOrder) {
        this.codOrder = codOrder;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public SalesMan getSeller() {
        return seller;
    }

    public void setSeller(SalesMan seller) {
        this.seller = seller;
    }

    public Date getDateDelivery() {
        return dateDelivery;
    }

    public void setDateDelivery(Date dateDelivery) {
        this.dateDelivery = dateDelivery;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Date getDateReg() {
        return dateReg;
    }

    public void setDateReg(Date dateReg) {
        this.dateReg = dateReg;
    }

    public int getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(int paymentType) {
        this.paymentType = paymentType;
    }

    public Double getTotalAmount(){
        Double totalAmount = 0d;
        for (Item item : items) {
            totalAmount += item.getQuantity() * item.getPrice();
        }
        return totalAmount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(getCodSale());
        parcel.writeInt(getCodOrder());
        parcel.writeLong(getDateOrder().getTime());
        parcel.writeParcelable(getClient(), flags);
        parcel.writeParcelable(getSeller(), flags);
        parcel.writeLong(getDateDelivery().getTime());
        parcel.writeTypedList(getItems());
        parcel.writeInt(getPaymentType());
    }

    public static final Parcelable.Creator<Order> CREATOR = new Parcelable.Creator<Order>() {
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        public Order[] newArray(int size) {
            return new Order[size];
        }
    };
}
