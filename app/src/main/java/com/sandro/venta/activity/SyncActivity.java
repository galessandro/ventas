package com.sandro.venta.activity;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.sandro.venta.R;
import com.sandro.venta.adapter.SyncAdapter;
import com.sandro.venta.bean.Sync;

import java.util.ArrayList;
import java.util.List;

public class SyncActivity extends AppCompatActivity {

    private SyncAdapter syncAdapter;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync);

        // Create a new TodoListAdapter for this ListActivity's ListView
        syncAdapter = new SyncAdapter(getApplicationContext(), getAllSyncs());

        ListView lstSyncs = (ListView) findViewById(R.id.listSyncItems);

        // Attach the adapter to this ListActivity's ListView
        lstSyncs.setAdapter(syncAdapter);

        lstSyncs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
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
                Toast.makeText(this, "chambito", Toast.LENGTH_SHORT).show();;
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private List<Sync> getAllSyncs() {
        List<Sync> syncs = new ArrayList<>();
        Sync productSync = new Sync();
        productSync.setCod(1);
        productSync.setName("PRODUCTOS");
        syncs.add(productSync);
        return syncs;
    }
}
