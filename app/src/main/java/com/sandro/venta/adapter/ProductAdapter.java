package com.sandro.venta.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.sandro.venta.R;
import com.sandro.venta.bean.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Creado por ggranados on 16/11/2015.
 */
public class ProductAdapter extends ArrayAdapter<Product> {

    private List<Product> filteredProductList;
    private List<Product> originalProductList;
    private Context mContext;

    private static final String TAG = "ProductAdapter";

    public ProductAdapter(Context mContext, List<Product> products) {
        super(mContext, R.layout.list_product_item, products);
        this.mContext = mContext;
        this.filteredProductList = products;
        this.originalProductList = products;
    }

    // Add a product to the adapter
    // Notify observers that the data set has changed
    public void add(Product product) {
        filteredProductList.add(product);
        notifyDataSetChanged();
    }

    @Override
    public void remove(Product product) {
        filteredProductList.remove(product);
        notifyDataSetChanged();

    }

    // Clears the list adapter of all items.
    public void clear() {
        filteredProductList.clear();
        notifyDataSetChanged();
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return filteredProductList.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Product getItem(int position) {
        return filteredProductList.get(position);
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

        ViewProductHolder holder;

        if(convertView == null){
            // Inflate the View for this product from product_item_layout.xml
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_product_item, null)
                    .findViewById(R.id.productItemListLayout);

            holder = new ViewProductHolder();
            holder.productDesc = (TextView) convertView.findViewById(R.id.productDesc);
            convertView.setTag(holder);
        } else {
            holder = (ViewProductHolder) convertView.getTag();
        }

        // Get the current product
        final Product product = getItem(position);

        holder.productDesc.setText(product.getName());

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
                    results.values = originalProductList;
                    results.count = originalProductList.size();
                }
                else {
                    // We perform filtering operation
                    List<Product> nProductList = new ArrayList<>();

                    for (Product product : originalProductList) {

                        if (product.getName().toUpperCase().contains(constraint.toString().toUpperCase()))
                            nProductList.add(product);
                    }

                    results.values = nProductList;
                    results.count = nProductList.size();

                }
                return results;

            }


            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                // Now we have to inform the adapter about the new list filtered
                filteredProductList = (List<Product>) results.values;
                notifyDataSetChanged();

            }
        };
    }

    private class  ViewProductHolder {
        TextView productDesc;
    }
}
