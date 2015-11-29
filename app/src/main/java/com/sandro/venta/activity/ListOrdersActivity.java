package com.sandro.venta.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.sandro.venta.R;
import com.sandro.venta.adapter.OrderAdapter;
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
        orderAdapter = new OrderAdapter(getApplicationContext(), db.getOrdersFromToday());

        ListView lstOrders = (ListView) findViewById(R.id.lstRealOrders);

        // Attach the adapter to this ListActivity's ListView
        lstOrders.setAdapter(orderAdapter);

        lstOrders.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListOrdersActivity.this, "Ordenes", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
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
        } else if (id == R.id.nav_closeSale) {
            intent.setClass(this, CloseSaleActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
