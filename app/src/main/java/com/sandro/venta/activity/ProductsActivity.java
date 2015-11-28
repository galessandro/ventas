package com.sandro.venta.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.sandro.venta.R;
import com.sandro.venta.adapter.ClientAdapter;
import com.sandro.venta.adapter.ProductAdapter;
import com.sandro.venta.bean.Client;
import com.sandro.venta.bean.Product;
import com.sandro.venta.helper.DatabaseHelper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductsActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener {

    private EditText txtSearchProduct;
    private DatabaseHelper db;
    private ProductAdapter productAdapter;
    private static final int REQUEST_PRODUCT_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        db = new DatabaseHelper(getApplicationContext());

        // Create a new TodoListAdapter for this ListActivity's ListView
        //productAdapter = new ProductAdapter(getApplicationContext(), db.getAllProducts());
        productAdapter = new ProductAdapter(getApplicationContext(), getProductos());


        ListView lstProductsView = (ListView) findViewById(R.id.lstProductsView);

        // Attach the adapter to this ListActivity's ListView
        lstProductsView.setAdapter(productAdapter);

        lstProductsView.setOnItemClickListener(this);

        txtSearchProduct = (EditText) findViewById(R.id.txtSearchProduct);
        txtSearchProduct.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String searchProduct = txtSearchProduct.getText().toString();
                productAdapter.getFilter().filter(searchProduct);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public List<Product> getProductos(){
        List<Product> listaProductos = new ArrayList<>();
        for (int i = 0; i <= 20; i++){
            Product p = new Product();
            p.setId(i);
            p.setCodProduct("CX10" + i);
            p.setPriceOne(100d);
            p.setPriceTwo(200d);
            p.setName("Galletas saladitas" + i);
            p.setBoxBy(1);
            p.setTypeUnit("U");

            listaProductos.add(p);
        }
        return listaProductos;
    }

    /**
     * Callback method to be invoked when an item in this AdapterView has
     * been clicked.
     * <p/>
     * Implementers can call getItemAtPosition(position) if they need
     * to access the data associated with the selected item.
     *
     * @param list     The AdapterView where the click happened.
     * @param view     The view within the AdapterView that was clicked (this
     *                 will be a view provided by the adapter)
     * @param position The position of the view in the adapter.
     * @param id       The row id of the item that was clicked.
     */
    @Override
    public void onItemClick(AdapterView<?> list, View view, int position, long id) {
        Product product = (Product) list.getItemAtPosition(position);

        if (product != null) {
            Intent intent = new Intent(this, AddProductActivity.class);
            intent.putExtra("selectedProduct", product);
            startActivityForResult(intent, REQUEST_PRODUCT_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_PRODUCT_CODE){
            if(resultCode == Activity.RESULT_OK){
                Product productAdded = data.getParcelableExtra("productAdded");
                int quantityAdded = data.getIntExtra("quantityAdded", 0);
                double priceAdded = data.getDoubleExtra("priceAdded", 0d);
                Intent intent = new Intent();
                intent.putExtra("productAdded", productAdded);
                intent.putExtra("quantityAdded", quantityAdded);
                intent.putExtra("priceAdded", priceAdded);
                setResult(Activity.RESULT_OK, intent);
                finish();
            } else if (resultCode == Activity.RESULT_CANCELED){
                Toast.makeText(this, "cancelo", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
