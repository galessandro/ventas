package com.sandro.venta.bean;

import java.util.Date;

/**
 * Creado por chambo on 25/10/2015.
 */
public class Item {

    private String codSale;
    private Product product;
    private Integer quantity;
    private Double price;
    private String typePrice;
    private Date dateReg;

    public String getCodSale() {
        return codSale;
    }

    public void setCodSale(String codSale) {
        this.codSale = codSale;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getTypePrice() {
        return typePrice;
    }

    public void setTypePrice(String typePrice) {
        this.typePrice = typePrice;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDateReg() {
        return dateReg;
    }

    public void setDateReg(Date dateReg) {
        this.dateReg = dateReg;
    }

}
