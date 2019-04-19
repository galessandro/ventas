package com.sandro.venta.api.service;

import com.sandro.venta.api.RetrofitClient;
import com.sandro.venta.api.model.SellerResponse;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginServicePresenter {
    private static final String TAG = LoginServicePresenter.class.getSimpleName();

    private LoginServiceInterface loginServiceInterface;
    private Disposable disposable;

    public LoginServicePresenter(LoginServiceInterface loginServiceInterface) {
        this.loginServiceInterface = loginServiceInterface;
    }

    private Observable<SellerResponse> loginObservable(String imei){
        return RetrofitClient.getRetrofitClient().create(SellerService.class)
                .login(imei)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Observer<SellerResponse> loginObserver(){
        return new Observer<SellerResponse>() {

            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(SellerResponse sellerResponse) {
                loginServiceInterface.displaySeller(sellerResponse);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                loginServiceInterface.displayError("could not login");
            }

            @Override
            public void onComplete() {
                loginServiceInterface.hideProgressBar();
            }
        };
    }

    public void login(String imei){
        loginObservable(imei).subscribeWith(loginObserver());
    }


}
