package com.sandro.venta.api.service;

import com.sandro.venta.api.model.SellerResponse;

import java.util.List;

public interface LoginServiceInterface {
    void displayProgressBar();
    void hideProgressBar();
    void displaySeller(SellerResponse sellerResponse);
    void displayError(String errorMessage);
}
