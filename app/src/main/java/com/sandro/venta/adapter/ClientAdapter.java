package com.sandro.venta.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sandro.venta.R;
import com.sandro.venta.bean.Client;

import java.util.ArrayList;
import java.util.List;

/**
 * Creado by chambo on 25/10/2015.
 */
public class ClientAdapter extends ArrayAdapter<Client> {

    private List<Client> filteredClientList;
    private List<Client> originalClientList;
    private Context mContext;
    private static final String TAG = "ClientAdapter";

    public ClientAdapter(Context mContext, List<Client> clients) {
        super(mContext, R.layout.list_client_item, clients);
        this.mContext = mContext;
        this.filteredClientList = clients;
        this.originalClientList = clients;
    }

    // Add a Client to the adapter
    // Notify observers that the data set has changed
    public void add(Client client) {
        filteredClientList.add(client);
        notifyDataSetChanged();
    }

    @Override
    public void remove(Client client) {
        filteredClientList.remove(client);
        notifyDataSetChanged();

    }

    // Clears the list adapter of all items.
    public void clear() {
        filteredClientList.clear();
        notifyDataSetChanged();
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return filteredClientList.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Client getItem(int position) {
        return filteredClientList.get(position);
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewClientHolder holder;

        if(convertView == null){
            // Get the current ToDoItem


            // Inflate the View for this Client
            // from client_item_layout.xml
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_client_item, null)
                            .findViewById(R.id.clientItemListLayout);

            holder = new ViewClientHolder();

            // Fill in specific Client data
            // Remember that the data that goes in this View
            // corresponds to the user interface elements defined
            // in the layout file
            // Display the id in TextView
            holder.clientItemName = (TextView)
                    convertView.findViewById(R.id.txtItemClientBussinnesName);
            holder.clientItemRuc = (TextView)
                    convertView.findViewById(R.id.txtItemClientRuc);

            convertView.setTag(holder);
        } else {
            holder = (ViewClientHolder) convertView.getTag();
        }

        final Client client = getItem(position);
        holder.clientItemName.setText(client.getBusinessName());
        holder.clientItemRuc.setText(mContext.getResources().getString(R.string.activity_client_lbl_ruc) + client.getRuc());


        // Return the View you just created
        return convertView;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults results = new FilterResults();

                // We implement here the filter logic
                if (constraint == null || constraint.length() == 0) {
                    // No filter implemented we return all the list
                    results.values = originalClientList;
                    results.count = originalClientList.size();
                }
                else {
                    // We perform filtering operation
                    List<Client> nClientList = new ArrayList<>();

                    for (Client client : originalClientList) {

                        if (client.getRuc().contains(constraint.toString()) ||
                                client.getFirstName().toUpperCase().contains(constraint.toString().toUpperCase()) ||
                                client.getLastName().toUpperCase().contains(constraint.toString().toUpperCase()))
                            nClientList.add(client);
                    }

                    results.values = nClientList;
                    results.count = nClientList.size();

                }
                return results;

            }


            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                // Now we have to inform the adapter about the new list filtered
                filteredClientList = (List<Client>) results.values;
                notifyDataSetChanged();

            }
        };
    }

    private class  ViewClientHolder {
        TextView clientItemName;
        TextView clientItemRuc;
    }
}
