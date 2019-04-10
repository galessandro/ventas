package com.sandro.venta.api.service;

import com.sandro.venta.api.model.ControlResponse;

import java.util.List;

public interface ControlListServiceInterface {
    void displayProgressBar();
    void hideProgressBar();
    void displayControl(List<ControlResponse> controlResponseList);
    void displayError(String errorMessage);
}
