package com.sandro.venta.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
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
import android.widget.Toast;

import com.sandro.venta.R;
import com.sandro.venta.adapter.ClientAdapter;
import com.sandro.venta.bean.Client;
import com.sandro.venta.helper.DatabaseHelper;

public class SelectClientActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private EditText txtSearchSelectedClient;

    private DatabaseHelper db;
    private ClientAdapter clientAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_client);

        db = new DatabaseHelper(getApplicationContext());

        // Create a new TodoListAdapter for this ListActivity's ListView
        clientAdapter = new ClientAdapter(getApplicationContext(), db.getAllClients());

        ListView lstClientsView = (ListView) findViewById(R.id.lstClientsView);

        // Attach the adapter to this ListActivity's ListView
        lstClientsView.setAdapter(clientAdapter);

        lstClientsView.setOnItemClickListener(this);

        txtSearchSelectedClient = (EditText) findViewById(R.id.txtSearchSelectedClient);
        txtSearchSelectedClient.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String searchClient = txtSearchSelectedClient.getText().toString();
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
    public void onItemClick(AdapterView<?> list, View view, int position, long id) {
        Client client = (Client) list.getItemAtPosition(position);

        if (client != null) {
            Intent intent = new Intent();
            intent.putExtra("selectedClient", client);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    }
}
