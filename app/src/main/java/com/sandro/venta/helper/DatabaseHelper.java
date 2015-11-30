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
    private static final int DATABASE_VERSION = 5;

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

        crearUsuariosDefecto(db);

    }

    public SalesMan validateUser(String user, String pass){
        SalesMan seller = null;
        String selectQuery = "SELECT  * " +
                "FROM " + TABLE_USERS + " " +
                "WHERE " + KEY_USER_USER + " = ? " +
                " AND " + KEY_USER_PASS + " = ?";

        Log.i(TAG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, new String[]{user, pass});

        if(c.moveToFirst()){
            seller = new SalesMan();
            seller.setUser((c.getString(c.getColumnIndex(KEY_USER_USER))));
            seller.setPass((c.getString(c.getColumnIndex(KEY_USER_PASS))));
            seller.setCodSeller((c.getString(c.getColumnIndex(KEY_USER_COD_SELLER))));
            seller.setName((c.getString(c.getColumnIndex(KEY_USER_NAME))));
        }

        return seller;
    }

    private void crearUsuariosDefecto(SQLiteDatabase db) {
        for(SalesMan seller : getDefaultUsers()){
            createUser(seller, db);
        }
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

    private List<SalesMan> getDefaultUsers(){
        List<SalesMan> lstUsers = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            SalesMan user = new SalesMan();
            user.setCodSeller("V00" + (i+1));
            user.setName("Luis" + (i + 1));
            user.setPass("123");
            user.setUser("V00" + (i+1));
            lstUsers.add(user);
        }
        return lstUsers;
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

    public long createUser(SalesMan user, SQLiteDatabase db){
        ContentValues values = new ContentValues();
        values.put(KEY_USER_COD_SELLER, user.getCodSeller());
        values.put(KEY_USER_NAME, user.getName());
        values.put(KEY_USER_USER, user.getUser());
        values.put(KEY_USER_PASS, user.getPass());

        return db.insert(TABLE_USERS, null, values);
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

    public int getMaxCodClient(){
        int maxCodClient = 1;
        String selectQuery = "SELECT  MAX( " +
                KEY_CLIENT_COD_CLIENT + ") AS " +
                KEY_CLIENT_MAX_COD +
                " FROM " + TABLE_CLIENTS + " ";

        Log.i(TAG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c =  db.query(TABLE_CLIENTS, new String[]{"MAX(codclient)"}, null, null, null, null, null);

        if(c.moveToFirst()){
            maxCodClient = c.getInt(0);
        }

        return maxCodClient;
    }

    public int getMaxOrder(){
        int maxOrder = 1;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c =  db.query(TABLE_ORDERS, new String[]{"MAX(codsale)"}, null, null, null, null, null);

        if(c.moveToFirst()){
            maxOrder = c.getInt(0);
        }

        return maxOrder;
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
                product.setPriceOne(c.getDouble(c.getColumnIndex(KEY_PRODUCT_PRICE_ONE)));
                product.setPriceTwo(c.getDouble(c.getColumnIndex(KEY_PRODUCT_PRICE_TWO)));
                product.setBoxBy(c.getInt(c.getColumnIndex(KEY_PRODUCT_BOX_BY)));
                product.setTypeUnit(c.getString(c.getColumnIndex(KEY_PRODUCT_TYPE_UNIT)));
                // adding to client list
                products.add(product);
            } while (c.moveToNext());
        }

        return products;

    }

    public int deleteAllProducts(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_PRODUCTS, null, null);
    }

    public int deleteAllClients(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_CLIENTS, null, null);
    }

    public long createProduct(Product product){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_PRODUCT_COD, product.getCodProduct());
        values.put(KEY_PRODUCT_NAME, product.getName());
        values.put(KEY_PRODUCT_PRICE_ONE, product.getPriceOne());
        values.put(KEY_PRODUCT_PRICE_TWO, product.getPriceTwo());
        values.put(KEY_PRODUCT_BOX_BY, product.getBoxBy());
        values.put(KEY_PRODUCT_TYPE_UNIT, product.getTypeUnit());

        return db.insert(TABLE_PRODUCTS, null, values);
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
        String selectQuery = "SELECT  " +
                "O." + KEY_ORDER_COD_SALE + ", " +
                "O." + KEY_ORDER_COD_ORDER + "," +
                "O." + KEY_ORDER_DATE_AT + "," +
                "O." + KEY_ORDER_DELIVERY_AT + "," +
                "C." + KEY_CLIENT_COD_CLIENT + "," +
                "C." + KEY_CLIENT_FIRST_NAME + "," +
                "C." + KEY_CLIENT_LAST_NAME + "," +
                "U." + KEY_USER_COD_SELLER +
                " FROM " + TABLE_ORDERS +
                " O INNER JOIN " + TABLE_CLIENTS  + " C ON O." + KEY_ORDER_CLIENT_COD + " =" +
                " C." + KEY_CLIENT_COD_CLIENT +
                " INNER JOIN " + TABLE_USERS  + " U ON O." + KEY_ORDER_SELLER_COD + " =" +
                " U." + KEY_USER_COD_SELLER +
                " WHERE O." + KEY_CREATED_AT + " BETWEEN datetime(date('now')||' 00:00:00') AND datetime(date('now')||' 23:59:59')";

        Log.i(TAG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        /*Cursor c = db.rawQuery(selectQuery, new String[]{
                "datetime(date('now')||' 00:00:00')",
                "datetime(date('now')||' 23:59:59')"
        });*/


        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Order order = new Order();
                order.setCodSale(c.getInt(c.getColumnIndex(KEY_ORDER_COD_SALE)));
                order.setCodOrder(c.getInt(c.getColumnIndex(KEY_ORDER_COD_ORDER)));
                order.setDateOrder(DateUtil.getDate(c.getString(
                        c.getColumnIndex(KEY_ORDER_DATE_AT))));
                order.setDateDelivery(DateUtil.getDate(c.getString(
                        c.getColumnIndex(KEY_ORDER_DELIVERY_AT))));
                Client client = new Client();
                client.setCodClient(c.getInt(c.getColumnIndex(KEY_CLIENT_COD_CLIENT)));
                client.setFirstName(c.getString(c.getColumnIndex(KEY_CLIENT_FIRST_NAME)));
                client.setLastName(c.getString(c.getColumnIndex(KEY_CLIENT_LAST_NAME)));
                SalesMan seller = new SalesMan();
                seller.setCodSeller(c.getString(c.getColumnIndex(KEY_USER_COD_SELLER)));
                order.setSeller(seller);
                order.setClient(client);
                order.setItems(getItemsFromOrder(order.getCodSale()));

                orders.add(order);
            } while (c.moveToNext());
        }
        return orders;
    }

    public List<Item> getItemsFromOrder(int codSale) {
        List<Item> items = new ArrayList<>();

        String selectQuery = "SELECT  " +
                "O." + KEY_ORDER_ITEM_QUANTITY + ", " +
                "O." + KEY_ORDER_ITEM_PRICE + "," +
                "O." + KEY_ORDER_ITEM_TYPE_PRICE + "," +
                "P." + KEY_PRODUCT_COD + "," +
                "P." + KEY_PRODUCT_NAME + "," +
                "P." + KEY_PRODUCT_BOX_BY + "," +
                "P." + KEY_PRODUCT_TYPE_UNIT +
                " FROM " +
                TABLE_ORDER_ITEMS +
                " O INNER JOIN " + TABLE_PRODUCTS  + " P ON O." + KEY_ORDER_ITEM_COD_PRODUCT + " = " +
                "P." + KEY_PRODUCT_COD +
                " WHERE O." + KEY_ORDER_COD_SALE + " = ?";

        Log.i(TAG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, new String[]{
                String.valueOf(codSale)
        });

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Item item = new Item();
                item.setQuantity(c.getInt(c.getColumnIndex(KEY_ORDER_ITEM_QUANTITY)));
                item.setPrice(c.getDouble(c.getColumnIndex(KEY_ORDER_ITEM_PRICE)));
                item.setTypePrice(c.getString(c.getColumnIndex(KEY_ORDER_ITEM_TYPE_PRICE)));
                Product product = new Product();
                product.setCodProduct(c.getString(c.getColumnIndex(KEY_PRODUCT_COD)));
                product.setName(c.getString(c.getColumnIndex(KEY_PRODUCT_NAME)));
                product.setBoxBy(c.getInt(c.getColumnIndex(KEY_PRODUCT_BOX_BY)));
                product.setTypeUnit(c.getString(c.getColumnIndex(KEY_PRODUCT_TYPE_UNIT)));
                item.setProduct(product);
                // adding to product list
                items.add(item);
            } while (c.moveToNext());
        }

        return items;

    }
}
