package com.sandro.venta.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.sandro.venta.R;
import com.sandro.venta.activity.ViewClientActivity;
import com.sandro.venta.adapter.ListClientAdapter;
import com.sandro.venta.api.model.ControlResponse;
import com.sandro.venta.api.model.CustomerResponse;
import com.sandro.venta.api.model.ProductResponse;
import com.sandro.venta.api.service.ControlListServiceInterface;
import com.sandro.venta.api.service.ControlListServicePresenter;
import com.sandro.venta.api.service.ControlServiceInterface;
import com.sandro.venta.api.service.ControlServicePresenter;
import com.sandro.venta.api.service.CustomerServiceInterface;
import com.sandro.venta.api.service.CustomerServicePresenter;
import com.sandro.venta.api.service.ProductServiceInterface;
import com.sandro.venta.api.service.ProductServicePresenter;
import com.sandro.venta.bean.Client;
import com.sandro.venta.bean.Control;
import com.sandro.venta.bean.ListClient;
import com.sandro.venta.bean.Product;
import com.sandro.venta.bean.SalesMan;
import com.sandro.venta.helper.DatabaseHelper;
import com.sandro.venta.util.SessionManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemSelected;
import butterknife.Unbinder;

public class TabClients extends Fragment implements SearchView.OnQueryTextListener, ListClientAdapter.OnClientListener {

    @BindView(R.id.rcyViewClients) RecyclerView recyclerView;
    @BindView(R.id.shimmer) ShimmerFrameLayout shimmer;
    @BindView(R.id.spnDays) AppCompatSpinner spinner;

    ListClientAdapter adapter;
    ArrayAdapter<CharSequence> adapterDays;
    Unbinder unbinder;
    private List<Client> listClients;
    private DatabaseHelper db;
    private SessionManager session;
    private Context context;
    private final String TAG = "GGRANADOS";
    boolean firstLoadDays = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        context = this.getContext();
        View rootView = inflater.inflate(R.layout.fragment_tab_clients, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        shimmer.startShimmer();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        session = new SessionManager(this.getContext());
        SalesMan salesMan = session.getUserDetails();

        db = new DatabaseHelper(this.getContext());

        int maxIdControlCustomer = db.getMaxIdControlTable("T002");

        adapterDays = ArrayAdapter.createFromResource(context,
                R.array.days_array, android.R.layout.simple_spinner_item);
        adapterDays.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterDays);

        ControlServicePresenter cControl = new ControlServicePresenter(new ControlServiceInterface() {
            @Override
            public void displayProgressBar() {
                shimmer.startShimmer();
            }

            @Override
            public void hideProgressBar() {
                shimmer.stopShimmer();
            }

            @Override
            public void displayControl(ControlResponse controlResponse) {
                Log.i(TAG, String.valueOf(controlResponse.getId()));
                if(maxIdControlCustomer == 0) {
                    getCustomersbySeller(controlResponse, salesMan.getCodSeller());
                }else if(controlResponse.getId() > maxIdControlCustomer){
                    updateCustomers(maxIdControlCustomer, controlResponse, salesMan.getCodSeller());
                } else {
                    loadClients(salesMan.getCodSeller());
                }
            }

            @Override
            public void displayError(String errorMessage) {
                Log.e(TAG, "error:" + errorMessage);
                loadClients(salesMan.getCodSeller());
                shimmer.stopShimmer();
            }
        });
        cControl.getMaxIdControl("T002");

        return rootView;
    }

    private void updateCustomers(Integer controlIdFrom, ControlResponse controlResponse, String codSeller) {
        CustomerServicePresenter c = new CustomerServicePresenter(new CustomerServiceInterface() {
            @Override
            public void displayProgressBar() {
                shimmer.startShimmer();
            }

            @Override
            public void hideProgressBar() {
                shimmer.stopShimmer();
            }

            @Override
            public void displayCustomers(List<CustomerResponse> customerResponseList) {
                Log.i(TAG, "displayCustomers" + customerResponseList.size());
                db.clientBatchUpdate(Client.toClientList(customerResponseList));
                db.createControl(Control.toControl(controlResponse));
                loadClients(codSeller);
            }

            @Override
            public void displayError(String errorMessage) {
                Log.e(TAG, "ERROR:" + errorMessage);
                loadClients(codSeller);
                shimmer.stopShimmer();
            }
        });
        c.getCustomersByControlRange(controlIdFrom, controlResponse.getId());
    }

    private void loadClients(String codSeller){
        listClients = db.getAllClientsFromSeller(codSeller);
        adapter = new ListClientAdapter(listClients, context, TabClients.this);
        recyclerView.setAdapter(adapter);
    }

    private void getCustomersbySeller(ControlResponse controlResponse, String codSeller) {
        CustomerServicePresenter c = new CustomerServicePresenter(new CustomerServiceInterface() {
            @Override
            public void displayProgressBar() {
                shimmer.startShimmer();
            }

            @Override
            public void hideProgressBar() {
                shimmer.stopShimmer();
            }

            @Override
            public void displayCustomers(List<CustomerResponse> customerResponse) {
                Log.i(TAG, String.valueOf(customerResponse.size()));
                db.createClientBatch(Client.toClientList(customerResponse));
                db.createControl(Control.toControl(controlResponse));
                loadClients(codSeller);
            }

            @Override
            public void displayError(String errorMessage) {
                Log.e(TAG, "ERROR:" + errorMessage);
                loadClients(codSeller);
                shimmer.stopShimmer();
            }
        });
        c.getCustomersBySeller(codSeller);
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

    @OnItemSelected(R.id.spnDays)
    public void spinnerItemSelected(Spinner spinner, int position) {
        if(firstLoadDays){
            firstLoadDays = false;
            return;
        }
        CharSequence days = adapterDays.getItem(position);
        if(days.toString().equals("TODOS")){
            adapter.setFilter(listClients);
        } else {
            List<Client> listClientsFiltered = filterByZona(listClients, days.toString());
            adapter.setFilter(listClientsFiltered);
        }

    }


    private List<Client> filterByZona(List<Client> listClientsFull, String zona){
        List<Client> listClientsFiltered = new ArrayList<>();
        for (Client client: listClientsFull) {
            if(client.getZona().toLowerCase().contains(zona.toLowerCase())){
                listClientsFiltered.add(client);
            }
        }
        return listClientsFiltered;
    }
}