package com.sandro.venta.api.service;

import com.sandro.venta.api.model.SellerResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SellerService {

    @GET("sellers")
    Observable<List<SellerResponse>> getSellers(@Query("controlId") Integer controlId);

    @GET("sellers/range")
    Observable<List<SellerResponse>> getSellersFromControlRange(
            @Query("controlIdFrom") Integer controlIdFrom,
            @Query("controlIdTo") Integer controlIdTo);

}
