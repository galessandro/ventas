package com.sandro.venta.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sandro.venta.R;
import com.sandro.venta.adapter.ItemAdapter;
import com.sandro.venta.bean.Client;
import com.sandro.venta.bean.Item;
import com.sandro.venta.bean.Order;
import com.sandro.venta.bean.Product;
import com.sandro.venta.bean.SalesMan;
import com.sandro.venta.helper.DatabaseHelper;
import com.sandro.venta.util.DateUtil;
import com.sandro.venta.util.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class NewOrderActivity extends AppCompatActivity implements View.OnClickListener {

    private DatabaseHelper db;
    private ItemAdapter itemAdapter;

    private TextView txtOrderCod;
    private TextView txtOrderClient;
    private TextView txtOrderSeller;
    private Button txtOrderDate;
    private Button txtOrderDeliveryDate;
    private TextView txtOrderTotalAmount;
    private TextView lblTotalProducts;
    private ListView lstProductsView;
    private Order order;
    private SessionManager session;

    DatePickerDialog datePickerOrderDialog;
    DatePickerDialog datePickerDeliveryDialog;

    private static final int REQUEST_SEARCH_PRODUCT_CODE = 102;
    private static final int REQUEST_SEARCH_CLIENT_CODE = 103;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);

        session = new SessionManager(getApplicationContext());

        order = new Order();
        SalesMan salesMan = session.getUserDetails();
        order.setSeller(salesMan);


        db = new DatabaseHelper(getApplicationContext());

        itemAdapter = new ItemAdapter(this, new ArrayList<Item>());

        lstProductsView = (ListView) findViewById(R.id.lstItems);

        // Attach the adapter to this ListActivity's ListView
        lstProductsView.setAdapter(itemAdapter);

        findViewsById();
        setDefaultDate();
        setListeners();
        updateSalesMan();

    }

    private void findViewsById() {
        txtOrderCod = (TextView) findViewById(R.id.txtOrderCod);
        txtOrderClient = (TextView) findViewById(R.id.txtOrderClient);
        txtOrderSeller = (TextView) findViewById(R.id.txtOrderSeller);
        txtOrderDate = (Button) findViewById(R.id.txtOrderDate);
        txtOrderDeliveryDate  = (Button) findViewById(R.id.txtOrderDeliveryDate);
        txtOrderTotalAmount = (TextView) findViewById(R.id.txtOrderTotalAmount);
        lblTotalProducts  = (TextView) findViewById(R.id.lblTotalProducts);
        lblTotalProducts.setText(String.valueOf(itemAdapter.getCount()));
    }

    private void setListeners() {
        datePickerOrderDialog = new DatePickerDialog(getSupportActionBar().getThemedContext(),
                new DatePickerDialog.OnDateSetListener() {

                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        txtOrderDate.setText(DateUtil.getDateFromDialogDatePicker(
                                year,
                                monthOfYear,
                                dayOfMonth));
                    }

                }, DateUtil.getCurrentYear(),
                DateUtil.getCurrentMonth(),
                DateUtil.getCurrentDay());

        datePickerDeliveryDialog = new DatePickerDialog(getSupportActionBar().getThemedContext(),
                new DatePickerDialog.OnDateSetListener() {

                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        txtOrderDeliveryDate.setText(DateUtil.getDateFromDialogDatePicker(
                                year,
                                monthOfYear,
                                dayOfMonth));
                    }

                }, DateUtil.getCurrentYear(),
                DateUtil.getCurrentMonth(),
                DateUtil.getCurrentDay());

        txtOrderDate.setOnClickListener(this);
        txtOrderDeliveryDate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == txtOrderDate) {
            datePickerOrderDialog.show();
        }else if (v == txtOrderDeliveryDate){
            datePickerDeliveryDialog.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.order_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i;
        switch (item.getItemId()) {
            case R.id.order_set_cliente:
                i = new Intent(this, SelectClientActivity.class);
                startActivityForResult(i, REQUEST_SEARCH_CLIENT_CODE);
                return true;
            case R.id.order_add_product:
                i = new Intent(this, ProductsActivity.class);
                startActivityForResult(i, REQUEST_SEARCH_PRODUCT_CODE);
                return true;
            case R.id.order_save_order:
                Toast.makeText(this, "Save order", Toast.LENGTH_SHORT).show();
                order.setItems(itemAdapter.getItems());
                order.setCodOrder(1);
                order.setCodSale(1);
                order.setDateDelivery(DateUtil.getDate(txtOrderDeliveryDate.getText().toString()));
                order.setDateOrder(DateUtil.getDate(txtOrderDate.getText().toString()));
                db.createOrder(order);
                for (Item saveItem : order.getItems()) {
                    db.createOrderItem(saveItem);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_SEARCH_PRODUCT_CODE){
            if(resultCode == Activity.RESULT_OK){
                Product productAdded = data.getParcelableExtra("productAdded");
                if(!productAlreadyAdded(productAdded)) {
                    int quantityAdded = data.getIntExtra("quantityAdded", 0);
                    double priceAdded = data.getDoubleExtra("priceAdded", 0d);
                    Item item = new Item();
                    item.setProduct(productAdded);
                    item.setQuantity(quantityAdded);
                    item.setPrice(priceAdded);
                    item.setTypePrice("P");
                    itemAdapter.add(item);
                    updateTotalProducts();
                    updateTotalOrderAmount();
                } else {
                    Toast.makeText(this,
                            getResources().getString(R.string.product_add_error_product_duplicate),
                            Toast.LENGTH_SHORT).show();
                }
            } else if (resultCode == Activity.RESULT_CANCELED){
            }
        }else if(requestCode == REQUEST_SEARCH_CLIENT_CODE){
            if(resultCode == Activity.RESULT_OK){
                Client clientAdded = data.getParcelableExtra("selectedClient");
                order.setClient(clientAdded);
                updateClient();
            } else if (resultCode == Activity.RESULT_CANCELED){
            }
        }
    }

    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);
    }

    private boolean productAlreadyAdded(Product productAdded) {
        boolean isProductAlreadyAdded = false;
        List<Item> lstItemsAdded = itemAdapter.getItems();
        for (Item item: lstItemsAdded ) {
            if(productAdded.getCodProduct().equals(item.getProduct().getCodProduct())){
                isProductAlreadyAdded = true;
                break;
            }
        }
        return isProductAlreadyAdded;
    }

    public void updateTotalProducts() {
        lblTotalProducts.setText(String.valueOf(itemAdapter.getCount()));
    }

    public void updateTotalOrderAmount() {
        txtOrderTotalAmount.setText(
                getResources().getString(R.string.simbolo_nuevo_sol).concat(
                        String.valueOf(itemAdapter.getTotalAmount())));
    }

    public void updateClient(){
        txtOrderClient.setText(order.getClient().getBusinessName());
    }

    public void updateSalesMan(){
        txtOrderSeller.setText(order.getSeller().getName());
    }


    // Do not modify below this point.
    private void setDefaultDate() {
        txtOrderDate.setText(DateUtil.getCurrentDate());
        txtOrderDeliveryDate.setText(DateUtil.getCurrentDate());
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(getSupportActionBar().getThemedContext())
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
}
