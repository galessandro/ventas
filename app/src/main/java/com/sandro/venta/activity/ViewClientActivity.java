package com.sandro.venta.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.sandro.venta.R;
import com.sandro.venta.bean.Client;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ViewClientActivity extends AppCompatActivity {

    @BindView(R.id.txtViewClientCod) TextView txtClientCode;
    @BindView(R.id.txtViewClientDocumento) TextView txtViewClientDocumento;
    @BindView(R.id.txtViewClientName) TextView txtClientName;
    @BindView(R.id.txtViewClientAddress) TextView txtClientAddress;
    @BindView(R.id.txtViewClientUbigeo) TextView txtClientUbigeo;
    @BindView(R.id.txtViewClientZona) TextView txtClientZona;
    @BindView(R.id.imgViewSemaphore) ImageView imgViewSemaphore;
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
        txtViewClientDocumento.setText(client.getCodEntidad() + "-" + client.getDocumento());
        txtClientName.setText(client.getFullName());
        txtClientAddress.setText(client.getAddress());
        txtClientUbigeo.setText(client.getUbigeo());
        txtClientZona.setText(client.getZona());

        GradientDrawable shape = (GradientDrawable) getResources().getDrawable(R.drawable.circle);
        int color = Color.RED;

        if(client.getSemaphore().equals("A")){
            color = Color.rgb(255, 191, 0);
        } else if(client.getSemaphore().equals("V")){
            color = Color.GREEN;
        }

        shape.setColor(color);

        imgViewSemaphore.setImageDrawable(shape);
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
