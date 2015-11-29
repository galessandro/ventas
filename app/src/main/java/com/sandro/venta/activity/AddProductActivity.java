package com.sandro.venta.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sandro.venta.R;
import com.sandro.venta.bean.Product;

public class AddProductActivity extends AppCompatActivity {

    private TextView lblCodProduct;
    private TextView lblNameProduct;
    private TextView lblBoxByProduct;
    private TextView lblPriceTwoProduct;
    private EditText txtPriceProduct;
    private EditText txtQuantityProduct;
    private Button btnAddProduct;
    private Product selectedProduct;
    private int quantity;
    private double price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        configureAddProduct();
        selectedProduct = getIntent().getParcelableExtra("selectedProduct");
        loadProductOnView(selectedProduct);
    }

    private void loadProductOnView(Product selectedProduct) {
        lblCodProduct.setText(selectedProduct.getCodProduct());
        lblNameProduct.setText(selectedProduct.getName());
        lblBoxByProduct.setText(String.valueOf(selectedProduct.getBoxBy()));
        lblPriceTwoProduct.setText(selectedProduct.getPriceTwo().toString());
        txtPriceProduct.setText(selectedProduct.getPriceTwo().toString());
    }

    private void configureAddProduct() {
        lblCodProduct = (TextView) findViewById(R.id.add_product_codigo);
        lblNameProduct = (TextView) findViewById(R.id.add_product_nombre);
        lblBoxByProduct = (TextView) findViewById(R.id.add_product_cajapor);
        lblPriceTwoProduct = (TextView) findViewById(R.id.add_product_preciodos);
        txtPriceProduct = (EditText) findViewById(R.id.add_product_precio);
        txtQuantityProduct = (EditText) findViewById(R.id.add_product_cantidad);
        btnAddProduct = (Button) findViewById(R.id.add_product_btn_add);
        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validarDatosIngresados()) {
                    capturarDatosIngresados();
                    addProduct();
                }
            }
        });
    }

    private boolean validarDatosIngresados() {

        return false;
    }

    private void addProduct() {
        Intent intent = new Intent();
        intent.putExtra("productAdded", selectedProduct);
        intent.putExtra("quantityAdded", quantity);
        intent.putExtra("priceAdded", price);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    private void capturarDatosIngresados() {
        quantity = Integer.parseInt(txtQuantityProduct.getText().toString());
        price = Double.parseDouble(txtPriceProduct.getText().toString());
    }


}
