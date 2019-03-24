package com.sandro.venta.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sandro.venta.R;
import com.sandro.venta.bean.SalesMan;
import com.sandro.venta.helper.DatabaseHelper;
import com.sandro.venta.util.SessionManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.txtLoginUser) EditText txtLoginUser;
    @BindView(R.id.txtLoginPass) EditText txtLoginPass;
    @BindView(R.id.btnLogin) Button btnLogin;
    DatabaseHelper db;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        configActivity();

        // Session Manager
        session = new SessionManager(getApplicationContext());
        db = new DatabaseHelper(getApplicationContext());
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