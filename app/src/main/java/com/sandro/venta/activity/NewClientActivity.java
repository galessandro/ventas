package com.sandro.venta.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sandro.venta.R;
import com.sandro.venta.bean.Client;
import com.sandro.venta.helper.DatabaseHelper;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;

public class NewClientActivity extends AppCompatActivity {

    private EditText txtClientFirstName;
    private EditText txtClientLastName;
    private EditText txtClientAddress;
    private EditText txtClientRuc;
    private EditText txtClientDni;

    // Database Helper
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_client);
        db = new DatabaseHelper(getApplicationContext());

        configureNewClient();
    }

    private void configureNewClient() {
        txtClientFirstName = (EditText) findViewById(R.id.txtClientFirstName);
        txtClientLastName = (EditText) findViewById(R.id.txtClientLastName);
        txtClientAddress = (EditText) findViewById(R.id.txtClientAddress);
        txtClientRuc = (EditText) findViewById(R.id.txtClientRuc);
        txtClientDni = (EditText) findViewById(R.id.txtClientDni);
        Button btnSaveClient = (Button) findViewById(R.id.btnSaveClient);

        btnSaveClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateForm()) {
                    Client client = loadClientFromActivity();
                    saveClient(client);
                }
            }
        });

    }

    private boolean validateForm() {
        if(StringUtils.isEmpty(txtClientFirstName.getText().toString())){
            txtClientFirstName.setError(
                    getResources().getString(R.string.client_add_error_required_value));
            return false;
        }

        if(StringUtils.isEmpty(txtClientLastName.getText().toString())){
            txtClientLastName.setError(
                    getResources().getString(R.string.client_add_error_required_value));
            return false;
        }

        if(StringUtils.isEmpty(txtClientAddress.getText().toString())){
            txtClientAddress.setError(
                    getResources().getString(R.string.client_add_error_required_value));
            return false;
        }

        if(StringUtils.isEmpty(txtClientRuc.getText().toString())){
            txtClientRuc.setError(
                    getResources().getString(R.string.client_add_error_required_value));
            return false;
        }

        if(StringUtils.isEmpty(txtClientDni.getText().toString())){
            txtClientDni.setError(
                    getResources().getString(R.string.client_add_error_required_value));
            return false;
        }

        return true;
    }

    private void saveClient(Client client) {
        db.createClient(client);
        Intent intent = new Intent();
        intent.putExtra("clientAdded", client);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(getResources().getString(R.string.activity_back_title))
                .setMessage(getResources().getString(R.string.activity_back_warning))
                .setPositiveButton(getResources().getString(R.string.activity_back_close_yes),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                setResult(Activity.RESULT_CANCELED);
                                finish();
                            }
                        })
                .setNegativeButton(getResources().getString(R.string.activity_back_close_no),
                        null)
                .show();
    }

    private Client loadClientFromActivity() {
        Client client = new Client();
        client.setCodClient(db.getMaxCodClient() + 1);
        client.setFirstName(txtClientFirstName.getText().toString());
        client.setLastName(txtClientLastName.getText().toString());
        client.setAddress(txtClientAddress.getText().toString());
        client.setRuc(txtClientRuc.getText().toString());
        client.setDni(txtClientDni.getText().toString());
        client.setDateReg(new Date());
        return client;
    }
}
