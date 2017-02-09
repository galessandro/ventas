package com.sandro.venta.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.sandro.venta.R;
import com.sandro.venta.adapter.ItemViewAdapter;
import com.sandro.venta.bean.Order;
import com.sandro.venta.util.DateUtil;

import java.text.DecimalFormat;


public class ViewOrderDetailActivity extends AppCompatActivity {

    private Order order;
    private TextView txtOrderCod;
    private TextView txtOrderClient;
    private TextView txtOrderSeller;
    private TextView txtOrderPaymentTypeView;
    private TextView txtOrderDate;
    private TextView txtOrderDeliveryDate;
    private TextView txtOrderTotalAmount;
    private TextView lblTotalProducts;
    private ListView lstItemsView;
    private ItemViewAdapter itemViewAdapter;
    private DecimalFormat df = new DecimalFormat("#.##");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order_detail);
        order = getIntent().getParcelableExtra("order");

        itemViewAdapter = new ItemViewAdapter(getApplicationContext(), order.getItems());
        findViewsById();
        loadOrderToView();

        lstItemsView.setAdapter(itemViewAdapter);

    }

    private void loadOrderToView() {
        txtOrderCod.setText(String.valueOf(order.getCodSale()));
        txtOrderClient.setText(order.getClient().getBusinessName());
        txtOrderSeller.setText(order.getSeller().getCodSeller());
        txtOrderPaymentTypeView.setText(order.getPaymentType() == Order.PAYMENT_TYPE_CASH ?
        Order.PAYMENT_TYPE_DESC_CASH : Order.PAYMENT_TYPE_DESC_CREDIT);
        txtOrderDate.setText(DateUtil.getFormatDate(order.getDateOrder(), DateUtil.datePeruFormat));
        txtOrderDeliveryDate.setText(DateUtil.getFormatDate(order.getDateDelivery(),
                DateUtil.datePeruFormat));
        txtOrderTotalAmount.setText(df.format(order.getTotalAmount()));
        lblTotalProducts.setText(String.valueOf(order.getItems().size()));
    }

    private void findViewsById() {
        txtOrderCod = (TextView) findViewById(R.id.txtOrderCodView);
        txtOrderClient = (TextView) findViewById(R.id.txtOrderClientView);
        txtOrderSeller = (TextView) findViewById(R.id.txtOrderSellerView);
        txtOrderPaymentTypeView = (TextView) findViewById(R.id.txtOrderPaymentTypeView);
        txtOrderDate = (TextView) findViewById(R.id.txtOrderDateView);
        txtOrderDeliveryDate  = (TextView) findViewById(R.id.txtOrderDeliveryDateView);
        txtOrderTotalAmount = (TextView) findViewById(R.id.txtOrderTotalAmountView);
        lblTotalProducts  = (TextView) findViewById(R.id.lblTotalProductsView);
        lstItemsView = (ListView) findViewById(R.id.lstItemsView);
    }
}
