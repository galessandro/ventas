package com.sandro.venta.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sandro.venta.R;
import com.sandro.venta.bean.SalesMan;

public class LoginActivity extends AppCompatActivity {

    private EditText txtLoginUser;
    private EditText txtLoginPass;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        configActivity();
    }

    private void configActivity() {
        txtLoginUser = (EditText) findViewById(R.id.txtLoginUser);
        txtLoginPass = (EditText) findViewById(R.id.txtLoginPass);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logIn();
            }
        });
    }

    private void logIn(){
        SalesMan user = new SalesMan();
        user.setUser(txtLoginUser.getText().toString());
        user.setPass(txtLoginPass.getText().toString());

        Intent intent = new Intent(LoginActivity.this, ListOrdersActivity.class);
        startActivity(intent);

       /* if (user.getUser().equals("admin") && user.getPass().equals("admin")) {
            Intent intent = new Intent(LoginActivity.this, ListOrdersActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, R.string.login_user_error, Toast.LENGTH_LONG).show();
        }*/
    }
}
