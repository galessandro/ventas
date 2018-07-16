package com.sandro.venta.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Creado por chambo on 25/10/2015.
 */
public class Item implements Parcelable{

    private Integer codSale;
    private Product product;
    private Double quantity;
    private Double price;
    private String typePrice;
    private Date dateReg;
    private int level;
    private Double priceLevelFrom;
    private Double priceLevelTo;
    private int priceOfList;

    public Item() {

    }

    private Item(Parcel in) {
        super();
        this.codSale = in.readInt();
        this.product = in.readParcelable(Product.class.getClassLoader());
        this.quantity = in.readDouble();
        this.price = in.readDouble();
        this.typePrice = in.readString();
        this.level = in.readInt();
        this.priceLevelFrom = in.readDouble();
        this.priceLevelTo = in.readDouble();
        this.priceOfList = in.readInt();
    }

    public Integer getCodSale() {
        return codSale;
    }

    public void setCodSale(Integer codSale) {
        this.codSale = codSale;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getTypePrice() {
        return typePrice;
    }

    public void setTypePrice(String typePrice) {
        this.typePrice = typePrice;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getDateReg() {
        return dateReg;
    }

    public void setDateReg(Date dateReg) {
        this.dateReg = dateReg;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Double getPriceLevelFrom() {
        return priceLevelFrom;
    }

    public void setPriceLevelFrom(Double priceLevelFrom) {
        this.priceLevelFrom = priceLevelFrom;
    }

    public Double getPriceLevelTo() {
        return priceLevelTo;
    }

    public void setPriceLevelTo(Double priceLevelTo) {
        this.priceLevelTo = priceLevelTo;
    }

    public int getPriceOfList() {
        return priceOfList;
    }

    public void setPriceOfList(int priceOfList) {
        this.priceOfList = priceOfList;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(getCodSale() == null ? 0 : getCodSale());
        parcel.writeParcelable(getProduct(), flags);
        parcel.writeDouble(getQuantity());
        parcel.writeDouble(getPrice());
        parcel.writeString(getTypePrice());
        parcel.writeInt(getLevel());
        parcel.writeDouble(getPriceLevelFrom());
        parcel.writeDouble(getPriceLevelTo());
        parcel.writeInt(getPriceOfList());
    }

    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}
