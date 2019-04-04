package com.sandro.venta.api.service;

import com.sandro.venta.api.RetrofitClient;
import com.sandro.venta.api.model.ControlResponse;
import com.sandro.venta.bean.Control;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ControlServicePresenter {
    private static final String TAG = ControlServicePresenter.class.getSimpleName();

    private ControlServiceInterface controlServiceInterface;
    private Disposable disposable;

    public ControlServicePresenter(ControlServiceInterface controlServiceInterface) {
        this.controlServiceInterface = controlServiceInterface;
    }

    private Observable<ControlResponse> getMaxIdControlObservable(String tableCod){
        return RetrofitClient.getRetrofitClient().create(ControlService.class)
                .getControls(tableCod)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Observer<ControlResponse> getMaxIdControlObserver(){
        return new Observer<ControlResponse>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(ControlResponse value) {
                controlServiceInterface.displayControl(value);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                controlServiceInterface.displayError("Could not fetch controls");
            }

            @Override
            public void onComplete() {
                controlServiceInterface.hideProgressBar();
            }
        };
    }

    public void getMaxIdControl(String tableCod){
        getMaxIdControlObservable(tableCod).subscribeWith(getMaxIdControlObserver());
    }
}
