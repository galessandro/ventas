package com.sandro.venta.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;

import com.sandro.venta.R;
import com.sandro.venta.activity.SyncActivity;
import com.sandro.venta.bean.Sync;

import java.util.List;

/**
 * Creado por chambito on 28/11/15.
 */
public class SyncAdapter extends ArrayAdapter<Sync> {
    private List<Sync> syncs;
    private Context mContext;

    private static final String TAG = "SyncAdapter";

    public SyncAdapter(Context mContext, List<Sync> syncs) {
        super(mContext, R.layout.list_sync_item, syncs);
        this.mContext = mContext;
        this.syncs = syncs;
    }

    // Add a product to the adapter
    // Notify observers that the data set has changed
    public void add(Sync sync) {
        syncs.add(sync);
        notifyDataSetChanged();
    }

    @Override
    public void remove(Sync order) {
        syncs.remove(order);
        notifyDataSetChanged();

    }

    // Clears the list adapter of all items.
    public void clear() {
        syncs.clear();
        notifyDataSetChanged();
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return syncs.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Sync getItem(int position) {
        return syncs.get(position);
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewSyncHolder holder;

        if(convertView == null){
            // Inflate the View for this product from product_item_layout.xml
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_sync_item, parent,
                    false).findViewById(R.id.listRealSyncItems);

            holder = new ViewSyncHolder();
            holder.syncName = (CheckedTextView) convertView.findViewById(R.id.chkSyncItem);
            convertView.setTag(holder);
        } else {
            holder = (ViewSyncHolder) convertView.getTag();
        }

        // Get the current product
        final Sync sync = getItem(position);

        holder.syncName.setText(sync.getName());
        //holder.syncName.setSelected(mCheckedItem == position ? true : false);
        //holder.syncName.setCheckMarkDrawable(position == mCheckedItem ? R.drawable.btn_radio_off_holo_light
         //       : R.drawable.btn_radio_on_holo_light);

        // Return the View you just created
        return convertView;
    }



    private class  ViewSyncHolder {
        CheckedTextView syncName;
    }
}
