package com.sandro.venta.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;

/**
 * Creado por ggranados on 05/11/2015.
 */
public class Product implements Parcelable {

    private int id;
    private String codProduct;
    private String name;
    private Double priceOne;
    private Double priceTwo;
    private int boxBy;
    private String typeUnit;

    public Product(){

    }

    private Product(Parcel in) {
        super();
        this.id = in.readInt();
        this.codProduct = in.readString();
        this.name = in.readString();
        this.priceOne = in.readDouble();
        this.priceTwo = in.readDouble();
        this.boxBy = in.readInt();
        this.typeUnit = in.readString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodProduct() {
        return codProduct;
    }

    public void setCodProduct(String codProduct) {
        this.codProduct = codProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPriceOne() {
        return priceOne;
    }

    public void setPriceOne(Double priceOne) {
        this.priceOne = priceOne;
    }

    public Double getPriceTwo() {
        return priceTwo;
    }

    public void setPriceTwo(Double priceTwo) {
        this.priceTwo = priceTwo;
    }

    public int getBoxBy() {
        return boxBy;
    }

    public void setBoxBy(int boxBy) {
        this.boxBy = boxBy;
    }

    public String getTypeUnit() {
        return typeUnit;
    }

    public void setTypeUnit(String typeUnit) {
        this.typeUnit = typeUnit;
    }

    /**
     * Describe the kinds of special objects contained in this Parcelable's
     * marshalled representation.
     *
     * @return a bitmask indicating the set of special object types marshalled
     * by the Parcelable.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param parcel  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(getId());
        parcel.writeString(getCodProduct());
        parcel.writeString(getName());
        parcel.writeDouble(getPriceOne() == null ? 0d : getPriceOne());
        parcel.writeDouble(getPriceTwo() == null ? 0d : getPriceTwo());
        parcel.writeInt(getBoxBy());
        parcel.writeString(getTypeUnit());
    }

    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}
