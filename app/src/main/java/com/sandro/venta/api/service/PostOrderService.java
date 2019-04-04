package com.sandro.venta.api.service;

import com.sandro.venta.api.model.CustomerResponse;
import com.sandro.venta.api.model.OrderPost;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PostOrderService {

    @POST("orders")
    Call<OrderPost> createOrder(@Body OrderPost order);
}
