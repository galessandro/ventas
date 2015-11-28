package com.sandro.venta.activity;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.sandro.venta.R;
import com.sandro.venta.bean.Client;

public class ViewClientActivity extends AppCompatActivity {

    private TextView txtClientCode;
    private TextView txtClientFirstName;
    private TextView txtClientLastName;
    private TextView txtClientAddress;
    private TextView txtClientRuc;
    private TextView txtClientDni;
    private Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_client);
        configureNewClient();
        client = getIntent().getParcelableExtra("selectedClient");
        loadClientToView();
    }

    private void configureNewClient() {
        txtClientCode = (TextView) findViewById(R.id.txtViewClientCod);
        txtClientFirstName = (TextView) findViewById(R.id.txtViewClientFirstName);
        txtClientLastName = (TextView) findViewById(R.id.txtViewClientLastName);
        txtClientAddress = (TextView) findViewById(R.id.txtViewClientAddress);
        txtClientRuc = (TextView) findViewById(R.id.txtViewClientRuc);
        txtClientDni = (TextView) findViewById(R.id.txtViewClientDni);
    }

    private void loadClientToView(){
        txtClientCode.setText(String.valueOf(client.getCodClient()));
        txtClientFirstName.setText(client.getFirstName());
        txtClientLastName.setText(client.getLastName());
        txtClientAddress.setText(client.getAddress());
        txtClientRuc.setText(client.getRuc());
        txtClientDni.setText(client.getDni());

    }
}
