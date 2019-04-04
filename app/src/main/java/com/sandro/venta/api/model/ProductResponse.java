
package com.sandro.venta.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductResponse {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("codproduct")
    @Expose
    private String codproduct;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("priceone")
    @Expose
    private Double priceone;
    @SerializedName("pricetwo")
    @Expose
    private Double pricetwo;
    @SerializedName("pricethree")
    @Expose
    private Double pricethree;
    @SerializedName("pricerangenameone")
    @Expose
    private Integer pricerangenameone;
    @SerializedName("pricerangefromone")
    @Expose
    private Double pricerangefromone;
    @SerializedName("pricerangetoone")
    @Expose
    private Double pricerangetoone;
    @SerializedName("pricevaluefromone")
    @Expose
    private Double pricevaluefromone;
    @SerializedName("pricevaluetoone")
    @Expose
    private Double pricevaluetoone;
    @SerializedName("pricerangenametwo")
    @Expose
    private Integer pricerangenametwo;
    @SerializedName("pricerangefromtwo")
    @Expose
    private Double pricerangefromtwo;
    @SerializedName("pricerangetotwo")
    @Expose
    private Double pricerangetotwo;
    @SerializedName("pricevaluefromtwo")
    @Expose
    private Double pricevaluefromtwo;
    @SerializedName("pricevaluetotwo")
    @Expose
    private Double pricevaluetotwo;
    @SerializedName("pricerangenamethree")
    @Expose
    private Integer pricerangenamethree;
    @SerializedName("pricerangefromthree")
    @Expose
    private Double pricerangefromthree;
    @SerializedName("pricerangetothree")
    @Expose
    private Double pricerangetothree;
    @SerializedName("pricevaluefromthree")
    @Expose
    private Double pricevaluefromthree;
    @SerializedName("pricevaluetothree")
    @Expose
    private Double pricevaluetothree;
    @SerializedName("pricerangenamefour")
    @Expose
    private Integer pricerangenamefour;
    @SerializedName("pricerangefromfour")
    @Expose
    private Double pricerangefromfour;
    @SerializedName("pricerangetofour")
    @Expose
    private Double pricerangetofour;
    @SerializedName("pricevaluefromfour")
    @Expose
    private Double pricevaluefromfour;
    @SerializedName("pricevaluetofour")
    @Expose
    private Double pricevaluetofour;
    @SerializedName("pricerangenamefive")
    @Expose
    private Integer pricerangenamefive;
    @SerializedName("pricerangefromfive")
    @Expose
    private Double pricerangefromfive;
    @SerializedName("pricerangetofive")
    @Expose
    private Double pricerangetofive;
    @SerializedName("pricevaluefromfive")
    @Expose
    private Double pricevaluefromfive;
    @SerializedName("pricevaluetofive")
    @Expose
    private Double pricevaluetofive;
    @SerializedName("boxby")
    @Expose
    private Integer boxby;
    @SerializedName("typeunit")
    @Expose
    private String typeunit;
    @SerializedName("priceoflist")
    @Expose
    private Integer priceoflist;
    @SerializedName("flagprice")
    @Expose
    private String flagprice;
    @SerializedName("fecIniVig")
    @Expose
    private String fecIniVig;
    @SerializedName("fecFinVig")
    @Expose
    private String fecFinVig;
    @SerializedName("fecUv")
    @Expose
    private String fecUv;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodproduct() {
        return codproduct;
    }

    public void setCodproduct(String codproduct) {
        this.codproduct = codproduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPriceone() {
        return priceone;
    }

    public void setPriceone(Double priceone) {
        this.priceone = priceone;
    }

    public Double getPricetwo() {
        return pricetwo;
    }

    public void setPricetwo(Double pricetwo) {
        this.pricetwo = pricetwo;
    }

    public Double getPricethree() {
        return pricethree;
    }

    public void setPricethree(Double pricethree) {
        this.pricethree = pricethree;
    }

    public Integer getPricerangenameone() {
        return pricerangenameone;
    }

    public void setPricerangenameone(Integer pricerangenameone) {
        this.pricerangenameone = pricerangenameone;
    }

    public Double getPricerangefromone() {
        return pricerangefromone;
    }

    public void setPricerangefromone(Double pricerangefromone) {
        this.pricerangefromone = pricerangefromone;
    }

    public Double getPricerangetoone() {
        return pricerangetoone;
    }

    public void setPricerangetoone(Double pricerangetoone) {
        this.pricerangetoone = pricerangetoone;
    }

    public Double getPricevaluefromone() {
        return pricevaluefromone;
    }

    public void setPricevaluefromone(Double pricevaluefromone) {
        this.pricevaluefromone = pricevaluefromone;
    }

    public Double getPricevaluetoone() {
        return pricevaluetoone;
    }

    public void setPricevaluetoone(Double pricevaluetoone) {
        this.pricevaluetoone = pricevaluetoone;
    }

    public Integer getPricerangenametwo() {
        return pricerangenametwo;
    }

    public void setPricerangenametwo(Integer pricerangenametwo) {
        this.pricerangenametwo = pricerangenametwo;
    }

    public Double getPricerangefromtwo() {
        return pricerangefromtwo;
    }

    public void setPricerangefromtwo(Double pricerangefromtwo) {
        this.pricerangefromtwo = pricerangefromtwo;
    }

    public Double getPricerangetotwo() {
        return pricerangetotwo;
    }

    public void setPricerangetotwo(Double pricerangetotwo) {
        this.pricerangetotwo = pricerangetotwo;
    }

    public Double getPricevaluefromtwo() {
        return pricevaluefromtwo;
    }

    public void setPricevaluefromtwo(Double pricevaluefromtwo) {
        this.pricevaluefromtwo = pricevaluefromtwo;
    }

    public Double getPricevaluetotwo() {
        return pricevaluetotwo;
    }

    public void setPricevaluetotwo(Double pricevaluetotwo) {
        this.pricevaluetotwo = pricevaluetotwo;
    }

    public Integer getPricerangenamethree() {
        return pricerangenamethree;
    }

    public void setPricerangenamethree(Integer pricerangenamethree) {
        this.pricerangenamethree = pricerangenamethree;
    }

    public Double getPricerangefromthree() {
        return pricerangefromthree;
    }

    public void setPricerangefromthree(Double pricerangefromthree) {
        this.pricerangefromthree = pricerangefromthree;
    }

    public Double getPricerangetothree() {
        return pricerangetothree;
    }

    public void setPricerangetothree(Double pricerangetothree) {
        this.pricerangetothree = pricerangetothree;
    }

    public Double getPricevaluefromthree() {
        return pricevaluefromthree;
    }

    public void setPricevaluefromthree(Double pricevaluefromthree) {
        this.pricevaluefromthree = pricevaluefromthree;
    }

    public Double getPricevaluetothree() {
        return pricevaluetothree;
    }

    public void setPricevaluetothree(Double pricevaluetothree) {
        this.pricevaluetothree = pricevaluetothree;
    }

    public Integer getPricerangenamefour() {
        return pricerangenamefour;
    }

    public void setPricerangenamefour(Integer pricerangenamefour) {
        this.pricerangenamefour = pricerangenamefour;
    }

    public Double getPricerangefromfour() {
        return pricerangefromfour;
    }

    public void setPricerangefromfour(Double pricerangefromfour) {
        this.pricerangefromfour = pricerangefromfour;
    }

    public Double getPricerangetofour() {
        return pricerangetofour;
    }

    public void setPricerangetofour(Double pricerangetofour) {
        this.pricerangetofour = pricerangetofour;
    }

    public Double getPricevaluefromfour() {
        return pricevaluefromfour;
    }

    public void setPricevaluefromfour(Double pricevaluefromfour) {
        this.pricevaluefromfour = pricevaluefromfour;
    }

    public Double getPricevaluetofour() {
        return pricevaluetofour;
    }

    public void setPricevaluetofour(Double pricevaluetofour) {
        this.pricevaluetofour = pricevaluetofour;
    }

    public Integer getPricerangenamefive() {
        return pricerangenamefive;
    }

    public void setPricerangenamefive(Integer pricerangenamefive) {
        this.pricerangenamefive = pricerangenamefive;
    }

    public Double getPricerangefromfive() {
        return pricerangefromfive;
    }

    public void setPricerangefromfive(Double pricerangefromfive) {
        this.pricerangefromfive = pricerangefromfive;
    }

    public Double getPricerangetofive() {
        return pricerangetofive;
    }

    public void setPricerangetofive(Double pricerangetofive) {
        this.pricerangetofive = pricerangetofive;
    }

    public Double getPricevaluefromfive() {
        return pricevaluefromfive;
    }

    public void setPricevaluefromfive(Double pricevaluefromfive) {
        this.pricevaluefromfive = pricevaluefromfive;
    }

    public Double getPricevaluetofive() {
        return pricevaluetofive;
    }

    public void setPricevaluetofive(Double pricevaluetofive) {
        this.pricevaluetofive = pricevaluetofive;
    }

    public Integer getBoxby() {
        return boxby;
    }

    public void setBoxby(Integer boxby) {
        this.boxby = boxby;
    }

    public String getTypeunit() {
        return typeunit;
    }

    public void setTypeunit(String typeunit) {
        this.typeunit = typeunit;
    }

    public Integer getPriceoflist() {
        return priceoflist;
    }

    public void setPriceoflist(Integer priceoflist) {
        this.priceoflist = priceoflist;
    }

    public String getFlagprice() {
        return flagprice;
    }

    public void setFlagprice(String flagprice) {
        this.flagprice = flagprice;
    }

    public String getFecIniVig() {
        return fecIniVig;
    }

    public void setFecIniVig(String fecIniVig) {
        this.fecIniVig = fecIniVig;
    }

    public String getFecFinVig() {
        return fecFinVig;
    }

    public void setFecFinVig(String fecFinVig) {
        this.fecFinVig = fecFinVig;
    }

    public String getFecUv() {
        return fecUv;
    }

    public void setFecUv(String fecUv) {
        this.fecUv = fecUv;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "ProductResponse{" +
                "id=" + id +
                ", codproduct='" + codproduct + '\'' +
                ", name='" + name + '\'' +
                ", priceone=" + priceone +
                ", pricetwo=" + pricetwo +
                ", pricethree=" + pricethree +
                ", pricerangenameone=" + pricerangenameone +
                ", pricerangefromone=" + pricerangefromone +
                ", pricerangetoone=" + pricerangetoone +
                ", pricevaluefromone=" + pricevaluefromone +
                ", pricevaluetoone=" + pricevaluetoone +
                ", pricerangenametwo=" + pricerangenametwo +
                ", pricerangefromtwo=" + pricerangefromtwo +
                ", pricerangetotwo=" + pricerangetotwo +
                ", pricevaluefromtwo=" + pricevaluefromtwo +
                ", pricevaluetotwo=" + pricevaluetotwo +
                ", pricerangenamethree=" + pricerangenamethree +
                ", pricerangefromthree=" + pricerangefromthree +
                ", pricerangetothree=" + pricerangetothree +
                ", pricevaluefromthree=" + pricevaluefromthree +
                ", pricevaluetothree=" + pricevaluetothree +
                ", pricerangenamefour=" + pricerangenamefour +
                ", pricerangefromfour=" + pricerangefromfour +
                ", pricerangetofour=" + pricerangetofour +
                ", pricevaluefromfour=" + pricevaluefromfour +
                ", pricevaluetofour=" + pricevaluetofour +
                ", pricerangenamefive=" + pricerangenamefive +
                ", pricerangefromfive=" + pricerangefromfive +
                ", pricerangetofive=" + pricerangetofive +
                ", pricevaluefromfive=" + pricevaluefromfive +
                ", pricevaluetofive=" + pricevaluetofive +
                ", boxby=" + boxby +
                ", typeunit='" + typeunit + '\'' +
                ", priceoflist=" + priceoflist +
                ", flagprice='" + flagprice + '\'' +
                ", fecIniVig='" + fecIniVig + '\'' +
                ", fecFinVig='" + fecFinVig + '\'' +
                ", fecUv='" + fecUv + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}