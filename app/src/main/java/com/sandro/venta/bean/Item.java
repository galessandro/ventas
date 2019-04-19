package com.sandro.venta.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Creado por chambo on 25/10/2015.
 */
public class Item implements Parcelable{

    private Integer id;
    private Integer orderId;
    private Product product;
    private Double quantity;
    private Double price;
    private String typePrice;
    private Date dateReg;
    private int level;
    private Double levelRangeFrom;
    private Double levelRangeTo;
    private int priceOfList;

    public Item() {

    }

    private Item(Parcel in) {
        super();
        this.id = in.readInt();
        this.orderId = in.readInt();
        this.product = in.readParcelable(Product.class.getClassLoader());
        this.quantity = in.readDouble();
        this.price = in.readDouble();
        this.typePrice = in.readString();
        this.level = in.readInt();
        this.levelRangeFrom = in.readDouble();
        this.levelRangeTo = in.readDouble();
        this.priceOfList = in.readInt();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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

    public Double getLevelRangeFrom() {
        return levelRangeFrom;
    }

    public void setLevelRangeFrom(Double priceLevelFrom) {
        this.levelRangeFrom = priceLevelFrom;
    }

    public Double getLevelRangeTo() {
        return levelRangeTo;
    }

    public void setLevelRangeTo(Double priceLevelTo) {
        this.levelRangeTo = priceLevelTo;
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
        parcel.writeInt(getId() == null ? 0 : getId());
        parcel.writeInt(getOrderId() == null ? 0 : getOrderId());
        parcel.writeParcelable(getProduct(), flags);
        parcel.writeDouble(getQuantity());
        parcel.writeDouble(getPrice());
        parcel.writeString(getTypePrice());
        parcel.writeInt(getLevel());
        parcel.writeDouble(getLevelRangeFrom());
        parcel.writeDouble(getLevelRangeTo());
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
