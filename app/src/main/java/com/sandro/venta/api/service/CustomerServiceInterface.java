package com.sandro.venta.api.service;

import com.sandro.venta.api.model.CustomerResponse;
import com.sandro.venta.api.model.SellerResponse;

import java.util.List;

public interface CustomerServiceInterface {
    void displayProgressBar();
    void hideProgressBar();
    void displayCustomers(List<CustomerResponse> customerResponse);
    void displayError(String errorMessage);
}
