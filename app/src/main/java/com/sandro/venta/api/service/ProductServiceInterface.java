package com.sandro.venta.api.service;

import com.sandro.venta.api.model.CustomerResponse;
import com.sandro.venta.api.model.ProductResponse;

import java.util.List;

public interface ProductServiceInterface {
    void displayProgressBar();
    void hideProgressBar();
    void displayProducts(List<ProductResponse> productResponseList);
    void displayError(String errorMessage);
}
