package com.sandro.venta.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.sandro.venta.R;
import com.sandro.venta.adapter.OrderAdapter;
import com.sandro.venta.bean.Order;
import com.sandro.venta.helper.DatabaseHelper;

public class ListOrdersActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int REQUEST_NEW_ORDER_CODE = 104;
    DatabaseHelper db;
    OrderAdapter orderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_orders);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListOrdersActivity.this, NewOrderActivity.class);
                startActivityForResult(intent, REQUEST_NEW_ORDER_CODE);
            }
        });

        //get the drawable
        Drawable myFabSrc = ResourcesCompat.getDrawable(getResources(),
                android.R.drawable.ic_input_add, getTheme());
        //copy it in a new one
        Drawable willBeWhite = myFabSrc.getConstantState().newDrawable();
        //set the color filter, you can use also Mode.SRC_ATOP
        willBeWhite.mutate().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        //set it to your fab button initialized before
        fab.setImageDrawable(willBeWhite);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        db = new DatabaseHelper(getApplicationContext());

        // Create a new TodoListAdapter for this ListActivity's ListView
        orderAdapter = new OrderAdapter(this, db.getOrdersFromToday());

        ListView lstOrders = (ListView) findViewById(R.id.lstRealOrders);

        // Attach the adapter to this ListActivity's ListView
        lstOrders.setAdapter(orderAdapter);

        lstOrders.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Order order = orderAdapter.getItem(position);
                viewOrderDetail(order);
            }
        });
    }

    private void viewOrderDetail(Order order) {
        Intent intent = new Intent(this, ViewOrderDetailActivity.class);
        intent.putExtra("order", order);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(getSupportActionBar().getThemedContext())
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(getResources().getString(R.string.activity_back_title))
                .setMessage(getResources().getString(R.string.order_logout))
                .setPositiveButton(getResources().getString(R.string.activity_back_close_yes),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                clearDrawer();
                                finish();
                            }
                        })
                .setNegativeButton(getResources().getString(R.string.activity_back_close_no),
                        null)
                .show();
    }

    private void clearDrawer(){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent = new Intent();

        if (id == R.id.nav_clientes) {
            intent.setClass(this, ClientsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_new_order) {
            intent.setClass(this, NewOrderActivity.class);
            startActivityForResult(intent, REQUEST_NEW_ORDER_CODE);
        } else if (id == R.id.nav_sync) {
            intent.setClass(this, SyncActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_export) {
            intent.setClass(this, ExportActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_NEW_ORDER_CODE){
            if(resultCode == AppCompatActivity.RESULT_OK){
                Order orderAdded = data.getParcelableExtra("orderAdded");
                orderAdapter.add(orderAdded);
            }
        }
    }

}
