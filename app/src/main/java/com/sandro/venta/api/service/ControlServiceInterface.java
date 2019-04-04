package com.sandro.venta.api.service;

import com.sandro.venta.api.model.ControlResponse;
import com.sandro.venta.bean.Control;

import java.util.List;

public interface ControlServiceInterface {
    void displayProgressBar();
    void hideProgressBar();
    void displayControl(ControlResponse controlResponse);
    void displayError(String errorMessage);
}
