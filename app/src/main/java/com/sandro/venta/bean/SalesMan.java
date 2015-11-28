package com.sandro.venta.bean;

/**
 * Created by t430 on 25/10/2015.
 */
public class SalesMan {

    private int id;
    private String codSeller;
    private String name;
    private String user;
    private String pass;

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodSeller() {
        return codSeller;
    }

    public void setCodSeller(String codSeller) {
        this.codSeller = codSeller;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
