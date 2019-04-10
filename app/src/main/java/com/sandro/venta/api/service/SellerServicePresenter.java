package com.sandro.venta.api.service;

import com.sandro.venta.api.RetrofitClient;
import com.sandro.venta.api.model.ControlResponse;
import com.sandro.venta.api.model.SellerResponse;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SellerServicePresenter {
    private static final String TAG = SellerServicePresenter.class.getSimpleName();

    private SellerServiceInterface sellerServiceInterface;
    private Disposable disposable;

    public SellerServicePresenter(SellerServiceInterface sellerServiceInterface) {
        this.sellerServiceInterface = sellerServiceInterface;
    }

    private Observable<List<SellerResponse>> getSellersObservable(){
        return RetrofitClient.getRetrofitClient().create(SellerService.class)
                .getSellers(null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Observable<List<SellerResponse>> getSellersByControlIdObservable(Integer controlId){
        return RetrofitClient.getRetrofitClient().create(SellerService.class)
                .getSellers(controlId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Observable<List<SellerResponse>> getSellersByControlRangeObservable(
            Integer controlIdFrom,
            Integer controlIdTo){
        return RetrofitClient.getRetrofitClient().create(SellerService.class)
                .getSellersFromControlRange(controlIdFrom, controlIdTo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Observer<List<SellerResponse>> getSellersObserver(){
        return new Observer<List<SellerResponse>>() {

            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(List<SellerResponse> value) {
                sellerServiceInterface.displaySellers(value);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                sellerServiceInterface.displayError("could not fetch sellers");
            }

            @Override
            public void onComplete() {
                sellerServiceInterface.hideProgressBar();
            }
        };
    }

    public void getSellers(){
        getSellersObservable().subscribeWith(getSellersObserver());
    }

    public void getSellersByControlId(Integer controlId){
        getSellersByControlIdObservable(controlId).subscribeWith(getSellersObserver());
    }

    public void getSellersByControlRange(Integer controlIdFrom, Integer controlIdTo){
        getSellersByControlRangeObservable(controlIdFrom, controlIdTo).subscribeWith(getSellersObserver());
    }
}
