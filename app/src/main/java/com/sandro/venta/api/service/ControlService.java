package com.sandro.venta.api.service;

import com.sandro.venta.api.model.ControlResponse;
import com.sandro.venta.api.model.CustomerResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ControlService {

    @GET("controls")
    Observable<ControlResponse> getControls(@Query("tabla") String tabla);

    @GET("controls/all")
    Observable<List<ControlResponse>> getAllControls();
}
