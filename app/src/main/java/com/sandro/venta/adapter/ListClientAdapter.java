package com.sandro.venta.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sandro.venta.R;
import com.sandro.venta.bean.Client;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListClientAdapter extends RecyclerView.Adapter<ListClientAdapter.ViewHolder> {

    List<Client> lstClients;
    Context ctx;
    private OnClientListener mOnClientListener;

    public ListClientAdapter(List<Client> lstClients, Context ctx, OnClientListener onClientListener) {
        this.lstClients = lstClients;
        this.ctx = ctx;
        this.mOnClientListener = onClientListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_client_item_rcy, viewGroup, false);
        return new ViewHolder(v, mOnClientListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        Client client = lstClients.get(i);
        holder.tvDni.setText(client.getDni());
        holder.tvName.setText(client.getBusinessName());
    }

    @Override
    public int getItemCount() {
        return lstClients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.txtItemClientRuc) TextView tvDni;
        @BindView(R.id.txtItemClientBussinnesName) TextView tvName;
        OnClientListener onClientListener;


        public ViewHolder(@NonNull View itemView, OnClientListener onClientListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.onClientListener = onClientListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onClientListener.onClientClick(getAdapterPosition());
        }
    }

    public void setFilter(List<Client> listClients){
        this.lstClients = new ArrayList<>();
        this.lstClients.addAll(listClients);
        notifyDataSetChanged();
    }

    public interface OnClientListener{
        void onClientClick(int position);
    }
}
