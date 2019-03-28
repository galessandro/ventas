package com.sandro.venta.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sandro.venta.R;
import com.sandro.venta.bean.ListOrder;
import com.sandro.venta.bean.Order;
import com.sandro.venta.util.DateUtil;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListOrderAdapter extends RecyclerView.Adapter<ListOrderAdapter.ViewHolder> {

    List<Order> listOrders;
    Context ctx;
    private OnListOrderListener mOnListOrderListener;
    private DecimalFormat df = new DecimalFormat("#.##");

    public ListOrderAdapter(List<Order> listOrders, Context ctx, OnListOrderListener onListOrderListener) {
        this.listOrders = listOrders;
        this.ctx = ctx;
        this.mOnListOrderListener = onListOrderListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_order_rcy, viewGroup, false);
        return new ViewHolder(v, mOnListOrderListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        Order order = listOrders.get(i);
        holder.tvOrderCod.setText(String.valueOf(order.getCodOrder()));
        holder.tvOrderClient.setText(order.getClient().getBusinessName());
        holder.tvOrderTotalAmount.setText(df.format(order.getTotalAmount()));
        holder.tvOrderDeliveryDate.setText(DateUtil.getFormatDate(order.getDateDelivery(), DateUtil.datePeruFormat));
    }

    @Override
    public int getItemCount() {
        return listOrders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.lblOrderCod) TextView tvOrderCod;
        @BindView(R.id.lblOrderClient) TextView tvOrderClient;
        @BindView(R.id.lblOrderTotalAmount) TextView tvOrderTotalAmount;
        @BindView(R.id.lblOrderDeliveryDate) TextView tvOrderDeliveryDate;
        OnListOrderListener onListOrderListener;


        public ViewHolder(@NonNull View itemView, OnListOrderListener onListOrderListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.onListOrderListener = onListOrderListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onListOrderListener.onListOrderClick(getAdapterPosition());
        }
    }

    public interface OnListOrderListener{
        void onListOrderClick(int position);
    }
}
