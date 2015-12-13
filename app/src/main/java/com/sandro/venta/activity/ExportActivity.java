package com.sandro.venta.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.sandro.venta.R;
import com.sandro.venta.bean.Item;
import com.sandro.venta.bean.Order;
import com.sandro.venta.helper.DatabaseHelper;
import com.sandro.venta.util.DateUtil;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ExportActivity extends AppCompatActivity {

    private static EditText txtOrderDate;
    private Button btnExport;
    private DatabaseHelper db;
    private final String FILE_NAME_ORDER = "ORDERS.TXT";
    private final String FILE_NAME_ITEM = "ITEMS.TXT";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_export);

        db = new DatabaseHelper(this);

        findViewsById();
        setDefaultDate();
        setListeners();
    }

    private void setListeners() {
        btnExport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Order> lstOrders = getOrdersFromDate(
                        DateUtil.getDate(txtOrderDate.getText().toString()));
                if (lstOrders.isEmpty()) {
                    Toast.makeText(ExportActivity.this, R.string.export_result_no_order,
                            Toast.LENGTH_SHORT).show();
                } else {
                    exportOrdersToFile(lstOrders, DateUtil.getDate(txtOrderDate.getText().toString()));
                }

            }
        });

        txtOrderDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialogIni();
            }
        });
    }

    private void exportOrdersToFile(List<Order> lstOrders, Date orderDate) {
        deleteFile(orderDate);
        for (Order order : lstOrders ) {
            saveOrderToFile(order, orderDate);
        }

        Toast.makeText(this, getString(R.string.export_result_success,
                        DateUtil.getFormatDate(orderDate, DateUtil.datePeruFormat)),
                Toast.LENGTH_SHORT).show();
    }

    private List<Order> getOrdersFromDate(Date fechaOrden) {
        return db.getOrdersFromARangeDate(fechaOrden);
    }

    private void setDefaultDate() {
        txtOrderDate.setText(DateUtil.getCurrentDate());
    }

    private void findViewsById() {
        txtOrderDate = (EditText) findViewById(R.id.exportIniDate);
        btnExport = (Button) findViewById(R.id.btnExport);

    }

    private void showDatePickerDialogIni() {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePickerIni");
    }


    public static class DatePickerFragment extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            txtOrderDate.setText(DateUtil.getDateFromDialogDatePicker(year, monthOfYear,
                    dayOfMonth));
        }
    }

    private void deleteFile(Date orderDate){
        File downloadPath =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File fileOrder = new File(downloadPath +
                File.separator +
                DateUtil.getFormatDate(orderDate, DateUtil.dateSimpleFormat) +
                File.separator +
                FILE_NAME_ORDER);

        File fileItem = new File(downloadPath +
                File.separator +
                DateUtil.getFormatDate(orderDate, DateUtil.dateSimpleFormat) +
                File.separator +
                FILE_NAME_ITEM);

        if(fileOrder.exists()){
            fileOrder.delete();
        }

        if(fileItem.exists()){
            fileItem.delete();
        }

    }

    private void saveOrderToFile(Order order, Date orderDate) {
        BufferedWriter bufferedWriter;
        try {
            File downloadPath =
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File folderOrder = new File(downloadPath + File.separator + DateUtil.getFormatDate(
                    orderDate, DateUtil.dateSimpleFormat));
            if(!folderOrder.exists()){
                folderOrder.mkdir();
            }

            File fileOrder = new File(folderOrder,  FILE_NAME_ORDER);
            File fileItem = new File(folderOrder, FILE_NAME_ITEM);

            Date date = new Date();

            StringBuffer sb = new StringBuffer();
            sb.append(StringUtils.leftPad(String.valueOf(order.getCodSale()), 8, "0"))
                    .append(StringUtils.leftPad(String.valueOf(order.getCodOrder()), 4, "0"))
                    .append(DateUtil.getFormatDate(order.getDateOrder(), DateUtil.dateSimpleFormat))
                    .append(StringUtils.leftPad(String.valueOf(order.getClient().getCodClient()), 8, "0"))
                    .append(order.getSeller().getCodSeller().substring(0, 2))
                    .append(DateUtil.getFormatDate(order.getDateDelivery(), DateUtil.dateSimpleFormat))
                    .append(DateUtil.getFormatDate(date, DateUtil.dateSimpleFormat))
                    .append(DateUtil.getFormatDate(date, DateUtil.timeFormat))
                    .append(String.valueOf(order.getPaymentType()));

            bufferedWriter = new BufferedWriter(new FileWriter(fileOrder,true));
            bufferedWriter.write(sb.toString() + "\n");
            bufferedWriter.close();

            bufferedWriter = new BufferedWriter(new FileWriter(fileItem, true));
            for (Item item : order.getItems()) {
                sb = new StringBuffer();
                sb.append(StringUtils.leftPad(String.valueOf(item.getCodSale()), 8, "0"))
                        .append(StringUtils.rightPad(item.getProduct().getCodProduct(), 9, " "))
                        .append(StringUtils.leftPad(String.valueOf(item.getQuantity()), 6, " "))
                        .append(StringUtils.leftPad(String.valueOf(item.getPrice()), 10, " "))
                        .append(item.getTypePrice())
                        .append(StringUtils.leftPad(String.valueOf(
                                item.getProduct().getBoxBy()), 8, "0"))
                        .append(item.getProduct().getTypeUnit())
                        .append(DateUtil.getFormatDate(date, DateUtil.dateSimpleFormat))
                        .append(DateUtil.getFormatDate(date, DateUtil.timeFormat));
                bufferedWriter.write(sb.toString() + "\n");
            }
            bufferedWriter.close();


        } catch (FileNotFoundException e) {
            Toast.makeText(this, getResources().getString(R.string.activity_sync_no_found_file),
                    Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (IOException e){
            Toast.makeText(this, getResources().getString(R.string.activity_sync_no_read_line),
                    Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
