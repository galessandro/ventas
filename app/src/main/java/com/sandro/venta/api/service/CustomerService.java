package com.sandro.venta.api.service;

import com.sandro.venta.api.model.CustomerResponse;
import com.sandro.venta.api.model.SellerResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CustomerService {

    @GET("customers")
    Observable<List<CustomerResponse>> getCustomers(@Query("controlId") Integer controlId);
}
