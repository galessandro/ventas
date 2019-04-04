
package com.sandro.venta.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemPost {

    @SerializedName("codsale")
    @Expose
    private String codsale;
    @SerializedName("product_id")
    @Expose
    private Integer productId;
    @SerializedName("quantity")
    @Expose
    private Double quantity;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("typeunit")
    @Expose
    private String typeunit;
    @SerializedName("boxby")
    @Expose
    private Integer boxby;
    @SerializedName("typeprice")
    @Expose
    private String typeprice;
    @SerializedName("pricetlist")
    @Expose
    private Integer pricetlist;
    @SerializedName("codlevel")
    @Expose
    private Integer codlevel;
    @SerializedName("levelrangefrom")
    @Expose
    private Double levelrangefrom;
    @SerializedName("levelrangeto")
    @Expose
    private Double levelrangeto;

    public String getCodsale() {
        return codsale;
    }

    public void setCodsale(String codsale) {
        this.codsale = codsale;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTypeunit() {
        return typeunit;
    }

    public void setTypeunit(String typeunit) {
        this.typeunit = typeunit;
    }

    public Integer getBoxby() {
        return boxby;
    }

    public void setBoxby(Integer boxby) {
        this.boxby = boxby;
    }

    public String getTypeprice() {
        return typeprice;
    }

    public void setTypeprice(String typeprice) {
        this.typeprice = typeprice;
    }

    public Integer getPricetlist() {
        return pricetlist;
    }

    public void setPricetlist(Integer pricetlist) {
        this.pricetlist = pricetlist;
    }

    public Integer getCodlevel() {
        return codlevel;
    }

    public void setCodlevel(Integer codlevel) {
        this.codlevel = codlevel;
    }

    public Double getLevelrangefrom() {
        return levelrangefrom;
    }

    public void setLevelrangefrom(Double levelrangefrom) {
        this.levelrangefrom = levelrangefrom;
    }

    public Double getLevelrangeto() {
        return levelrangeto;
    }

    public void setLevelrangeto(Double levelrangeto) {
        this.levelrangeto = levelrangeto;
    }

}