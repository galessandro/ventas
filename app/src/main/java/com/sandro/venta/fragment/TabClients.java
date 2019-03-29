package com.sandro.venta.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.sandro.venta.R;
import com.sandro.venta.activity.ViewClientActivity;
import com.sandro.venta.adapter.ListClientAdapter;
import com.sandro.venta.bean.Client;
import com.sandro.venta.bean.ListClient;
import com.sandro.venta.bean.SalesMan;
import com.sandro.venta.helper.DatabaseHelper;
import com.sandro.venta.util.SessionManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TabClients extends Fragment implements SearchView.OnQueryTextListener, ListClientAdapter.OnClientListener {

    @BindView(R.id.rcyViewClients)
    RecyclerView recyclerView;
    ListClientAdapter adapter;

    Unbinder unbinder;

    private List<Client> listClients;

    DatabaseHelper db;
    private SessionManager session;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.fragment_tab_clients, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        session = new SessionManager(this.getContext());
        SalesMan salesMan = session.getUserDetails();

        db = new DatabaseHelper(this.getContext());

        listClients = db.getAllClientsFromSeller(salesMan.getCodSeller());


        adapter = new ListClientAdapter(listClients, this.getContext(), this);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.client_tab_menu, menu);

        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(this);

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        List<Client> listClientsFiltered  = filter(listClients, s);
        adapter.setFilter(listClientsFiltered);
        return false;
    }

    private List<Client> filter(List<Client> listClientsFull, String nombre){
        List<Client> listClientsFiltered = new ArrayList<>();
        for (Client client: listClientsFull) {
            if(client.getBusinessName().toLowerCase().contains(nombre.toLowerCase())){
                listClientsFiltered.add(client);
            }
        }
        return listClientsFiltered;
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClientClick(int position) {
        Client client = adapter.getItem(position);

        if (client != null) {
            Intent intent = new Intent(this.getContext(), ViewClientActivity.class);
            intent.putExtra("selectedClient", client);
            startActivity(intent);
        }
    }
}