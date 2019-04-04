package com.sandro.venta.bean;

import com.sandro.venta.api.model.ControlResponse;

public class Control {

    private int id;
    private String table;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public static Control toControl(ControlResponse controlResponse){
        Control control = new Control();
        control.setId(controlResponse.getId());
        control.setTable(controlResponse.getTable());
        return control;
    }
}
