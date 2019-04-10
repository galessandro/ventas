package com.sandro.venta.api.service;

import com.sandro.venta.api.RetrofitClient;
import com.sandro.venta.api.model.CustomerResponse;
import com.sandro.venta.api.model.SellerResponse;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CustomerServicePresenter {
    private static final String TAG = CustomerServicePresenter.class.getSimpleName();

    private CustomerServiceInterface customerServiceInterface;
    private Disposable disposable;

    public CustomerServicePresenter(CustomerServiceInterface customerServiceInterface) {
        this.customerServiceInterface = customerServiceInterface;
    }

    private Observable<List<CustomerResponse>> getCustomersObservable(){
        return RetrofitClient.getRetrofitClient().create(CustomerService.class)
                .getCustomers(null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Observable<List<CustomerResponse>> getCustomersByControlIdObservable(Integer controlId){
        return RetrofitClient.getRetrofitClient().create(CustomerService.class)
                .getCustomers(controlId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Observable<List<CustomerResponse>> getCustomersByControlRangeObservable(
            Integer controlIdFrom,
            Integer controlIdTo){
        return RetrofitClient.getRetrofitClient().create(CustomerService.class)
                .getCustomersFromControlRange(controlIdFrom,controlIdTo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Observer<List<CustomerResponse>> getCustomersObserver(){
        return new Observer<List<CustomerResponse>>(){

            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(List<CustomerResponse> value) {
                customerServiceInterface.displayCustomers(value);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                customerServiceInterface.displayError("could not fetch customers");
            }

            @Override
            public void onComplete() {
                customerServiceInterface.hideProgressBar();
            }
        };
    }

    public void getCustomers(){
        getCustomersObservable().subscribeWith(getCustomersObserver());
    }

    public void getCustomersByControlId(Integer controlId){
        getCustomersByControlIdObservable(controlId).subscribeWith(getCustomersObserver());
    }

    public void getCustomersByControlRange(Integer controlIdFrom, Integer controlIdTo){
        getCustomersByControlRangeObservable(controlIdFrom, controlIdTo).subscribeWith(getCustomersObserver());
    }
}
