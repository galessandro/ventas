package com.sandro.venta.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.sandro.venta.R;
import com.sandro.venta.bean.Order;
import com.sandro.venta.bean.Product;
import com.sandro.venta.util.DateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Creado por ggranados on 26/11/2015.
 */
public class OrderAdapter extends ArrayAdapter<Order> {

    private List<Order> orders;
    private Context mContext;

    private static final String TAG = "OrderAdapter";

    public OrderAdapter(Context mContext, List<Order> orders) {
        super(mContext, R.layout.list_order_row, orders);
        this.mContext = mContext;
        this.orders = orders;
    }

    // Add a product to the adapter
    // Notify observers that the data set has changed
    public void add(Order order) {
        orders.add(order);
        notifyDataSetChanged();
    }

    @Override
    public void remove(Order order) {
        orders.remove(order);
        notifyDataSetChanged();

    }

    // Clears the list adapter of all items.
    public void clear() {
        orders.clear();
        notifyDataSetChanged();
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return orders.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Order getItem(int position) {
        return orders.get(position);
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

        ViewOrderHolder holder;

        if(convertView == null){
            // Inflate the View for this product from product_item_layout.xml
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_order_row, null)
                    .findViewById(R.id.orderListLayout);

            holder = new ViewOrderHolder();
            holder.orderCod = (TextView) convertView.findViewById(R.id.lblOrderCod);
            holder.orderClient = (TextView) convertView.findViewById(R.id.lblOrderClient);
            holder.orderTotalAmount = (TextView) convertView.findViewById(R.id.lblOrderTotalAmount);
            holder.orderDeliveryDate = (TextView) convertView.findViewById(R.id.lblOrderDeliveryDate);
            convertView.setTag(holder);
        } else {
            holder = (ViewOrderHolder) convertView.getTag();
        }

        // Get the current product
        final Order order = getItem(position);

        holder.orderCod.setText(String.valueOf(order.getCodOrder()));
        holder.orderClient.setText(order.getClient().getBusinessName());
        holder.orderTotalAmount.setText(order.getTotalAmount().toString());
        holder.orderDeliveryDate.setText(DateUtil.getFormatDate(order.getDateDelivery(), DateUtil.datePeruFormat));

        // Return the View you just created
        return convertView;

    }

    private class  ViewOrderHolder {
        TextView orderCod;
        TextView orderClient;
        TextView orderTotalAmount;
        TextView orderDeliveryDate;
    }
}
