package com.sandro.venta.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by t430 on 25/10/2015.
 */
public class Order {

    private int codSale;
    private int codOrder;
    private Date dateOrder;
    private Client client;
    private SalesMan seller;
    private Date dateDelivery;
    private List<Item> items;
    private Date dateReg;

    public Order (){
        items = new ArrayList<>();
    }

    public int getCodSale() {
        return codSale;
    }

    public void setCodSale(int codSale) {
        this.codSale = codSale;
    }

    public int getCodOrder() {
        return codOrder;
    }

    public void setCodOrder(int codOrder) {
        this.codOrder = codOrder;
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

    public Double getTotalAmount(){
        Double totalAmount = 0d;
        for (Item item : items) {
            totalAmount += item.getQuantity() * item.getPrice();
        }
        return totalAmount;
    }

}
