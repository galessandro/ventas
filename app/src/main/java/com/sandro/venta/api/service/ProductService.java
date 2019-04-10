package com.sandro.venta.api.service;

import com.sandro.venta.api.model.ProductResponse;
import com.sandro.venta.api.model.SellerResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProductService {

    @GET("products")
    Observable<List<ProductResponse>> getProducts(@Query("controlId") Integer controlId);

    @GET("products/range")
    Observable<List<ProductResponse>> getProductsFromControlRange(
            @Query("controlIdFrom") Integer controlIdFrom,
            @Query("controlIdTo") Integer controlIdTo);
}
