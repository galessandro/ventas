package com.sandro.venta.api.service;

import android.util.Log;

import com.sandro.venta.api.RetrofitClient;
import com.sandro.venta.api.model.ControlResponse;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ControlListServicePresenter {
    private static final String TAG = ControlListServicePresenter.class.getSimpleName();

    private ControlListServiceInterface controlServiceInterface;
    private Disposable disposable;

    public ControlListServicePresenter(ControlListServiceInterface controlServiceInterface) {
        this.controlServiceInterface = controlServiceInterface;
    }

    private Observable<List<ControlResponse>> getAllControlsObservable(){
        return RetrofitClient.getRetrofitClient().create(ControlService.class)
                .getAllControls()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Observer<List<ControlResponse>> getAllControlsObserver(){
        return new Observer<List<ControlResponse>>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(List<ControlResponse> controlResponseList) {
                controlServiceInterface.displayControl(controlResponseList);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                controlServiceInterface.displayError("could not fetch all controls");
            }

            @Override
            public void onComplete() {
                Log.i("GGRANADOS","completed getAllControls");
                controlServiceInterface.hideProgressBar();
            }
        };
    }


    public void getAllControls(){
        getAllControlsObservable().subscribeWith(getAllControlsObserver());
    }
}
