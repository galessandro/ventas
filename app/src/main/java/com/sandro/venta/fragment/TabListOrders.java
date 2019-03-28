package com.sandro.venta.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sandro.venta.R;
import com.sandro.venta.activity.ViewOrderDetailActivity;
import com.sandro.venta.adapter.ListClientAdapter;
import com.sandro.venta.adapter.ListOrderAdapter;
import com.sandro.venta.bean.Client;
import com.sandro.venta.bean.ListClient;
import com.sandro.venta.bean.ListOrder;
import com.sandro.venta.bean.Order;
import com.sandro.venta.bean.SalesMan;
import com.sandro.venta.helper.DatabaseHelper;
import com.sandro.venta.util.DateUtil;
import com.sandro.venta.util.SessionManager;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TabListOrders extends Fragment implements ListOrderAdapter.OnListOrderListener {

    @BindView(R.id.rcyViewListOrders)
    RecyclerView recyclerView;
    ListOrderAdapter adapter;

    Unbinder unbinder;

    private List<Order> listOrders;

    DatabaseHelper db;
    private SessionManager session;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab_list_orders, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        session = new SessionManager(this.getContext());
        SalesMan salesMan = session.getUserDetails();

        db = new DatabaseHelper(this.getContext());

        listOrders = db.getOrdersFromToday(salesMan.getCodSeller());

        adapter = new ListOrderAdapter(listOrders, this.getContext(), this);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onListOrderClick(int position) {
        Order order = listOrders.get(position);
        viewOrderDetail(order);
    }

    private void viewOrderDetail(Order order) {
        Intent intent = new Intent(this.getContext(), ViewOrderDetailActivity.class);
        intent.putExtra("order", order);
        startActivity(intent);
    }
}