
package com.sandro.venta.api.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderPost {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("app_id")
    @Expose
    private Integer appId;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
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

    @Override
    public String toString() {
        return "OrderPost{" +
                "appId=" + appId +
                ", dateorder='" + dateorder + '\'' +
                ", customerId=" + customerId +
                ", sellerId=" + sellerId +
                ", datedelivery='" + datedelivery + '\'' +
                ", paymenttype='" + paymenttype + '\'' +
                ", receiptType='" + receiptType + '\'' +
                ", imei='" + imei + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", semaphore='" + semaphore + '\'' +
                ", statusDownloaded='" + statusDownloaded + '\'' +
                ", orderInterna=" + orderInterna +
                ", itemPosts=" + itemPosts +
                '}';
    }
}