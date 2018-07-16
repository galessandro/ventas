package com.sandro.venta.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Creado por ggranados on 05/11/2015.
 */
public class Product implements Parcelable {

    private int id;
    private String codProduct;
    private String name;
    private Double priceOne;
    private Double priceTwo;
    private Double priceThree;
    private List<PriceLevel> priceLevelList;
    private int boxBy;
    private String typeUnit;
    private int priceOfList;
    private String flagPrice;

    public static String PRODUCT_FLAG_LEVELS_ENABLE = "1";


    public Product() {

    }

    private Product(Parcel in) {
        super();
        this.priceLevelList = new ArrayList<>();
        this.id = in.readInt();
        this.codProduct = in.readString();
        this.name = in.readString();
        this.priceOne = in.readDouble();
        this.priceTwo = in.readDouble();
        this.priceThree = in.readDouble();

        PriceLevel priceLevel = new PriceLevel();
        priceLevel.setLevel(in.readInt());
        priceLevel.setRangeFrom(in.readDouble());
        priceLevel.setRangeTo(in.readDouble());
        priceLevel.setPriceFrom(in.readDouble());
        priceLevel.setPriceTo(in.readDouble());
        this.priceLevelList.add(priceLevel);

        priceLevel = new PriceLevel();
        priceLevel.setLevel(in.readInt());
        priceLevel.setRangeFrom(in.readDouble());
        priceLevel.setRangeTo(in.readDouble());
        priceLevel.setPriceFrom(in.readDouble());
        priceLevel.setPriceTo(in.readDouble());
        this.priceLevelList.add(priceLevel);

        priceLevel = new PriceLevel();
        priceLevel.setLevel(in.readInt());
        priceLevel.setRangeFrom(in.readDouble());
        priceLevel.setRangeTo(in.readDouble());
        priceLevel.setPriceFrom(in.readDouble());
        priceLevel.setPriceTo(in.readDouble());
        this.priceLevelList.add(priceLevel);

        priceLevel = new PriceLevel();
        priceLevel.setLevel(in.readInt());
        priceLevel.setRangeFrom(in.readDouble());
        priceLevel.setRangeTo(in.readDouble());
        priceLevel.setPriceFrom(in.readDouble());
        priceLevel.setPriceTo(in.readDouble());
        this.priceLevelList.add(priceLevel);

        priceLevel = new PriceLevel();
        priceLevel.setLevel(in.readInt());
        priceLevel.setRangeFrom(in.readDouble());
        priceLevel.setRangeTo(in.readDouble());
        priceLevel.setPriceFrom(in.readDouble());
        priceLevel.setPriceTo(in.readDouble());
        this.priceLevelList.add(priceLevel);
        this.boxBy = in.readInt();
        this.typeUnit = in.readString();
        this.priceOfList = in.readInt();
        this.flagPrice = in.readString();
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

    public Double getPriceThree() {
        return priceThree;
    }

    public void setPriceThree(Double priceThree) {
        this.priceThree = priceThree;
    }

    public List<PriceLevel> getPriceLevelList() {
        return priceLevelList;
    }

    public void setPriceLevelList(List<PriceLevel> priceLevelList) {
        this.priceLevelList = priceLevelList;
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

    public int getPriceOfList() {
        return priceOfList;
    }

    public void setPriceOfList(int priceOfList) {
        this.priceOfList = priceOfList;
    }

    public String getFlagPrice() {
        return flagPrice;
    }

    public void setFlagPrice(String flagPrice) {
        this.flagPrice = flagPrice;
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
     * @param parcel The Parcel in which the object should be written.
     * @param flags  Additional flags about how the object should be written.
     *               May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(getId());
        parcel.writeString(getCodProduct());
        parcel.writeString(getName());
        parcel.writeDouble(getPriceOne() == null ? 0d : getPriceOne());
        parcel.writeDouble(getPriceTwo() == null ? 0d : getPriceTwo());
        parcel.writeDouble(getPriceThree() == null ? 0d : getPriceThree());

        if(getPriceLevelList() != null) {
            parcel.writeInt(getPriceLevelList().get(0).getLevel());
            parcel.writeDouble(getPriceLevelList().get(0).getRangeFrom() == null ? 0d : getPriceLevelList().get(0).getRangeFrom());
            parcel.writeDouble(getPriceLevelList().get(0).getRangeTo() == null ? 0d : getPriceLevelList().get(0).getRangeTo());
            parcel.writeDouble(getPriceLevelList().get(0).getPriceFrom() == null ? 0d : getPriceLevelList().get(0).getPriceFrom());
            parcel.writeDouble(getPriceLevelList().get(0).getPriceTo() == null ? 0d : getPriceLevelList().get(0).getPriceTo());

            parcel.writeInt(getPriceLevelList().get(1).getLevel());
            parcel.writeDouble(getPriceLevelList().get(1).getRangeFrom() == null ? 0d : getPriceLevelList().get(1).getRangeFrom());
            parcel.writeDouble(getPriceLevelList().get(1).getRangeTo() == null ? 0d : getPriceLevelList().get(1).getRangeTo());
            parcel.writeDouble(getPriceLevelList().get(1).getPriceFrom() == null ? 0d : getPriceLevelList().get(1).getPriceFrom());
            parcel.writeDouble(getPriceLevelList().get(1).getPriceTo() == null ? 0d : getPriceLevelList().get(1).getPriceTo());

            parcel.writeInt(getPriceLevelList().get(2).getLevel());
            parcel.writeDouble(getPriceLevelList().get(2).getRangeFrom() == null ? 0d : getPriceLevelList().get(2).getRangeFrom());
            parcel.writeDouble(getPriceLevelList().get(2).getRangeTo() == null ? 0d : getPriceLevelList().get(2).getRangeTo());
            parcel.writeDouble(getPriceLevelList().get(2).getPriceFrom() == null ? 0d : getPriceLevelList().get(2).getPriceFrom());
            parcel.writeDouble(getPriceLevelList().get(2).getPriceTo() == null ? 0d : getPriceLevelList().get(2).getPriceTo());

            parcel.writeInt(getPriceLevelList().get(3).getLevel());
            parcel.writeDouble(getPriceLevelList().get(3).getRangeFrom() == null ? 0d : getPriceLevelList().get(3).getRangeFrom());
            parcel.writeDouble(getPriceLevelList().get(3).getRangeTo() == null ? 0d : getPriceLevelList().get(3).getRangeTo());
            parcel.writeDouble(getPriceLevelList().get(3).getPriceFrom() == null ? 0d : getPriceLevelList().get(3).getPriceFrom());
            parcel.writeDouble(getPriceLevelList().get(3).getPriceTo() == null ? 0d : getPriceLevelList().get(3).getPriceTo());

            parcel.writeInt(getPriceLevelList().get(4).getLevel());
            parcel.writeDouble(getPriceLevelList().get(4).getRangeFrom() == null ? 0d : getPriceLevelList().get(4).getRangeFrom());
            parcel.writeDouble(getPriceLevelList().get(4).getRangeTo() == null ? 0d : getPriceLevelList().get(4).getRangeTo());
            parcel.writeDouble(getPriceLevelList().get(4).getPriceFrom() == null ? 0d : getPriceLevelList().get(4).getPriceFrom());
            parcel.writeDouble(getPriceLevelList().get(4).getPriceTo() == null ? 0d : getPriceLevelList().get(4).getPriceTo());
        } else {
            parcel.writeInt(0);
            parcel.writeDouble(0d);
            parcel.writeDouble(0d);
            parcel.writeDouble(0d);
            parcel.writeDouble(0d);

            parcel.writeInt(0);
            parcel.writeDouble(0d);
            parcel.writeDouble(0d);
            parcel.writeDouble(0d);
            parcel.writeDouble(0d);

            parcel.writeInt(0);
            parcel.writeDouble(0d);
            parcel.writeDouble(0d);
            parcel.writeDouble(0d);
            parcel.writeDouble(0d);

            parcel.writeInt(0);
            parcel.writeDouble(0d);
            parcel.writeDouble(0d);
            parcel.writeDouble(0d);
            parcel.writeDouble(0d);

            parcel.writeInt(0);
            parcel.writeDouble(0d);
            parcel.writeDouble(0d);
            parcel.writeDouble(0d);
            parcel.writeDouble(0d);

        }

        parcel.writeInt(getBoxBy());
        parcel.writeString(getTypeUnit());
        parcel.writeInt(getPriceOfList());
        parcel.writeString(getFlagPrice());
    }

    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public int getLevelByQuantity(Double quantity) {
        int priceLevelId = 1;
        for (PriceLevel level : getPriceLevelList()) {
            if (quantity >= level.getRangeFrom() && quantity <= level.getRangeTo()) {
                return level.getLevel();
            }
        }
        return priceLevelId;
    }

    public Double getLowLevelById(int levelId) {
        Double lowLevel = 0d;
        for (PriceLevel level : getPriceLevelList()) {
            if (level.getLevel() == levelId) {
                return level.getRangeFrom();
            }
        }
        return lowLevel;
    }

    public Double getHighLevelById(int levelId) {
        Double highLevel = 0d;
        for (PriceLevel level : getPriceLevelList()) {
            if (level.getLevel() == levelId) {
                return level.getRangeTo();
            }
        }
        return highLevel;
    }

    public Double getPriceHighByLevel(int levelId) {
        Double priceByLevel = 0d;
        for (PriceLevel level : getPriceLevelList()) {
            if (level.getLevel() == levelId) {
                return level.getPriceTo();
            }
        }
        return priceByLevel;
    }


    public Double getPriceByQuantity(Double quantity) {
        return getPriceHighByLevel(getLevelByQuantity(quantity));
    }
}