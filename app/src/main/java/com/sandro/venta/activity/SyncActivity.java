package com.sandro.venta.activity;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.sandro.venta.R;
import com.sandro.venta.adapter.SyncAdapter;
import com.sandro.venta.bean.Client;
import com.sandro.venta.bean.PriceLevel;
import com.sandro.venta.bean.Product;
import com.sandro.venta.bean.Sync;
import com.sandro.venta.helper.DatabaseHelper;
import com.sandro.venta.util.ObjectResponse;

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
    private ProgressBar mProgressBar;
    private Button btnSync;

    private static final int PRODUCTOS = 0;
    private static final int CLIENTES = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync);

        btnSync = (Button) findViewById(R.id.btnSync);

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

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        btnSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Integer> checkedItems = getSelectedItems();
                if(checkedItems.size() <= 0){
                    Toast.makeText(SyncActivity.this,
                            getResources().getString(R.string.activity_sync_no_check_items)
                            ,Toast.LENGTH_SHORT).show();
                } else {
                    confirmSync(checkedItems);
                }
            }
        });

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
                                /*for (Integer i: checkedItems) {
                                    if (i == PRODUCTOS) {
                                        syncProductosFromFile();
                                    } else if (i == CLIENTES) {
                                        syncClientesFromFile();
                                    }
                                }*/
                                new SyncFileTask().execute(checkedItems);
                            }
                        })
                .setNegativeButton(getResources().getString(R.string.activity_back_close_no),
                        null)
                .show();
    }

    private ObjectResponse syncProductosFromFile() {
        ObjectResponse response = new ObjectResponse();
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
                product.setPriceThree(Double.parseDouble(read.substring(109, 119)));
                List<PriceLevel> priceLevels = new ArrayList<>();
                PriceLevel priceLevel = new PriceLevel();
                priceLevel.setLevel(1);
                priceLevel.setRangeFrom(Double.parseDouble(read.substring(119, 125)));
                priceLevel.setRangeTo(Double.parseDouble(read.substring(125, 131)));
                priceLevel.setPriceFrom(Double.parseDouble(read.substring(131, 141)));
                priceLevel.setPriceTo(Double.parseDouble(read.substring(141, 151)));
                priceLevels.add(priceLevel);
                priceLevel = new PriceLevel();
                priceLevel.setLevel(2);
                priceLevel.setRangeFrom(Double.parseDouble(read.substring(151, 157)));
                priceLevel.setRangeTo(Double.parseDouble(read.substring(157, 163)));
                priceLevel.setPriceFrom(Double.parseDouble(read.substring(163, 173)));
                priceLevel.setPriceTo(Double.parseDouble(read.substring(173, 183)));
                priceLevels.add(priceLevel);
                priceLevel = new PriceLevel();
                priceLevel.setLevel(3);
                priceLevel.setRangeFrom(Double.parseDouble(read.substring(183, 189)));
                priceLevel.setRangeTo(Double.parseDouble(read.substring(189, 195)));
                priceLevel.setPriceFrom(Double.parseDouble(read.substring(195, 205)));
                priceLevel.setPriceTo(Double.parseDouble(read.substring(205, 215)));
                priceLevels.add(priceLevel);
                priceLevel = new PriceLevel();
                priceLevel.setLevel(4);
                priceLevel.setRangeFrom(Double.parseDouble(read.substring(215, 221)));
                priceLevel.setRangeTo(Double.parseDouble(read.substring(221, 227)));
                priceLevel.setPriceFrom(Double.parseDouble(read.substring(227, 237)));
                priceLevel.setPriceTo(Double.parseDouble(read.substring(237, 247)));
                priceLevels.add(priceLevel);
                priceLevel = new PriceLevel();
                priceLevel.setLevel(5);
                priceLevel.setRangeFrom(Double.parseDouble(read.substring(247, 253)));
                priceLevel.setRangeTo(Double.parseDouble(read.substring(253, 259)));
                priceLevel.setPriceFrom(Double.parseDouble(read.substring(259, 269)));
                priceLevel.setPriceTo(Double.parseDouble(read.substring(269, 279)));
                priceLevels.add(priceLevel);
                product.setPriceLevelList(priceLevels);
                product.setBoxBy(Integer.parseInt(read.substring(279, 287)));
                product.setTypeUnit(read.substring(287, 288).isEmpty() ? "U" :
                        read.substring(287, 288));
                product.setPriceOfList(Integer.parseInt(read.substring(288, 294)));
                product.setFlagPrice(read.substring(294, 295).trim());
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
                response.setError(false);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            response.addMensaje(getResources().getString(R.string.activity_sync_no_found_file_producto));
            response.setError(true);

        } catch (IOException e){
            e.printStackTrace();
            response.addMensaje(getResources().getString(R.string.activity_sync_no_read_line_producto));
            response.setError(true);

        }
        return response;
    }

    private ObjectResponse syncClientesFromFile() {
        ObjectResponse response = new ObjectResponse();
        BufferedReader bufferedReader;
        try {
            File downloadPath =
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File file = new File(downloadPath, "CLIENTES.TXT");
            bufferedReader = new BufferedReader(new FileReader(file));
            String read;
            List<Client> lstClients = new ArrayList<>();
            while ((read = bufferedReader.readLine()) != null) {
                Client client = new Client();
                client.setCodClient(Integer.parseInt(read.substring(0, 8)));
                client.setRuc(read.substring(8, 19));
                client.setDni(read.substring(19, 27));
                client.setFirstName("");
                client.setLastName(read.substring(27, 117));
                client.setAddress(read.substring(117, 197));
                client.setCodSeller(read.substring(197, 199));
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

                response.setError(false);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            response.addMensaje(getResources().getString(R.string.activity_sync_no_found_file_cliente));
            response.setError(true);
        } catch (IOException e){
            e.printStackTrace();
            response.addMensaje(getResources().getString(R.string.activity_sync_no_found_file_cliente));
            response.setError(true);
        }
        return response;
    }

    class SyncFileTask extends AsyncTask<List<Integer>, Integer, List<ObjectResponse>> {
        @Override
        protected void onPreExecute() {
            btnSync.setEnabled(false);
            mProgressBar.setVisibility(ProgressBar.VISIBLE);
        }

        @Override
        protected List<ObjectResponse> doInBackground(List<Integer>... checkedItems) {
            List<ObjectResponse> response = new ArrayList<>();
            List<Integer> result = checkedItems[0];
            for (Integer i: result) {
                if (i == PRODUCTOS) {
                    ObjectResponse resultProducts = syncProductosFromFile();
                    response.add(resultProducts);
                    publishProgress(20);
                } else if (i == CLIENTES) {
                    publishProgress(40);
                    ObjectResponse resultClients = syncClientesFromFile();
                    response.add(resultClients);
                    publishProgress(100);
                }
            }
            return response;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            mProgressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(List<ObjectResponse> results) {
            mProgressBar.setVisibility(ProgressBar.INVISIBLE);
            btnSync.setEnabled(true);
            boolean error = false;
            String mensajeError = "";
            for (ObjectResponse response: results ) {
                if(response.isError()){
                    error = true;
                }
                mensajeError += response.getMensajesError();
            }

            if(error){
                Toast.makeText(SyncActivity.this,
                        mensajeError,
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(SyncActivity.this,
                        getResources().getString(R.string.activity_sync_success),
                        Toast.LENGTH_SHORT).show();
            }

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
