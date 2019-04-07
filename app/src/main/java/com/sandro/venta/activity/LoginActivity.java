package com.sandro.venta.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sandro.venta.R;
import com.sandro.venta.api.model.ControlResponse;
import com.sandro.venta.api.model.CustomerResponse;
import com.sandro.venta.api.model.ProductResponse;
import com.sandro.venta.api.model.SellerResponse;
import com.sandro.venta.api.service.ControlServiceInterface;
import com.sandro.venta.api.service.ControlServicePresenter;
import com.sandro.venta.api.service.CustomerServiceInterface;
import com.sandro.venta.api.service.CustomerServicePresenter;
import com.sandro.venta.api.service.ProductServiceInterface;
import com.sandro.venta.api.service.ProductServicePresenter;
import com.sandro.venta.api.service.SellerServiceInterface;
import com.sandro.venta.api.service.SellerServicePresenter;
import com.sandro.venta.bean.Client;
import com.sandro.venta.bean.Control;
import com.sandro.venta.bean.Product;
import com.sandro.venta.bean.SalesMan;
import com.sandro.venta.helper.DatabaseHelper;
import com.sandro.venta.util.SessionManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.txtLoginUser) EditText txtLoginUser;
    @BindView(R.id.txtLoginPass) EditText txtLoginPass;
    @BindView(R.id.btnLogin) Button btnLogin;
    DatabaseHelper db;
    private SessionManager session;
    private String TAG = "GGRANADOS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        configActivity();

        // Session Manager
        session = new SessionManager(getApplicationContext());
        db = new DatabaseHelper(getApplicationContext());

        int maxIdControlSeller = db.getMaxIdControlTable("T001");
        int maxIdControlCustomer = db.getMaxIdControlTable("T002");
        int maxIdControlProduct = db.getMaxIdControlTable("T003");

        /*ControlService2 controlService = new ControlService2();
        controlService.updateSellers("T001", maxIdControlSeller, db);*/

        /*SellerService sellerService = RetrofitClient.getRetrofitClient().create(SellerService.class);
        Call<List<SellerResponse>> call = sellerService.getSellers();
        call.enqueue(new Callback<List<SellerResponse>>() {
            @Override
            public void onResponse(Call<List<SellerResponse>> call, Response<List<SellerResponse>> response) {

            }

            @Override
            public void onFailure(Call<List<SellerResponse>> call, Throwable t) {

            }
        });*/

        ControlServicePresenter cSeller = new ControlServicePresenter(new ControlServiceInterface() {
            @Override
            public void displayProgressBar() {

            }

            @Override
            public void hideProgressBar() {

            }

            @Override
            public void displayControl(ControlResponse controlResponse) {
                Log.i(TAG, String.valueOf(controlResponse.getId()));

                if(maxIdControlSeller == 0) {
                    //db.createControl(Control.toControl(controlResponse));
                    getAllSellers(controlResponse);
                }else if(controlResponse.getId() > maxIdControlSeller){
                    //db.createControl(Control.toControl(controlResponse));
                    updateSelers(controlResponse);
                }
            }

            @Override
            public void displayError(String errorMessage) {
                Log.e(TAG, String.valueOf(errorMessage));
            }
        });

        cSeller.getMaxIdControl("T001");

        ControlServicePresenter cCustomer = new ControlServicePresenter(new ControlServiceInterface() {
            @Override
            public void displayProgressBar() {

            }

            @Override
            public void hideProgressBar() {

            }

            @Override
            public void displayControl(ControlResponse controlResponse) {
                Log.i(TAG, String.valueOf(controlResponse.getId()));

                if(maxIdControlCustomer == 0) {
//                    db.createControl(Control.toControl(controlResponse));
                    getAllCustomers(controlResponse);
                }else if(controlResponse.getId() > maxIdControlCustomer){
//                    db.createControl(Control.toControl(controlResponse));
                    updateCustomers(controlResponse);
                }
            }

            @Override
            public void displayError(String errorMessage) {
                Log.e(TAG, String.valueOf(errorMessage));
            }
        });

        cCustomer.getMaxIdControl("T002");

        ControlServicePresenter cProduct = new ControlServicePresenter(new ControlServiceInterface() {
            @Override
            public void displayProgressBar() {

            }

            @Override
            public void hideProgressBar() {

            }

            @Override
            public void displayControl(ControlResponse controlResponse) {
                Log.i(TAG, String.valueOf(controlResponse.getId()));

                if(maxIdControlProduct == 0) {
                    //db.createControl(Control.toControl(controlResponse));
                    getAllProducts(controlResponse);
                }else if(controlResponse.getId() > maxIdControlProduct){
                    //db.createControl(Control.toControl(controlResponse));
                    updateProducts(controlResponse);
                }
            }

            @Override
            public void displayError(String errorMessage) {
                Log.e(TAG, String.valueOf(errorMessage));
            }
        });
        cProduct.getMaxIdControl("T003");
    }

    private void updateProducts(ControlResponse controlResponse) {
        ProductServicePresenter c = new ProductServicePresenter(new ProductServiceInterface() {
            @Override
            public void displayProgressBar() {

            }

            @Override
            public void hideProgressBar() {

            }

            @Override
            public void displayProducts(List<ProductResponse> productResponseList) {
                Log.i(TAG, "displayProducts" + productResponseList.size());
                db.productBatchUpdate(Product.toProductList(productResponseList));
                db.createControl(Control.toControl(controlResponse));
            }

            @Override
            public void displayError(String errorMessage) {
                Log.e(TAG, String.valueOf(errorMessage));
            }
        });
        c.getProductsByControlId(controlResponse.getId());
    }

    private void updateCustomers(ControlResponse controlResponse) {
        CustomerServicePresenter c = new CustomerServicePresenter(new CustomerServiceInterface() {
            @Override
            public void displayProgressBar() {

            }

            @Override
            public void hideProgressBar() {

            }

            @Override
            public void displayCustomers(List<CustomerResponse> customerResponseList) {
                Log.i(TAG, "displayCustomers" + customerResponseList.size());
                db.clientBatchUpdate(Client.toClientList(customerResponseList));
                db.createControl(Control.toControl(controlResponse));
            }

            @Override
            public void displayError(String errorMessage) {
                Log.e(TAG, String.valueOf(errorMessage));
            }
        });
        c.getCustomersByControlId(controlResponse.getId());
    }

    private void getAllCustomers(ControlResponse controlResponse) {
        CustomerServicePresenter c = new CustomerServicePresenter(new CustomerServiceInterface() {
            @Override
            public void displayProgressBar() {

            }

            @Override
            public void hideProgressBar() {

            }

            @Override
            public void displayCustomers(List<CustomerResponse> customerResponse) {
                Log.i(TAG, String.valueOf(customerResponse.size()));
                db.createClientBatch(Client.toClientList(customerResponse));
                db.createControl(Control.toControl(controlResponse));
            }

            @Override
            public void displayError(String errorMessage) {
                Log.e(TAG, String.valueOf(errorMessage));
            }
        });
        c.getCustomers();
    }

    private void getAllProducts(ControlResponse controlResponse) {
        ProductServicePresenter c = new ProductServicePresenter(new ProductServiceInterface() {
            @Override
            public void displayProgressBar() {

            }

            @Override
            public void hideProgressBar() {

            }

            @Override
            public void displayProducts(List<ProductResponse> productResponseList) {
                Log.i(TAG, String.valueOf(productResponseList.size()));
                db.createProductBatch(Product.toProductList(productResponseList));
                db.createControl(Control.toControl(controlResponse));
            }

            @Override
            public void displayError(String errorMessage) {
                Log.e(TAG, String.valueOf(errorMessage));
            }
        });
        c.getProducts();
    }

    public void getAllSellers(ControlResponse controlResponse){
        SellerServicePresenter s = new SellerServicePresenter(new SellerServiceInterface() {
            @Override
            public void displayProgressBar() {

            }

            @Override
            public void hideProgressBar() {

            }

            @Override
            public void displaySellers(List<SellerResponse> sellerResponse) {
                Log.i(TAG, String.valueOf(sellerResponse.size()));
                db.createUserBatch(SalesMan.toSalesManList(sellerResponse));
                db.createControl(Control.toControl(controlResponse));
            }

            @Override
            public void displayError(String errorMessage) {
                Log.e(TAG, String.valueOf(errorMessage));
            }
        });
        s.getSellers();
    }

    public void updateSelers(ControlResponse controlResponse){
        SellerServicePresenter s = new SellerServicePresenter(new SellerServiceInterface() {
            @Override
            public void displayProgressBar() {

            }

            @Override
            public void hideProgressBar() {

            }

            @Override
            public void displaySellers(List<SellerResponse> sellerResponse) {
                Log.i(TAG, "displaySellers:" + sellerResponse.size());
                db.sellerBatchUpdate(SalesMan.toSalesManList(sellerResponse));
                db.createControl(Control.toControl(controlResponse));
            }

            @Override
            public void displayError(String errorMessage) {

            }
        });
        s.getSellersByControlId(controlResponse.getId());
    }

    @Override
    protected void onResume() {
        super.onResume();
        clearForm();
    }

    private void clearForm() {
        txtLoginUser.setText("");
        txtLoginPass.setText("");
        txtLoginUser.requestFocus();
    }

    private void configActivity() {
        InputFilter[] editFilters = txtLoginUser.getFilters();
        InputFilter[] newFilters = new InputFilter[editFilters.length + 1];
        System.arraycopy(editFilters, 0, newFilters, 0, editFilters.length);
        newFilters[editFilters.length] = new InputFilter.AllCaps();
        txtLoginUser.setFilters(newFilters);
    }

    @OnClick(R.id.btnLogin)
    public void logIn(){
        SalesMan user= db.validateUser(
                txtLoginUser.getText().toString(),
                txtLoginPass.getText().toString());

        if( user != null ){
            session.logoutUser();
            session.createLoginSession(user.getName(), user.getCodSeller());
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, R.string.login_user_error, Toast.LENGTH_SHORT).show();
        }
    }
}