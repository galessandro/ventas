package com.sandro.venta.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.sandro.venta.R;
import com.sandro.venta.adapter.ClientAdapter;
import com.sandro.venta.bean.Client;
import com.sandro.venta.bean.SalesMan;
import com.sandro.venta.helper.DatabaseHelper;
import com.sandro.venta.util.SessionManager;


public class ClientsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,
        AdapterView.OnItemLongClickListener {

    private EditText txtSearchClient;

    DatabaseHelper db;
    ClientAdapter clientAdapter;
    private SessionManager session;
    private final int REQUEST_CLIENT_CODE = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clients);

        session = new SessionManager(getApplicationContext());
        SalesMan salesMan = session.getUserDetails();

        db = new DatabaseHelper(getApplicationContext());

        // Create a new TodoListAdapter for this ListActivity's ListView
        clientAdapter = new ClientAdapter(getApplicationContext(),
                db.getAllClientsFromSeller(salesMan.getCodSeller()));

        ListView lstClientsView = (ListView) findViewById(R.id.lstClientsView);

        // Attach the adapter to this ListActivity's ListView
        lstClientsView.setAdapter(clientAdapter);

        lstClientsView.setOnItemClickListener(this);
        lstClientsView.setOnItemLongClickListener(this);

        txtSearchClient = (EditText) findViewById(R.id.txtSearchClient);
        txtSearchClient.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String searchClient = txtSearchClient.getText().toString();
                clientAdapter.getFilter().filter(searchClient);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        // Load saved clients, if necessary
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.client_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_client:
                goToAddClientActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void goToAddClientActivity() {
        Intent intent = new Intent(this, NewClientActivity.class);
        startActivityForResult(intent, REQUEST_CLIENT_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_CLIENT_CODE){
            if(resultCode == Activity.RESULT_OK){
                Client clientAdded = data.getParcelableExtra("clientAdded");
                clientAdapter.add(clientAdded);
            } else if (resultCode == Activity.RESULT_CANCELED){
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> list, View view, int position, long id) {
        Client client = (Client) list.getItemAtPosition(position);

        if (client != null) {
            Intent intent = new Intent(this, ViewClientActivity.class);
            intent.putExtra("selectedClient", client);
            startActivity(intent);
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        return false;
    }
}
