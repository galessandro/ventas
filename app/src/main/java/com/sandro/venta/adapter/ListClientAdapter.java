package com.sandro.venta.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sandro.venta.R;
import com.sandro.venta.bean.ListClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListClientAdapter extends RecyclerView.Adapter<ListClientAdapter.ViewHolder> {

    List<ListClient> lstClients;
    Context ctx;

    public ListClientAdapter(List<ListClient> lstClients, Context ctx) {
        this.lstClients = lstClients;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_client_item_rcy, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        ListClient client = lstClients.get(i);
        holder.tvDni.setText(client.getDni());
        holder.tvName.setText(client.getName());
    }

    @Override
    public int getItemCount() {
        return lstClients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.txtItemClientRuc) TextView tvDni;
        @BindView(R.id.txtItemClientBussinnesName) TextView tvName;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setFilter(List<ListClient> listClients){
        this.lstClients = new ArrayList<>();
        this.lstClients.addAll(listClients);
        notifyDataSetChanged();
    }
}
