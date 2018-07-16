package com.sandro.venta.bean;

public class PriceLevel {

    private int level;
    private Double rangeFrom;
    private Double rangeTo;
    private Double priceFrom;
    private Double priceTo;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Double getRangeFrom() {
        return rangeFrom;
    }

    public void setRangeFrom(double rangeFrom) {
        this.rangeFrom = rangeFrom;
    }

    public Double getRangeTo() {
        return rangeTo;
    }

    public void setRangeTo(double rangeTo) {
        this.rangeTo = rangeTo;
    }

    public Double getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(double priceFrom) {
        this.priceFrom = priceFrom;
    }

    public Double getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(double priceTo) {
        this.priceTo = priceTo;
    }
}
