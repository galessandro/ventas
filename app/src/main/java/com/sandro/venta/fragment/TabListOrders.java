package com.sandro.venta.fragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sandro.venta.R;
import com.sandro.venta.activity.ViewOrderDetailActivity;
import com.sandro.venta.adapter.ListClientAdapter;
import com.sandro.venta.adapter.ListOrderAdapter;
import com.sandro.venta.api.RetrofitClient;
import com.sandro.venta.api.model.OrderPost;
import com.sandro.venta.api.service.PostOrderService;
import com.sandro.venta.bean.Client;
import com.sandro.venta.bean.ListClient;
import com.sandro.venta.bean.ListOrder;
import com.sandro.venta.bean.Order;
import com.sandro.venta.bean.SalesMan;
import com.sandro.venta.helper.DatabaseHelper;
import com.sandro.venta.util.Constants;
import com.sandro.venta.util.DateUtil;
import com.sandro.venta.util.SessionManager;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TabListOrders extends Fragment implements ListOrderAdapter.OnListOrderListener {

    @BindView(R.id.rcyViewListOrders)
    RecyclerView recyclerView;
    ListOrderAdapter adapter;

    Unbinder unbinder;

    private List<Order> listOrders;

    DatabaseHelper db;
    private SessionManager session;
    private SalesMan salesMan;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab_list_orders, container, false);
        setHasOptionsMenu(true);
        unbinder = ButterKnife.bind(this, rootView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        session = new SessionManager(this.getContext());
        salesMan = session.getUserDetails();

        db = new DatabaseHelper(this.getContext());

        List<Order> listOrdersTmp = db.getOrdersFromToday(salesMan.getId());
        removeZeroOrders(listOrdersTmp);

        adapter = new ListOrderAdapter(listOrders, this.getContext(), this);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    private void removeZeroOrders(List<Order> listOrdersTmp) {
        listOrders = new ArrayList<>();
        for (Order order: listOrdersTmp) {
            if(order.getTotalAmount() > 0d){
                listOrders.add(order);
            }
        }
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.tab_listorders_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.order_sync:
                new AlertDialog.Builder(this.getActivity())
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle(getResources().getString(R.string.order_menu_sync))
                        .setMessage(getResources().getString(R.string.order_sync_msg))
                        .setPositiveButton(
                                getResources().getString(R.string.order_warning_save_accept),
                                (dialog, which) -> verificarOrdenesPendientes())
                        .setNegativeButton(
                                getResources().getString(R.string.order_warning_save_cancel),
                                null)
                        .show();
                return true;
        }
        return false;
    }

    public void verificarOrdenesPendientes(){
        int pendingOrder = db.countPendingOrder();
        if (pendingOrder > 0){
            sincronizarOrdenes();
        } else {
            Toast.makeText(getContext(), "No hay ordenes pendientes de sincronizar", Toast.LENGTH_SHORT).show();
        }
    }

    public void sincronizarOrdenes(){
        List<Order> orders = db.getPendingOrders();
        if(orders != null && orders.size() > 0){
            saveOrderNube(orders);
        }
    }

    private void saveOrderNube(List<Order> orders) {
        PostOrderService orderService = RetrofitClient.getRetrofitClient().create(PostOrderService.class);
        //Call<OrderPost> call = orderService.createOrder(order.toPostOrder());
        List<OrderPost> orderPostList = new ArrayList<>();
        for (Order order: orders) {
            OrderPost orderPost = order.toPostOrder();
            orderPostList.add(orderPost);
        }
        Call<List<OrderPost>> call = orderService.createOrders(orderPostList);
        call.enqueue(new Callback<List<OrderPost>>() {
            @Override
            public void onResponse(Call<List<OrderPost>> call, Response<List<OrderPost>> response) {
                if(response.isSuccessful()){
                    Log.i("GGRANADOS","success save");
                    List<OrderPost> resultList = response.body();
                    for (OrderPost resultPost: resultList) {
                        if(resultPost.getId()!=null && resultPost.getId()>0){
                            Order order = new Order();
                            order.setId(resultPost.getAppId());
                            order.setFlagCloud(Constants.CLOUD_SAVE_SUCCESS);
                            db.updateOrder(order);
                        }
                        Log.i("GGRANADOS", resultPost.toString());
                    }
                    listOrders = db.getOrdersFromToday(salesMan.getId());
                    adapter = new ListOrderAdapter(listOrders, TabListOrders.this.getContext(), TabListOrders.this);
                    recyclerView.setAdapter(adapter);
                    Toast.makeText(TabListOrders.this.getActivity(), "exito", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("GGRANADOS", "failed save:" + response.errorBody());
                    Toast.makeText(TabListOrders.this.getActivity(), "failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<OrderPost>> call, Throwable t) {
                Log.e("GGRANADOS", "failure:" + t.toString());
                Toast.makeText(TabListOrders.this.getActivity(), "failure", Toast.LENGTH_SHORT).show();
            }
        });
        /*call.enqueue(new Callback<OrderPost>() {
            @Override
            public void onResponse(Call<OrderPost> call, Response<OrderPost> response) {
                if(response.isSuccessful()){
                    Log.i("GGRANADOS","success save");
                    orders.setFlagCloud(Constants.CLOUD_SAVE_SUCCESS);
                    db.updateOrder(orders);
                    Toast.makeText(TabListOrders.this.getActivity(), "exito", Toast.LENGTH_SHORT).show();
                } else {
                    Log.i("GGRANADOS", "failed save:" + response.errorBody());
                    Toast.makeText(TabListOrders.this.getActivity(), "failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OrderPost> call, Throwable t) {
                Log.i("GGRANADOS", "failed:" + t.toString());
                Toast.makeText(TabListOrders.this.getActivity(), "failure", Toast.LENGTH_SHORT).show();
            }
        });*/
    }

}