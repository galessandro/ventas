package com.sandro.venta.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.text.InputFilter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sandro.venta.R;
import com.sandro.venta.api.model.SellerResponse;
import com.sandro.venta.api.service.LoginServiceInterface;
import com.sandro.venta.api.service.LoginServicePresenter;
import com.sandro.venta.bean.SalesMan;
import com.sandro.venta.helper.DatabaseHelper;
import com.sandro.venta.util.SessionManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.txtLoginUser)
    EditText txtLoginUser;
    @BindView(R.id.txtLoginPass)
    EditText txtLoginPass;
    @BindView(R.id.btnLogin)
    Button btnLogin;

    private DatabaseHelper db;
    private SessionManager session;

    TelephonyManager telephonyManager;

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
        if (!hasPermissions(this, permissions)) {
            ActivityCompat.requestPermissions(this, permissions, permissionAll);
            telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        } else {
            telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        }


        // Session Manager
        session = new SessionManager(getApplicationContext());
        db = new DatabaseHelper(getApplicationContext());
    }

    @Override
    protected void onResume() {
        super.onResume();
        session.logoutUser();
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

    @SuppressLint("MissingPermission")
    @OnClick(R.id.btnLogin)
    public void logIn() {
        SalesMan user = db.validateUser(
                txtLoginUser.getText().toString(),
                txtLoginPass.getText().toString());

        if (user != null) {
            session.logoutUser();
            session.createLoginSession(user.getName(), user.getCodSeller(), user.getId());
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            LoginServicePresenter l = new LoginServicePresenter(new LoginServiceInterface() {
                @Override
                public void displayProgressBar() {

                }

                @Override
                public void hideProgressBar() {

                }

                @Override
                public void displaySeller(SellerResponse sellerResponse) {
                    if(sellerResponse!=null){
                        SalesMan user = SalesMan.toSalesMan(sellerResponse);
                        if(!db.isUserExists(sellerResponse.getId())){
                            db.createUser(user);
                        }
                        session.logoutUser();
                        session.createLoginSession(user.getName(), user.getCodSeller(), user.getId());
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, R.string.login_user_error, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void displayError(String errorMessage) {
                    Toast.makeText(LoginActivity.this, R.string.login_user_error, Toast.LENGTH_LONG).show();
                }
            });
            l.login(telephonyManager.getDeviceId());
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