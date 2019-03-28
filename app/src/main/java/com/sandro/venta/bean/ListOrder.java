package com.sandro.venta.bean;

public class ListOrder {

    private String orderCod;
    private String orderClient;
    private String orderTotalAmount;
    private String orderDeliveryDate;

    public ListOrder(String orderCod, String orderClient, String orderTotalAmount, String orderDeliveryDate) {
        this.orderCod = orderCod;
        this.orderClient = orderClient;
        this.orderTotalAmount = orderTotalAmount;
        this.orderDeliveryDate = orderDeliveryDate;
    }

    public String getOrderCod() {
        return orderCod;
    }

    public void setOrderCod(String orderCod) {
        this.orderCod = orderCod;
    }

    public String getOrderClient() {
        return orderClient;
    }

    public void setOrderClient(String orderClient) {
        this.orderClient = orderClient;
    }

    public String getOrderTotalAmount() {
        return orderTotalAmount;
    }

    public void setOrderTotalAmount(String orderTotalAmount) {
        this.orderTotalAmount = orderTotalAmount;
    }

    public String getOrderDeliveryDate() {
        return orderDeliveryDate;
    }

    public void setOrderDeliveryDate(String orderDeliveryDate) {
        this.orderDeliveryDate = orderDeliveryDate;
    }
}
