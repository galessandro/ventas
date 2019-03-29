package com.sandro.venta.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sandro.venta.R;
import com.sandro.venta.bean.Client;
import com.sandro.venta.bean.Order;
import com.sandro.venta.bean.SalesMan;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ViewClientActivity extends AppCompatActivity {

    @BindView(R.id.txtViewClientCod) TextView txtClientCode;
    @BindView(R.id.txtViewClientFirstName) TextView txtClientFirstName;
    @BindView(R.id.txtViewClientLastName) TextView txtClientLastName;
    @BindView(R.id.txtViewClientAddress) TextView txtClientAddress;
    @BindView(R.id.txtViewClientRuc) TextView txtClientRuc;
    @BindView(R.id.txtViewClientDni) TextView txtClientDni;
    private Client client;
    private static final int REQUEST_NEW_ORDER_CODE = 104;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_client);
        ButterKnife.bind(this);
        client = getIntent().getParcelableExtra("selectedClient");
        loadClientToView();
    }

    private void loadClientToView(){
        txtClientCode.setText(String.valueOf(client.getCodClient()));
        txtClientFirstName.setText(client.getFirstName());
        txtClientLastName.setText(client.getLastName());
        txtClientAddress.setText(client.getAddress());
        txtClientRuc.setText(client.getRuc());
        txtClientDni.setText(client.getDni());
    }

    @OnClick(R.id.btnNewOrder)
    public void newOrder(){
        Intent intent = new Intent(ViewClientActivity.this, NewOrderActivity.class);
        intent.putExtra("selectedClient", client);
        startActivityForResult(intent, REQUEST_NEW_ORDER_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_NEW_ORDER_CODE){
            if(resultCode == AppCompatActivity.RESULT_OK){
                Intent intent = new Intent(ViewClientActivity.this, MainActivity.class);
                intent.putExtra("value", 2);
                startActivity(intent);
            }
        }
    }

}
