package com.sandro.venta.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sandro.venta.R;
import com.sandro.venta.adapter.ListClientAdapter;
import com.sandro.venta.bean.ListClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TabClients extends Fragment {

    @BindView(R.id.rcyViewClients)
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;

    Unbinder unbinder;

    private List<ListClient> lstClients;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab_clients, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        lstClients = new ArrayList<>();

        for (int i = 0; i <= 20; i++){
            ListClient client = new ListClient(
                    "dni " + i,
                    "name " + i
            );
            lstClients.add(client);
        }
        adapter = new ListClientAdapter(lstClients, this.getContext());
        recyclerView.setAdapter(adapter);

        return rootView;
    }
}