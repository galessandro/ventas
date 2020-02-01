package com.sandro.venta.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.sandro.venta.api.model.ItemPost;
import com.sandro.venta.api.model.OrderPost;
import com.sandro.venta.util.DateUtil;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.http.POST;

/**
 * Creado por ggranados on 25/10/2015.
 */
public class Order implements Parcelable{

    private int id;
    private int paymentType;
    private int paymentVoucherType;
    private int flagCloud;
    private Date dateOrder;
    private Date dateDelivery;
    private Date dateReg;
    private Client client;
    private SalesMan seller;
    private String imei;
    private String semaphore;
    private double latitude;
    private double longitude;
    private long orderInterna;
    private List<Item> items;

    public Order (){
        items = new ArrayList<>();
    }

    private Order(Parcel in) {
        super();
        this.id = in.readInt();
        this.dateOrder = new Date(in.readLong());
        this.client = in.readParcelable(Client.class.getClassLoader());
        this.seller = in.readParcelable(SalesMan.class.getClassLoader());
        this.dateDelivery = new Date(in.readLong());
        items = new ArrayList<>();
        in.readTypedList(items, Item.CREATOR);
        this.paymentType = in.readInt();
        this.paymentVoucherType = in.readInt();
        this.flagCloud = in.readInt();
        this.orderInterna = in.readLong();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getSemaphore() {
        return semaphore;
    }

    public void setSemaphore(String semaphore) {
        this.semaphore = semaphore;
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

    public int getPaymentVoucherType() {
        return paymentVoucherType;
    }

    public void setPaymentVoucherType(int paymentVoucherType) {
        this.paymentVoucherType = paymentVoucherType;
    }

    public int getFlagCloud() {
        return flagCloud;
    }

    public void setFlagCloud(int flagCloud) {
        this.flagCloud = flagCloud;
    }

    public long getOrderInterna() {
        return orderInterna;
    }

    public void setOrderInterna(long orderInterna) {
        this.orderInterna = orderInterna;
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
        parcel.writeInt(getId());
        parcel.writeLong(getDateOrder().getTime());
        parcel.writeParcelable(getClient(), flags);
        parcel.writeParcelable(getSeller(), flags);
        parcel.writeLong(getDateDelivery().getTime());
        parcel.writeTypedList(getItems());
        parcel.writeInt(getPaymentType());
        parcel.writeInt(getPaymentVoucherType());
        parcel.writeInt(getFlagCloud());
        parcel.writeLong(getOrderInterna());
    }

    public static final Parcelable.Creator<Order> CREATOR = new Parcelable.Creator<Order>() {
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    public OrderPost toPostOrder(){
        OrderPost post = new OrderPost();
        post.setAppId(this.getId());
        post.setDateorder(DateUtil.getFormatDate(this.getDateOrder(), DateUtil.dateSimpleFormat));
        post.setCustomerId(this.getClient().getId());
        post.setSellerId(this.getSeller().getId());
        post.setDatedelivery(DateUtil.getFormatDate(this.getDateDelivery(), DateUtil.dateSimpleFormat));
        post.setPaymenttype(String.valueOf(this.getPaymentType()));
        post.setReceiptType(String.valueOf(this.getPaymentVoucherType()));
        post.setImei(this.getImei());
        post.setLatitude(this.getLatitude());
        post.setLongitude(this.getLongitude());
        post.setStatusDownloaded("1");
        post.setSemaphore(this.getSemaphore());
        post.setOrderInterna(this.getOrderInterna());

        List<ItemPost> itemPostList = new ArrayList<>();

        for (Item item: this.getItems()) {
            ItemPost itemPost = new ItemPost();
            itemPost.setAppId(this.getId());
            itemPost.setProductId(item.getProduct().getId());
            itemPost.setTypeunit(item.getProduct().getTypeUnit());
            itemPost.setQuantity(item.getQuantity());
            itemPost.setPrice(item.getPrice());
            itemPost.setTypeprice(item.getTypePrice());
            itemPost.setBoxby(item.getProduct().getBoxBy());
            itemPost.setPricetlist(item.getPriceOfList());
            itemPost.setCodlevel(item.getLevel());
            itemPost.setLevelrangefrom(item.getLevelRangeFrom());
            itemPost.setLevelrangeto(item.getLevelRangeTo());
            itemPostList.add(itemPost);
        }
        post.setItemPosts(itemPostList);
        return post;
    }
}
