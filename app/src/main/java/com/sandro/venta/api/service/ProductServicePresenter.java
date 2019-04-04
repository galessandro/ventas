package com.sandro.venta.api.service;

import com.sandro.venta.api.RetrofitClient;
import com.sandro.venta.api.model.CustomerResponse;
import com.sandro.venta.api.model.ProductResponse;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProductServicePresenter {
    private static final String TAG = ProductServicePresenter.class.getSimpleName();

    private ProductServiceInterface productServiceInterface;
    private Disposable disposable;

    public ProductServicePresenter(ProductServiceInterface productServiceInterface) {
        this.productServiceInterface = productServiceInterface;
    }

    private Observable<List<ProductResponse>> getProductsObservable(){
        return RetrofitClient.getRetrofitClient().create(ProductService.class)
                .getProducts(null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Observable<List<ProductResponse>> getProductsByControlIdObservable(Integer controlId){
        return RetrofitClient.getRetrofitClient().create(ProductService.class)
                .getProducts(controlId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Observer<List<ProductResponse>> getProductsObserver(){
        return new Observer<List<ProductResponse>>(){

            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(List<ProductResponse> value) {
                productServiceInterface.displayProducts(value);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                productServiceInterface.displayError("could not fetch products");
            }

            @Override
            public void onComplete() {
                productServiceInterface.hideProgressBar();
            }
        };
    }

    public void getProducts(){
        getProductsObservable().subscribeWith(getProductsObserver());
    }

    public void getProductsByControlId(Integer controlId){
        getProductsByControlIdObservable(controlId).subscribeWith(getProductsObserver());
    }
}
