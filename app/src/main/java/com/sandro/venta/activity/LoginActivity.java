package com.sandro.venta.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
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

    private DatabaseHelper db;
    private SessionManager session;

    private final String TAG = LoginActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        configActivity();


        int permissionAll = 1;

        String[] permissions = {
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};
        if(!hasPermissions(this, permissions)){
            ActivityCompat.requestPermissions(this, permissions, permissionAll);
        }

        // Session Manager
        session = new SessionManager(getApplicationContext());
        db = new DatabaseHelper(getApplicationContext());

        int maxIdControlSeller = db.getMaxIdControlTable("T001");
        int maxIdControlCustomer = db.getMaxIdControlTable("T002");
        int maxIdControlProduct = db.getMaxIdControlTable("T003");

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
                    getAllSellers(controlResponse);
                }else if(controlResponse.getId() > maxIdControlSeller){
                    updateSellers(controlResponse);
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
                    getAllCustomers(controlResponse);
                }else if(controlResponse.getId() > maxIdControlCustomer){
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

    private void getAllSellers(ControlResponse controlResponse){
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

    private void updateSellers(ControlResponse controlResponse){
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
            session.createLoginSession(user.getName(), user.getCodSeller(), user.getId());
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, R.string.login_user_error, Toast.LENGTH_SHORT).show();
        }
    }

    private static boolean hasPermissions(Context context, String... permissions){

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M && context!=null && permissions!=null){
            for(String permission: permissions){
                if(ActivityCompat.checkSelfPermission(context, permission)!= PackageManager.PERMISSION_GRANTED){
                    return  false;
                }
            }
        }
        return true;
    }
}