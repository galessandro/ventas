package com.sandro.venta.api.service;

import com.sandro.venta.api.model.SellerResponse;

import java.util.List;

public interface SellerServiceInterface {
    void displayProgressBar();
    void hideProgressBar();
    void displaySellers(List<SellerResponse> sellerResponse);
    void displayError(String errorMessage);
}
