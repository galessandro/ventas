
package com.sandro.venta.api.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderPost {

    @SerializedName("codsale")
    @Expose
    private String codsale;
    @SerializedName("codorder")
    @Expose
    private String codorder;
    @SerializedName("dateorder")
    @Expose
    private String dateorder;
    @SerializedName("customer_id")
    @Expose
    private Integer customerId;
    @SerializedName("seller_id")
    @Expose
    private Integer sellerId;
    @SerializedName("datedelivery")
    @Expose
    private String datedelivery;
    @SerializedName("paymenttype")
    @Expose
    private String paymenttype;
    @SerializedName("receiptType")
    @Expose
    private String receiptType;
    @SerializedName("imei")
    @Expose
    private String imei;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("semaphore")
    @Expose
    private String semaphore;
    @SerializedName("statusDownloaded")
    @Expose
    private String statusDownloaded;
    @SerializedName("orderInterna")
    @Expose
    private Long orderInterna;
    @SerializedName("itemPosts")
    @Expose
    private List<ItemPost> itemPosts = null;

    public String getCodsale() {
        return codsale;
    }

    public void setCodsale(String codsale) {
        this.codsale = codsale;
    }

    public String getCodorder() {
        return codorder;
    }

    public void setCodorder(String codorder) {
        this.codorder = codorder;
    }

    public String getDateorder() {
        return dateorder;
    }

    public void setDateorder(String dateorder) {
        this.dateorder = dateorder;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public String getDatedelivery() {
        return datedelivery;
    }

    public void setDatedelivery(String datedelivery) {
        this.datedelivery = datedelivery;
    }

    public String getPaymenttype() {
        return paymenttype;
    }

    public void setPaymenttype(String paymenttype) {
        this.paymenttype = paymenttype;
    }

    public String getReceiptType() {
        return receiptType;
    }

    public void setReceiptType(String receiptType) {
        this.receiptType = receiptType;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getSemaphore() {
        return semaphore;
    }

    public void setSemaphore(String semaphore) {
        this.semaphore = semaphore;
    }

    public String getStatusDownloaded() {
        return statusDownloaded;
    }

    public void setStatusDownloaded(String statusDownloaded) {
        this.statusDownloaded = statusDownloaded;
    }

    public Long getOrderInterna() {
        return orderInterna;
    }

    public void setOrderInterna(Long orderInterna) {
        this.orderInterna = orderInterna;
    }

    public List<ItemPost> getItemPosts() {
        return itemPosts;
    }

    public void setItemPosts(List<ItemPost> itemPosts) {
        this.itemPosts = itemPosts;
    }

}