package com.sandro.venta.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;

import com.sandro.venta.R;
import com.sandro.venta.adapter.SyncAdapter;
import com.sandro.venta.bean.Client;
import com.sandro.venta.bean.Product;
import com.sandro.venta.bean.Sync;
import com.sandro.venta.helper.DatabaseHelper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SyncActivity extends AppCompatActivity {

    private static final String TAG = "SyncActivity";
    private DatabaseHelper db;
    private SyncAdapter syncAdapter;
    private ListView lstSyncs;

    private static final int PRODUCTOS = 0;
    private static final int CLIENTES = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync);

        db = new DatabaseHelper(getApplicationContext());

        // Create a new TodoListAdapter for this ListActivity's ListView
        syncAdapter = new SyncAdapter(this, getAllSyncs());

        lstSyncs = (ListView) findViewById(R.id.listSyncItems);

        lstSyncs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                View v = lstSyncs.getChildAt(position);
                CheckedTextView c = (CheckedTextView) v.findViewById(R.id.chkSyncItem);
                c.setChecked(!c.isChecked());
            }
        });

        // Attach the adapter to this ListActivity's ListView
        lstSyncs.setAdapter(syncAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sync_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sync_items:
                List<Integer> checkedItems = getSelectedItems();
                if(checkedItems.size() <= 0){
                    Toast.makeText(SyncActivity.this,
                            getResources().getString(R.string.activity_sync_no_check_items)
                            ,Toast.LENGTH_SHORT).show();
                } else {
                    confirmSync(checkedItems);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private List<Integer> getSelectedItems() {
        SparseBooleanArray checkedItemPositions = lstSyncs.getCheckedItemPositions();
        List<Integer> checkedItems = new ArrayList<>();

        final int checkedItemCount = checkedItemPositions.size();
        for (int i = 0; i < checkedItemCount; i++) {
            int key = checkedItemPositions.keyAt(i);
            if (checkedItemPositions.get(key)) {
                checkedItems.add(key);
            }
        }
        return checkedItems;
    }

    private void confirmSync(final List<Integer> checkedItems) {
        new AlertDialog.Builder(getSupportActionBar().getThemedContext())
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(getResources().getString(R.string.activity_back_title))
                .setMessage(getResources().getString(R.string.activity_sync_warning))
                .setPositiveButton(getResources().getString(R.string.activity_back_close_yes),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                for (Integer i: checkedItems) {
                                    if (i == PRODUCTOS) {
                                        syncProductosFromFile();
                                    } else if (i == CLIENTES) {
                                        syncClientesFromFile();
                                    }
                                }
                            }
                        })
                .setNegativeButton(getResources().getString(R.string.activity_back_close_no),
                        null)
                .show();
    }

    private void syncProductosFromFile() {
        BufferedReader bufferedReader;
        try {
            File downloadPath =
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File file = new File(downloadPath, "ARTICULOS.TXT");
            bufferedReader = new BufferedReader(new FileReader(file));
            String read;
            List<Product> lstProducts = new ArrayList<>();

            while ((read = bufferedReader.readLine()) != null) {
                Product product = new Product();
                product.setCodProduct(read.substring(0, 9).replaceAll("\\s+", ""));
                product.setName(read.substring(9, 89).trim());
                product.setPriceOne(Double.parseDouble(read.substring(89, 99)));
                product.setPriceTwo(Double.parseDouble(read.substring(99, 109)));
                product.setBoxBy(Integer.parseInt(read.substring(109, 117)));
                product.setTypeUnit(read.substring(117, 118).isEmpty() ? "U" :
                        read.substring(117, 118));
                lstProducts.add(product);
            }

            bufferedReader.close();

            if(!lstProducts.isEmpty()){
                int deletedProducts = db.deleteAllProducts();
                Log.d(TAG, "deletedProducts:" + deletedProducts);
                for (Product newProduct : lstProducts) {
                    db.createProduct(newProduct);
                }
                Log.d(TAG, "newProducts:" + lstProducts.size());

                Toast.makeText(SyncActivity.this,
                        getResources().getString(R.string.activity_sync_success),
                        Toast.LENGTH_SHORT).show();
            }

        } catch (FileNotFoundException e) {
            Toast.makeText(this, getResources().getString(R.string.activity_sync_no_found_file),
                    Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (IOException e){
            Toast.makeText(this, getResources().getString(R.string.activity_sync_no_read_line),
                    Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void syncClientesFromFile() {
        BufferedReader bufferedReader;
        try {
            File downloadPath =
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File file = new File(downloadPath, "CLIENTES.TXT");
            bufferedReader = new BufferedReader(new FileReader(file));
            String read;
            List<Client> lstClients = new ArrayList<>();
            int i = 0;
            while ((read = bufferedReader.readLine()) != null) {
                Client client = new Client();
                i++;
                client.setCodClient(i);
                client.setRuc(read.substring(4, 15));
                client.setDni(read.substring(15, 23));
                client.setFirstName("");
                client.setLastName(read.substring(23, 113));
                client.setAddress(read.substring(113, 193));
                lstClients.add(client);
            }

            bufferedReader.close();

            if(!lstClients.isEmpty()){
                int deletedClients = db.deleteAllClients();
                Log.d(TAG, "deletedClients:" + deletedClients);
                for (Client newClient : lstClients) {
                    db.createClient(newClient);
                }
                Log.d(TAG, "newClients:" + lstClients.size());

                Toast.makeText(SyncActivity.this,
                        getResources().getString(R.string.activity_sync_success),
                        Toast.LENGTH_SHORT).show();
            }

        } catch (FileNotFoundException e) {
            Toast.makeText(this, getResources().getString(R.string.activity_sync_no_found_file),
                    Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (IOException e){
            Toast.makeText(this, getResources().getString(R.string.activity_sync_no_read_line),
                    Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private List<Sync> getAllSyncs() {
        List<Sync> syncs = new ArrayList<>();
        Sync sync = new Sync();
        sync.setCod(1);
        sync.setName("PRODUCTOS");
        syncs.add(sync);
        sync = new Sync();
        sync.setCod(2);
        sync.setName("CLIENTES");
        syncs.add(sync);
        return syncs;
    }
}
