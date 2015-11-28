package com.sandro.venta.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.sandro.venta.bean.Client;
import com.sandro.venta.bean.Item;
import com.sandro.venta.bean.Order;
import com.sandro.venta.bean.Product;
import com.sandro.venta.bean.SalesMan;
import com.sandro.venta.util.DateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Creado by gg on 04/11/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 3;

    // Database Name
    private static final String DATABASE_NAME = "ventas";

    // Table Names
    private static final String TABLE_CLIENTS = "clients";
    private static final String TABLE_ORDERS = "orders";
    private static final String TABLE_ORDER_ITEMS= "orders_item";
    private static final String TABLE_PRODUCTS = "products";
    private static final String TABLE_USERS = "users";

    // Common column names
    private static final String KEY_CREATED_AT = "createdat"; //16

    // Clients Table - column names
    private static final String KEY_CLIENT_ID = "id";
    private static final String KEY_CLIENT_COD_CLIENT = "codclient";//4
    private static final String KEY_CLIENT_FIRST_NAME = "firstname"; //45
    private static final String KEY_CLIENT_LAST_NAME = "lastname"; //45
    private static final String KEY_CLIENT_ADDRESS = "address"; //80
    private static final String KEY_CLIENT_RUC = "ruc";//11
    private static final String KEY_CLIENT_DNI = "dni";//8
    private static final String KEY_CLIENT_MAX_COD = "maxcodclient";//8

    // Orders Table - column names
    private static final String KEY_ORDER_ID = "id";
    private static final String KEY_ORDER_COD_SALE = "codsale"; //8
    private static final String KEY_ORDER_COD_ORDER = "codorder"; //4
    private static final String KEY_ORDER_DATE_AT = "dateorder";//8
    private static final String KEY_ORDER_CLIENT_COD = "codclient";//4
    private static final String KEY_ORDER_SELLER_COD = "codseller";//2
    private static final String KEY_ORDER_DELIVERY_AT = "datedelivery";//8

    // Orders item Table - column names
    private static final String KEY_ORDER_ITEM_ID = "id";
    private static final String KEY_ORDER_ITEM_COD_SALE = "codsale"; //8
    private static final String KEY_ORDER_ITEM_COD_PRODUCT = "codproduct";//9
    private static final String KEY_ORDER_ITEM_QUANTITY = "quantity";//6
    private static final String KEY_ORDER_ITEM_PRICE = "price"; //10
    private static final String KEY_ORDER_ITEM_TYPE_UNIT = "typeunit"; //1
    private static final String KEY_ORDER_ITEM_BOX_BY = "boxby"; //8
    private static final String KEY_ORDER_ITEM_TYPE_PRICE = "typeprice"; //1

    // Product Table - column names
    private static final String KEY_PRODUCT_ID = "id";
    private static final String KEY_PRODUCT_COD = "codproduct"; //9
    private static final String KEY_PRODUCT_NAME = "name"; //80
    private static final String KEY_PRODUCT_PRICE_ONE = "priceone"; //10
    private static final String KEY_PRODUCT_PRICE_TWO = "pricetwo"; //10
    private static final String KEY_PRODUCT_BOX_BY = "boxby"; //8
    private static final String KEY_PRODUCT_TYPE_UNIT = "typeunit"; //1

    // User Table - column names
    private static final String KEY_USER_ID = "id";
    private static final String KEY_USER_USER = "user";
    private static final String KEY_USER_PASS = "pass";
    private static final String KEY_USER_COD_SELLER = "codseller";
    private static final String KEY_USER_NAME = "name";

    // Table Create Statements
    private static final String CREATE_TABLE_CLIENTS = "CREATE TABLE "
            + TABLE_CLIENTS + "(" +
            KEY_CLIENT_ID + " INTEGER PRIMARY KEY," +
            KEY_CLIENT_COD_CLIENT + " INTEGER UNIQUE, " +
            KEY_CLIENT_RUC + " TEXT," +
            KEY_CLIENT_DNI + " TEXT," +
            KEY_CLIENT_FIRST_NAME + " TEXT," +
            KEY_CLIENT_LAST_NAME + " TEXT, " +
            KEY_CLIENT_ADDRESS + " TEXT," +
            KEY_CREATED_AT + " DATETIME DEFAULT CURRENT_TIMESTAMP)";

    private static final String CREATE_TABLE_ORDERS = "CREATE TABLE "
            + TABLE_ORDERS + "(" +
            KEY_ORDER_ID + " INTEGER PRIMARY KEY," +
            KEY_ORDER_COD_SALE + " INTEGER, " +
            KEY_ORDER_COD_ORDER + " INTEGER," +
            KEY_ORDER_DATE_AT + " DATETIME DEFAULT CURRENT_TIMESTAMP, " +
            KEY_ORDER_CLIENT_COD + " TEXT," +
            KEY_ORDER_SELLER_COD + " TEXT," +
            KEY_ORDER_DELIVERY_AT + " DATETIME DEFAULT CURRENT_TIMESTAMP," +
            KEY_CREATED_AT + " DATETIME DEFAULT CURRENT_TIMESTAMP)";

    private static final String CREATE_TABLE_ORDERS_ITEM = "CREATE TABLE "
            + TABLE_ORDER_ITEMS + "(" +
            KEY_ORDER_ITEM_ID + " INTEGER PRIMARY KEY," +
            KEY_ORDER_ITEM_COD_SALE + " INTEGER, " +
            KEY_ORDER_ITEM_COD_PRODUCT + " TEXT," +
            KEY_ORDER_ITEM_QUANTITY + " INTEGER, " +
            KEY_ORDER_ITEM_PRICE + " REAL, " +
            KEY_ORDER_ITEM_TYPE_UNIT + " TEXT," +
            KEY_ORDER_ITEM_BOX_BY + " INTEGER," +
            KEY_ORDER_ITEM_TYPE_PRICE + " TEXT," +
            KEY_CREATED_AT + " DATETIME DEFAULT CURRENT_TIMESTAMP)";

    private static final String CREATE_TABLE_PRODUCTS = "CREATE TABLE "
            + TABLE_PRODUCTS + "(" +
            KEY_PRODUCT_ID + " INTEGER PRIMARY KEY," +
            KEY_PRODUCT_COD + " TEXT, " +
            KEY_PRODUCT_NAME + " TEXT, " +
            KEY_PRODUCT_PRICE_ONE + " REAL," +
            KEY_PRODUCT_PRICE_TWO + " REAL, " +
            KEY_PRODUCT_BOX_BY + " INTEGER, " +
            KEY_PRODUCT_TYPE_UNIT + " TEXT" + ")";


    private static final String CREATE_TABLE_USERS = "CREATE TABLE "
            + TABLE_USERS + "(" +
            KEY_USER_ID + " INTEGER PRIMARY KEY," +
            KEY_USER_USER + " TEXT, " +
            KEY_USER_PASS + " TEXT, " +
            KEY_USER_COD_SELLER + " TEXT," +
            KEY_USER_NAME + " TEXT)";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_CLIENTS);
        db.execSQL(CREATE_TABLE_PRODUCTS);
        db.execSQL(CREATE_TABLE_ORDERS);
        db.execSQL(CREATE_TABLE_ORDERS_ITEM);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLIENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDER_ITEMS);

        // create new tables
        onCreate(db);
    }


    public long createClient(Client client){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_CLIENT_COD_CLIENT, client.getCodClient());
        values.put(KEY_CLIENT_FIRST_NAME, client.getFirstName());
        values.put(KEY_CLIENT_LAST_NAME, client.getLastName());
        values.put(KEY_CLIENT_ADDRESS, client.getAddress());
        values.put(KEY_CLIENT_RUC, client.getRuc());
        values.put(KEY_CLIENT_DNI, client.getDni());
        values.put(KEY_CREATED_AT, DateUtil.getCurrentDateTime());

        return db.insert(TABLE_CLIENTS, null, values);
    }

    public Client getClient(int id){
        Client client = null;
        String selectQuery = "SELECT  * " +
                "FROM " + TABLE_CLIENTS + " " +
                "WHERE " + KEY_CLIENT_ID + " = ? ";

        Log.i(TAG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, new String[]{id + ""});

        if(c.moveToFirst()){
            client = new Client();
            client.setId(c.getInt(c.getColumnIndex(KEY_CLIENT_ID)));
            client.setCodClient(c.getInt(c.getColumnIndex(KEY_CLIENT_COD_CLIENT)));
            client.setFirstName((c.getString(c.getColumnIndex(KEY_CLIENT_FIRST_NAME))));
            client.setLastName(c.getString(c.getColumnIndex(KEY_CLIENT_LAST_NAME)));
            client.setAddress((c.getString(c.getColumnIndex(KEY_CLIENT_ADDRESS))));
            client.setRuc((c.getString(c.getColumnIndex(KEY_CLIENT_RUC))));
            client.setDni((c.getString(c.getColumnIndex(KEY_CLIENT_DNI))));
            client.setDateReg(DateUtil.getDateTime(c.getString(c.getColumnIndex(KEY_CREATED_AT))));
        }

        return client;
    }

    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_CLIENTS;

        Log.i(TAG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Client client = new Client();
                client.setId(c.getInt(c.getColumnIndex(KEY_CLIENT_ID)));
                client.setCodClient(c.getInt(c.getColumnIndex(KEY_CLIENT_COD_CLIENT)));
                client.setFirstName((c.getString(c.getColumnIndex(KEY_CLIENT_FIRST_NAME))));
                client.setLastName(c.getString(c.getColumnIndex(KEY_CLIENT_LAST_NAME)));
                client.setAddress((c.getString(c.getColumnIndex(KEY_CLIENT_ADDRESS))));
                client.setRuc((c.getString(c.getColumnIndex(KEY_CLIENT_RUC))));
                client.setDni((c.getString(c.getColumnIndex(KEY_CLIENT_DNI))));
                client.setDateReg(DateUtil.getDateTime(c.getString(c.getColumnIndex(KEY_CREATED_AT))));

                // adding to client list
                clients.add(client);
            } while (c.moveToNext());
        }
        return clients;
    }

    public List<Client> getClientsFilterBySearch(String searchFilter) {
        List<Client> clients = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_CLIENTS +
                " WHERE " + KEY_CLIENT_RUC + " LIKE ? " +
                " OR " + KEY_CLIENT_FIRST_NAME + " LIKE ? " +
                " OR " + KEY_CLIENT_LAST_NAME + " LIKE ?";

        Log.i(TAG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, new String[]{
                "%" +searchFilter + "%",
                "%" +searchFilter + "%",
                "%" +searchFilter + "%"});

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Client client = new Client();
                client.setId(c.getInt(c.getColumnIndex(KEY_CLIENT_ID)));
                client.setCodClient(c.getInt(c.getColumnIndex(KEY_CLIENT_COD_CLIENT)));
                client.setFirstName((c.getString(c.getColumnIndex(KEY_CLIENT_FIRST_NAME))));
                client.setLastName(c.getString(c.getColumnIndex(KEY_CLIENT_LAST_NAME)));
                client.setAddress((c.getString(c.getColumnIndex(KEY_CLIENT_ADDRESS))));
                client.setRuc((c.getString(c.getColumnIndex(KEY_CLIENT_RUC))));
                client.setDni((c.getString(c.getColumnIndex(KEY_CLIENT_DNI))));
                client.setDateReg(DateUtil.getDateTime(c.getString(c.getColumnIndex(KEY_CREATED_AT))));

                // adding to client list
                clients.add(client);
            } while (c.moveToNext());
        }
        return clients;
    }

    public int getMaxCodClient(){
        int maxCodClient = 1;
        String selectQuery = "SELECT  MAX( " +
                KEY_CLIENT_COD_CLIENT + ") AS " +
                KEY_CLIENT_MAX_COD +
                " FROM " + TABLE_CLIENTS + " ";

        Log.i(TAG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        //Cursor c = db.rawQuery(selectQuery, null);

        Cursor c =  db.query(TABLE_CLIENTS, new String [] {"MAX(codclient)"}, null, null, null, null, null);

        if(c.moveToFirst()){
            maxCodClient = c.getInt(0);
        }

        return maxCodClient;
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_PRODUCTS;

        Log.i(TAG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Product product = new Product();
                product.setId(c.getInt(c.getColumnIndex(KEY_PRODUCT_ID)));
                product.setCodProduct(c.getString(c.getColumnIndex(KEY_PRODUCT_COD)));
                product.setName(c.getString(c.getColumnIndex(KEY_PRODUCT_NAME)));
                //product.setPriceOne(c.getDouble(c.getColumnIndex(KEY_PRODUCT_PRICE_ONE)));
                //product.setPriceTwo(c.getDouble(c.getColumnIndex(KEY_PRODUCT_PRICE_TWO)));
                product.setBoxBy(c.getInt(c.getColumnIndex(KEY_PRODUCT_BOX_BY)));
                product.setTypeUnit(c.getString(c.getColumnIndex(KEY_PRODUCT_TYPE_UNIT)));
                // adding to client list
                products.add(product);
            } while (c.moveToNext());
        }

        return products;

    }

    public long createOrder(Order order){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ORDER_COD_SALE, order.getCodSale());
        values.put(KEY_ORDER_COD_ORDER, order.getCodOrder());
        values.put(KEY_ORDER_DATE_AT, DateUtil.getFormatDate(order.getDateOrder()));
        values.put(KEY_ORDER_CLIENT_COD, order.getClient().getCodClient());
        values.put(KEY_ORDER_SELLER_COD, order.getSeller().getCodSeller());
        values.put(KEY_ORDER_DELIVERY_AT, DateUtil.getFormatDate(order.getDateDelivery()));
        values.put(KEY_CREATED_AT, DateUtil.getCurrentDateTime());

        return db.insert(TABLE_ORDERS, null, values);
    }

    public long createOrderItem(Item item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ORDER_ITEM_COD_SALE, item.getCodSale());
        values.put(KEY_ORDER_ITEM_COD_PRODUCT, item.getProduct().getCodProduct());
        values.put(KEY_ORDER_ITEM_QUANTITY, item.getQuantity());
        values.put(KEY_ORDER_ITEM_PRICE, item.getProduct().getPriceOne().doubleValue());
        values.put(KEY_ORDER_ITEM_TYPE_UNIT, item.getProduct().getTypeUnit());
        values.put(KEY_ORDER_ITEM_BOX_BY, item.getProduct().getBoxBy());
        values.put(KEY_ORDER_ITEM_TYPE_PRICE, item.getTypePrice());
        values.put(KEY_CREATED_AT, DateUtil.getCurrentDateTime());

        return db.insert(TABLE_ORDER_ITEMS, null, values);
    }

    public List<Order> getOrdersFromToday(){
        List<Order> orders = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " +
                TABLE_ORDERS +
                " INNER JOIN " + TABLE_CLIENTS  + " ON "  +
                TABLE_ORDERS + "." + KEY_ORDER_CLIENT_COD + " = " +
                TABLE_CLIENTS + "." + KEY_CLIENT_COD_CLIENT +
                " INNER JOIN " + TABLE_USERS  + " ON "  +
                TABLE_ORDERS + "." + KEY_ORDER_SELLER_COD + " = " +
                TABLE_USERS + "." + KEY_USER_COD_SELLER +
                " INNER JOIN " + TABLE_ORDER_ITEMS + " ON "  +
                TABLE_ORDERS + "." + KEY_ORDER_COD_SALE + " = " +
                TABLE_ORDER_ITEMS + "." + KEY_ORDER_ITEM_COD_SALE +
                " INNER JOIN " + TABLE_PRODUCTS + " ON "  +
                TABLE_ORDER_ITEMS + "." + KEY_ORDER_ITEM_COD_PRODUCT + " = " +
                TABLE_PRODUCTS + "." + KEY_PRODUCT_COD +
                " WHERE " + TABLE_ORDERS + "";

        Log.i(TAG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Order order = new Order();
                Client client = new Client();
                List<Item> lstItems = new ArrayList<>();
                Item item = new Item();
                lstItems.add(item);
                order.setClient(client);
                order.setItems(lstItems);
                SalesMan seller = new SalesMan();
                order.setSeller(seller);
                orders.add(order);
            } while (c.moveToNext());
        }
        return orders;
    }
}
