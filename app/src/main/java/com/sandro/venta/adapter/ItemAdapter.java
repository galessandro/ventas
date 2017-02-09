package com.sandro.venta.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sandro.venta.R;
import com.sandro.venta.activity.NewOrderActivity;
import com.sandro.venta.bean.Item;
import com.sandro.venta.util.DecimalDigitsInputFilter;

import java.util.List;

/**
 * Creado por ggranados on 20/11/2015.
 */
public class ItemAdapter extends ArrayAdapter<Item> {

    private List<Item> items;
    private Context mContext;

    private static final String TAG = "ItemAdapter";

    public ItemAdapter(Context mContext, List<Item> items) {
        super(mContext, R.layout.list_order_item, items);
        this.mContext = mContext;
        this.items = items;
    }

    // Add a Client to the adapter
    // Notify observers that the data set has changed
    public void add(Item item) {
        items.add(item);
        notifyDataSetChanged();
    }

    @Override
    public void remove(Item item) {
        items.remove(item);
        notifyDataSetChanged();

    }

    // Clears the list adapter of all items.
    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return items.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Item getItem(int position) {
        return items.get(position);
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

        // Inflate the View for this Client
        // from client_item_layout.xml
        LinearLayout itemLayout = (LinearLayout)
                LayoutInflater.from(mContext).inflate(R.layout.list_order_item, parent, false)
                            .findViewById(R.id.orderItemListLayout);

        // Get the current ToDoItem
        final Item item = getItem(position);

        // Fill in specific Client data
        // Remember that the data that goes in this View
        // corresponds to the user interface elements defined
        // in the layout file
        // Display the id in TextView
        final TextView lblOrderItemCod = (TextView) itemLayout.findViewById(R.id.order_item_product_cod);
        lblOrderItemCod.setText(item.getProduct().getCodProduct());

        final TextView lblOrderItemName = (TextView) itemLayout.findViewById(R.id.order_item_name);
        lblOrderItemName.setText(item.getProduct().getName());

        final EditText txtOrderItemQuantity = (EditText) itemLayout.findViewById(R.id.order_item_quantity);
        txtOrderItemQuantity.setText(String.valueOf(item.getQuantity()));
        txtOrderItemQuantity.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(2)});

        txtOrderItemQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String quantity = txtOrderItemQuantity.getText().toString();
                quantity = quantity.equals("") ? "0" : quantity;
                item.setQuantity(Double.parseDouble(quantity));
                ((NewOrderActivity) mContext).updateTotalOrderAmount();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        final TextView txtOrderItemPrice = (TextView) itemLayout.findViewById(R.id.order_item_price);
        txtOrderItemPrice.setText(String.valueOf(item.getPrice()));

        /*txtOrderItemPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String price = txtOrderItemPrice.getText().toString();
                price = price.equals("") ? "0" : price;
                item.setPrice(Double.parseDouble(price));
                ((NewOrderActivity) mContext).updateTotalOrderAmount();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });*/

        final ImageButton btnOrderItemRemove = (ImageButton) itemLayout.findViewById(R.id.order_item_btn_remove);
        btnOrderItemRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(item);
                ((NewOrderActivity) mContext).updateTotalProducts();
                ((NewOrderActivity) mContext).updateTotalOrderAmount();
            }

        });


        // Return the View you just created
        return itemLayout;

    }

    public Double getTotalAmount(){
        double totalAmount = 0;
        for (Item item : items){
            totalAmount += item.getQuantity() * item.getPrice();
        }
        return totalAmount;
    }

    public List<Item> getItems() {
        return items;
    }

}
