package com.sandro.venta.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.sandro.venta.bean.Client;
import com.sandro.venta.bean.Control;
import com.sandro.venta.bean.Item;
import com.sandro.venta.bean.Order;
import com.sandro.venta.bean.PriceLevel;
import com.sandro.venta.bean.Product;
import com.sandro.venta.bean.SalesMan;
import com.sandro.venta.util.DateUtil;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Creado by gg on 04/11/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 30;

    // Database Name
    private static final String DATABASE_NAME = "ventas";

    // Table Names
    private static final String TABLE_CLIENTS = "clients";
    private static final String TABLE_ORDERS = "orders";
    private static final String TABLE_ORDER_ITEMS= "orders_item";
    private static final String TABLE_PRODUCTS = "products";
    private static final String TABLE_USERS = "users";
    private static final String TABLE_CONTROLS = "controls";

    // Common column names
    private static final String KEY_CREATED_AT = "createdat"; //16

    // Clients Table - column names
    private static final String KEY_CLIENT_ID = "id";
    private static final String KEY_CLIENT_COD_CLIENT = "codclient";
    private static final String KEY_CLIENT_FIRST_NAME = "firstname";
    private static final String KEY_CLIENT_LAST_NAME = "lastname";
    private static final String KEY_CLIENT_ADDRESS = "address";
    private static final String KEY_CLIENT_RUC = "ruc";
    private static final String KEY_CLIENT_DNI = "dni";
    private static final String KEY_CLIENT_COD_SELLER = "codseller";
    private static final String KEY_CLIENT_MAX_COD = "maxcodclient";
    private static final String KEY_CLIENT_SEMAPHORE = "semaphore";
    private static final String KEY_CLIENT_DOCUMENTO = "documento";
    private static final String KEY_CLIENT_FULL_NAME = "fullname";
    private static final String KEY_CLIENT_COD_ENTIDAD = "cod_entidad";
    private static final String KEY_CLIENT_UBIGEO = "ubigeo";
    private static final String KEY_CLIENT_ZONA = "zona";

    // Orders Table - column names
    private static final String KEY_ORDER_ID = "id";
    private static final String KEY_ORDER_DATE_AT = "dateorder";
    private static final String KEY_ORDER_CLIENT_COD = "codclient";
    private static final String KEY_ORDER_SELLER_COD = "codseller";
    private static final String KEY_ORDER_DELIVERY_AT = "datedelivery";
    private static final String KEY_ORDER_PAYMENT_TYPE = "paymenttype";
    private static final String KEY_ORDER_PAYMENT_VOUCHER_TYPE = "paymentVouchertype";
    private static final String KEY_ORDER_IMEI = "imei";
    private static final String KEY_ORDER_SEMAPHORE = "semaphore";
    private static final String KEY_ORDER_LATITUDE = "latitude";
    private static final String KEY_ORDER_LONGITUDE = "longitude";
    private static final String KEY_ORDER_FLAG_CLOUD = "flagcloud";
    private static final String KEY_ORDER_COD_ORDER_INTERNA = "ordeninterna";


    // Orders item Table - column names
    private static final String KEY_ORDER_ITEM_ID = "id";
    private static final String KEY_ORDER_ITEM_ORDER_ID = "order_id";
    private static final String KEY_ORDER_ITEM_COD_PRODUCT = "codproduct";
    private static final String KEY_ORDER_ITEM_QUANTITY = "quantity";
    private static final String KEY_ORDER_ITEM_PRICE = "price";
    private static final String KEY_ORDER_ITEM_TYPE_UNIT = "typeunit";
    private static final String KEY_ORDER_ITEM_BOX_BY = "boxby";
    private static final String KEY_ORDER_ITEM_TYPE_PRICE = "typeprice";
    private static final String KEY_ORDER_ITEM_PRICE_LIST = "pricetlist";
    private static final String KEY_ORDER_ITEM_PRODUCT_COD_LEVEL = "codlevel";
    private static final String KEY_ORDER_ITEM_PRODUCT_LEVEL_RANGE_FROM = "levelrangefrom";
    private static final String KEY_ORDER_ITEM_PRODUCT_LEVEL_RANGE_TO = "levelrangeto";

    // ProductResponse Table - column names
    private static final String KEY_PRODUCT_ID = "id";
    private static final String KEY_PRODUCT_COD = "codproduct";
    private static final String KEY_PRODUCT_NAME = "name";
    private static final String KEY_PRODUCT_PRICE_ONE = "priceone";
    private static final String KEY_PRODUCT_PRICE_TWO = "pricetwo";
    private static final String KEY_PRODUCT_PRICE_THREE = "pricethree";
    private static final String KEY_PRODUCT_PRICE_RANGE_NAME_ONE = "pricerangenameone";
    private static final String KEY_PRODUCT_PRICE_RANGE_FROM_ONE = "pricerangefromone";
    private static final String KEY_PRODUCT_PRICE_RANGE_TO_ONE = "pricerangetoone";
    private static final String KEY_PRODUCT_PRICE_VALUE_FROM_ONE = "pricevaluefromone";
    private static final String KEY_PRODUCT_PRICE_VALUE_TO_ONE = "pricevaluetoone";
    private static final String KEY_PRODUCT_PRICE_RANGE_NAME_TWO = "pricerangenametwo";
    private static final String KEY_PRODUCT_PRICE_RANGE_FROM_TWO = "pricerangefromtwo";
    private static final String KEY_PRODUCT_PRICE_RANGE_TO_TWO = "pricerangetotwo";
    private static final String KEY_PRODUCT_PRICE_VALUE_FROM_TWO = "pricevaluefromtwo";
    private static final String KEY_PRODUCT_PRICE_VALUE_TO_TWO = "pricevaluetotwo";
    private static final String KEY_PRODUCT_PRICE_RANGE_NAME_THREE = "pricerangenamethree";
    private static final String KEY_PRODUCT_PRICE_RANGE_FROM_THREE = "pricerangefromthree";
    private static final String KEY_PRODUCT_PRICE_RANGE_TO_THREE = "pricerangetothree";
    private static final String KEY_PRODUCT_PRICE_VALUE_FROM_THREE = "pricevaluefromthree";
    private static final String KEY_PRODUCT_PRICE_VALUE_TO_THREE = "pricevaluetothree";
    private static final String KEY_PRODUCT_PRICE_RANGE_NAME_FOUR = "pricerangenamefour";
    private static final String KEY_PRODUCT_PRICE_RANGE_FROM_FOUR = "pricerangefromfour";
    private static final String KEY_PRODUCT_PRICE_RANGE_TO_FOUR = "pricerangetofour";
    private static final String KEY_PRODUCT_PRICE_VALUE_FROM_FOUR = "pricevaluefromfour";
    private static final String KEY_PRODUCT_PRICE_VALUE_TO_FOUR = "pricevaluetofour";
    private static final String KEY_PRODUCT_PRICE_RANGE_NAME_FIVE = "pricerangenamefive";
    private static final String KEY_PRODUCT_PRICE_RANGE_FROM_FIVE = "pricerangefromfive";
    private static final String KEY_PRODUCT_PRICE_RANGE_TO_FIVE = "pricerangetofive";
    private static final String KEY_PRODUCT_PRICE_VALUE_FROM_FIVE = "pricevaluefromfive";
    private static final String KEY_PRODUCT_PRICE_VALUE_TO_FIVE = "pricevaluetofive";
    private static final String KEY_PRODUCT_BOX_BY = "boxby";
    private static final String KEY_PRODUCT_TYPE_UNIT = "typeunit";
    private static final String KEY_PRODUCT_PRICE_OF_LIST = "priceoflist";
    private static final String KEY_PRODUCT_FLAG_PRICE = "flagprice";

    // User Table - column names
    private static final String KEY_USER_ID = "id";
    private static final String KEY_USER_USER = "user";
    private static final String KEY_USER_PASS = "pass";
    private static final String KEY_USER_COD_SELLER = "codseller";
    private static final String KEY_USER_NAME = "name";
    private static final String KEY_USER_IMEI = "imei";

    private static final String KEY_CONTROL_ID = "id";
    private static final String KEY_CONTROL_TABLE = "tabla";//4

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
            KEY_CLIENT_COD_SELLER + " TEXT," +
            KEY_CLIENT_SEMAPHORE + " TEXT," +
            KEY_CLIENT_DOCUMENTO + " TEXT," +
            KEY_CLIENT_UBIGEO + " TEXT," +
            KEY_CLIENT_ZONA + " TEXT," +
            KEY_CLIENT_FULL_NAME + " TEXT," +
            KEY_CLIENT_COD_ENTIDAD + " TEXT," +
            KEY_CREATED_AT + " DATETIME DEFAULT CURRENT_TIMESTAMP)";

    private static final String CREATE_TABLE_ORDERS = "CREATE TABLE "
            + TABLE_ORDERS + "(" +
            KEY_ORDER_ID + " INTEGER PRIMARY KEY," +
            KEY_ORDER_DATE_AT + " DATETIME DEFAULT CURRENT_TIMESTAMP, " +
            KEY_ORDER_CLIENT_COD + " INTEGER," +
            KEY_ORDER_SELLER_COD + " INTEGER," +
            KEY_ORDER_IMEI + " TEXT," +
            KEY_ORDER_SEMAPHORE + " TEXT," +
            KEY_ORDER_DELIVERY_AT + " DATETIME DEFAULT CURRENT_TIMESTAMP," +
            KEY_ORDER_PAYMENT_TYPE + " INTEGER, " +
            KEY_ORDER_PAYMENT_VOUCHER_TYPE + " INTEGER, " +
            KEY_ORDER_LATITUDE + " REAL, " +
            KEY_ORDER_LONGITUDE + " REAL, " +
            KEY_ORDER_FLAG_CLOUD + " INTEGER, " +
            KEY_ORDER_COD_ORDER_INTERNA + " NUMERIC, " +
            KEY_CREATED_AT + " DATETIME DEFAULT CURRENT_TIMESTAMP, " +
            "FOREIGN KEY(" + KEY_ORDER_CLIENT_COD + ") REFERENCES " + TABLE_CLIENTS + "("+ KEY_CLIENT_ID +"), " +
            "FOREIGN KEY(" + KEY_ORDER_SELLER_COD + ") REFERENCES " + TABLE_USERS + "("+ KEY_USER_ID +"))";


    private static final String CREATE_TABLE_ORDERS_ITEM = "CREATE TABLE "
            + TABLE_ORDER_ITEMS + "(" +
            KEY_ORDER_ITEM_ID + " INTEGER PRIMARY KEY," +
            KEY_ORDER_ITEM_ORDER_ID + " INTEGER, " +
            KEY_ORDER_ITEM_COD_PRODUCT + " INTEGER," +
            KEY_ORDER_ITEM_QUANTITY + " REAL, " +
            KEY_ORDER_ITEM_PRICE + " REAL, " +
            KEY_ORDER_ITEM_TYPE_UNIT + " TEXT," +
            KEY_ORDER_ITEM_BOX_BY + " INTEGER," +
            KEY_ORDER_ITEM_TYPE_PRICE + " TEXT," +
            KEY_CREATED_AT + " DATETIME DEFAULT CURRENT_TIMESTAMP," +
            KEY_ORDER_ITEM_PRICE_LIST + " INTEGER," +
            KEY_ORDER_ITEM_PRODUCT_COD_LEVEL + " INTEGER," +
            KEY_ORDER_ITEM_PRODUCT_LEVEL_RANGE_FROM + " REAL, " +
            KEY_ORDER_ITEM_PRODUCT_LEVEL_RANGE_TO + " REAL, " +
            "FOREIGN KEY(" + KEY_ORDER_ITEM_COD_PRODUCT + ") REFERENCES " + TABLE_PRODUCTS + "("+ KEY_PRODUCT_ID +"))";
            ;

    private static final String CREATE_TABLE_PRODUCTS = "CREATE TABLE "
            + TABLE_PRODUCTS + "(" +
            KEY_PRODUCT_ID + " INTEGER PRIMARY KEY," +
            KEY_PRODUCT_COD + " TEXT, " +
            KEY_PRODUCT_NAME + " TEXT, " +
            KEY_PRODUCT_PRICE_ONE + " REAL," +
            KEY_PRODUCT_PRICE_TWO + " REAL, " +
            KEY_PRODUCT_PRICE_THREE + " REAL, " +
            KEY_PRODUCT_PRICE_RANGE_NAME_ONE + " INTEGER, " +
            KEY_PRODUCT_PRICE_RANGE_FROM_ONE + " REAL, " +
            KEY_PRODUCT_PRICE_RANGE_TO_ONE + " REAL, " +
            KEY_PRODUCT_PRICE_VALUE_FROM_ONE + " REAL, " +
            KEY_PRODUCT_PRICE_VALUE_TO_ONE + " REAL, " +
            KEY_PRODUCT_PRICE_RANGE_NAME_TWO + " INTEGER, " +
            KEY_PRODUCT_PRICE_RANGE_FROM_TWO + " REAL, " +
            KEY_PRODUCT_PRICE_RANGE_TO_TWO + " REAL, " +
            KEY_PRODUCT_PRICE_VALUE_FROM_TWO + " REAL, " +
            KEY_PRODUCT_PRICE_VALUE_TO_TWO + " REAL, " +
            KEY_PRODUCT_PRICE_RANGE_NAME_THREE + " INTEGER, " +
            KEY_PRODUCT_PRICE_RANGE_FROM_THREE + " REAL, " +
            KEY_PRODUCT_PRICE_RANGE_TO_THREE + " REAL, " +
            KEY_PRODUCT_PRICE_VALUE_FROM_THREE + " REAL, " +
            KEY_PRODUCT_PRICE_VALUE_TO_THREE + " REAL, " +
            KEY_PRODUCT_PRICE_RANGE_NAME_FOUR + " INTEGER, " +
            KEY_PRODUCT_PRICE_RANGE_FROM_FOUR + " REAL, " +
            KEY_PRODUCT_PRICE_RANGE_TO_FOUR + " REAL, " +
            KEY_PRODUCT_PRICE_VALUE_FROM_FOUR + " REAL, " +
            KEY_PRODUCT_PRICE_VALUE_TO_FOUR + " REAL, " +
            KEY_PRODUCT_PRICE_RANGE_NAME_FIVE + " INTEGER, " +
            KEY_PRODUCT_PRICE_RANGE_FROM_FIVE + " REAL, " +
            KEY_PRODUCT_PRICE_RANGE_TO_FIVE + " REAL, " +
            KEY_PRODUCT_PRICE_VALUE_FROM_FIVE + " REAL, " +
            KEY_PRODUCT_PRICE_VALUE_TO_FIVE + " REAL, " +
            KEY_PRODUCT_BOX_BY + " INTEGER, " +
            KEY_PRODUCT_TYPE_UNIT + " TEXT," +
            KEY_PRODUCT_PRICE_OF_LIST + " INTEGER," +
            KEY_PRODUCT_FLAG_PRICE + " TEXT" +
            ")";


    private static final String CREATE_TABLE_USERS = "CREATE TABLE "
            + TABLE_USERS + "(" +
            KEY_USER_ID + " INTEGER PRIMARY KEY," +
            KEY_USER_USER + " TEXT, " +
            KEY_USER_PASS + " TEXT, " +
            KEY_USER_COD_SELLER + " TEXT," +
            KEY_USER_IMEI + " TEXT," +
            KEY_USER_NAME + " TEXT)";


    private static final String CREATE_TABLE_CONTROLS = "CREATE TABLE "
            + TABLE_CONTROLS + "(" +
            KEY_CONTROL_ID + " INTEGER PRIMARY KEY," +
            KEY_CONTROL_TABLE + " TEXT," +
            KEY_CREATED_AT + " DATETIME DEFAULT CURRENT_TIMESTAMP)";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_CLIENTS);
        db.execSQL(CREATE_TABLE_PRODUCTS);
        db.execSQL(CREATE_TABLE_ORDERS);
        db.execSQL(CREATE_TABLE_ORDERS_ITEM);
        db.execSQL(CREATE_TABLE_CONTROLS);
        //crearUsuariosDefecto(db);
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
            seller.setId(c.getInt(c.getColumnIndex(KEY_USER_ID)));
        }

        return seller;
    }

    public boolean isUserExists(Integer userId){
        boolean exists = false;
        String selectQuery = "SELECT  count(1) " +
                "FROM " + TABLE_USERS + " " +
                "WHERE " + KEY_USER_ID + " = ? ";

        Log.i(TAG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, new String[]{userId.toString()});

        if(c.moveToFirst()){
            if(c.getInt(0)>0){
                exists = true;
            }
        }

        return exists;
    }

    private void crearUsuariosDefecto(SQLiteDatabase db) {
        for(SalesMan seller : getDefaultUsers()){
            createUser(seller);
        }
    }

    public long createControl(Control control){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_CONTROL_ID, control.getId());
        values.put(KEY_CONTROL_TABLE, control.getTable());

        return db.insert(TABLE_CONTROLS, null, values);
    }



    public int getMaxIdControlTable(String table){
        int maxControlId = 1;
        String selectQuery = "SELECT ifnull(max(" + KEY_CONTROL_ID + "), 0) " +
                "FROM " + TABLE_CONTROLS + " " +
                "WHERE " + KEY_CONTROL_TABLE + " = ? ";

        Log.i(TAG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, new String[]{table});

        if(c.moveToFirst()){
            maxControlId = c.getInt(0);
        }

        return maxControlId;


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLIENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDER_ITEMS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTROLS);

        // create new tables
        onCreate(db);
    }

    private List<SalesMan> getDefaultUsers(){

        List<SalesMan> lstUsers = new ArrayList<>();

        for (int i = 0; i < 99; i++){
            SalesMan user = new SalesMan();
            user.setCodSeller(i < 9 ? "0" + (i + 1) : ""+ (i + 1));
            user.setName("Seller" + (i + 1));
            user.setPass("123");
            user.setUser("V" + (i < 9 ? "0" + (i + 1) : ""+ (i + 1)));
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
        values.put(KEY_CLIENT_COD_SELLER, client.getCodSeller());
        values.put(KEY_CLIENT_SEMAPHORE, client.getSemaphore());
        values.put(KEY_CLIENT_DOCUMENTO, client.getDocumento());
        values.put(KEY_CLIENT_COD_ENTIDAD, client.getCodEntidad());
        values.put(KEY_CLIENT_FULL_NAME, client.getFullName());
        values.put(KEY_CLIENT_UBIGEO, client.getUbigeo());
        values.put(KEY_CLIENT_ZONA, client.getZona());
        values.put(KEY_CREATED_AT, DateUtil.getCurrentDateTime());

        return db.insert(TABLE_CLIENTS, null, values);
    }

    public void createClientBatch(List<Client> clientList) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            for (Client client : clientList) {

                ContentValues values = new ContentValues();
                values.put(KEY_CLIENT_COD_CLIENT, client.getCodClient());
                values.put(KEY_CLIENT_FIRST_NAME, client.getFirstName());
                values.put(KEY_CLIENT_LAST_NAME, client.getLastName());
                values.put(KEY_CLIENT_ADDRESS, client.getAddress());
                values.put(KEY_CLIENT_RUC, client.getRuc());
                values.put(KEY_CLIENT_DNI, client.getDni());
                values.put(KEY_CLIENT_COD_SELLER, client.getCodSeller());
                values.put(KEY_CLIENT_SEMAPHORE, client.getSemaphore());
                values.put(KEY_CLIENT_DOCUMENTO, client.getDocumento());
                values.put(KEY_CLIENT_COD_ENTIDAD, client.getCodEntidad());
                values.put(KEY_CLIENT_FULL_NAME, client.getFullName());
                values.put(KEY_CLIENT_UBIGEO, client.getUbigeo());
                values.put(KEY_CLIENT_ZONA, client.getZona());
                values.put(KEY_CREATED_AT, DateUtil.getCurrentDateTime());
                values.put(KEY_CLIENT_ID, client.getId());
                db.insert(TABLE_CLIENTS, null, values);
            }
            db.setTransactionSuccessful();
        } catch (Exception e){
            Log.e(TAG, e.toString());
        } finally {
            db.endTransaction();
        }
    }

    public void createProductBatch(List<Product> productList) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            for (Product product : productList) {
                ContentValues values = new ContentValues();
                values.put(KEY_PRODUCT_ID, product.getId());
                values.put(KEY_PRODUCT_COD, product.getCodProduct());
                values.put(KEY_PRODUCT_NAME, product.getName());
                values.put(KEY_PRODUCT_PRICE_ONE, product.getPriceOne());
                values.put(KEY_PRODUCT_PRICE_TWO, product.getPriceTwo());
                values.put(KEY_PRODUCT_PRICE_THREE, product.getPriceThree());
                values.put(KEY_PRODUCT_PRICE_RANGE_NAME_ONE, product.getPriceLevelList().get(0).getLevel());
                values.put(KEY_PRODUCT_PRICE_RANGE_FROM_ONE, product.getPriceLevelList().get(0).getRangeFrom());
                values.put(KEY_PRODUCT_PRICE_RANGE_TO_ONE, product.getPriceLevelList().get(0).getRangeTo());
                values.put(KEY_PRODUCT_PRICE_VALUE_FROM_ONE, product.getPriceLevelList().get(0).getPriceFrom());
                values.put(KEY_PRODUCT_PRICE_VALUE_TO_ONE, product.getPriceLevelList().get(0).getPriceTo());
                values.put(KEY_PRODUCT_PRICE_RANGE_NAME_TWO, product.getPriceLevelList().get(1).getLevel());
                values.put(KEY_PRODUCT_PRICE_RANGE_FROM_TWO, product.getPriceLevelList().get(1).getRangeFrom());
                values.put(KEY_PRODUCT_PRICE_RANGE_TO_TWO, product.getPriceLevelList().get(1).getRangeTo());
                values.put(KEY_PRODUCT_PRICE_VALUE_FROM_TWO, product.getPriceLevelList().get(1).getPriceFrom());
                values.put(KEY_PRODUCT_PRICE_VALUE_TO_TWO, product.getPriceLevelList().get(1).getPriceTo());
                values.put(KEY_PRODUCT_PRICE_RANGE_NAME_THREE, product.getPriceLevelList().get(2).getLevel());
                values.put(KEY_PRODUCT_PRICE_RANGE_FROM_THREE, product.getPriceLevelList().get(2).getRangeFrom());
                values.put(KEY_PRODUCT_PRICE_RANGE_TO_THREE, product.getPriceLevelList().get(2).getRangeTo());
                values.put(KEY_PRODUCT_PRICE_VALUE_FROM_THREE, product.getPriceLevelList().get(2).getPriceFrom());
                values.put(KEY_PRODUCT_PRICE_VALUE_TO_THREE, product.getPriceLevelList().get(2).getPriceTo());
                values.put(KEY_PRODUCT_PRICE_RANGE_NAME_FOUR, product.getPriceLevelList().get(3).getLevel());
                values.put(KEY_PRODUCT_PRICE_RANGE_FROM_FOUR, product.getPriceLevelList().get(3).getRangeFrom());
                values.put(KEY_PRODUCT_PRICE_RANGE_TO_FOUR, product.getPriceLevelList().get(3).getRangeTo());
                values.put(KEY_PRODUCT_PRICE_VALUE_FROM_FOUR, product.getPriceLevelList().get(3).getPriceFrom());
                values.put(KEY_PRODUCT_PRICE_VALUE_TO_FOUR, product.getPriceLevelList().get(3).getPriceTo());
                values.put(KEY_PRODUCT_PRICE_RANGE_NAME_FIVE, product.getPriceLevelList().get(4).getLevel());
                values.put(KEY_PRODUCT_PRICE_RANGE_FROM_FIVE, product.getPriceLevelList().get(4).getRangeFrom());
                values.put(KEY_PRODUCT_PRICE_RANGE_TO_FIVE, product.getPriceLevelList().get(4).getRangeTo());
                values.put(KEY_PRODUCT_PRICE_VALUE_FROM_FIVE, product.getPriceLevelList().get(4).getPriceFrom());
                values.put(KEY_PRODUCT_PRICE_VALUE_TO_FIVE, product.getPriceLevelList().get(4).getPriceTo());
                values.put(KEY_PRODUCT_BOX_BY, product.getBoxBy());
                values.put(KEY_PRODUCT_TYPE_UNIT, product.getTypeUnit());
                values.put(KEY_PRODUCT_PRICE_OF_LIST, product.getPriceOfList());
                values.put(KEY_PRODUCT_FLAG_PRICE, product.getFlagPrice());

                db.insert(TABLE_PRODUCTS, null, values);
            }
            db.setTransactionSuccessful();
        } catch (Exception e){
            Log.e(TAG, e.toString());
        } finally {
            db.endTransaction();
        }
    }

    public int updateOrder(Order order){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ORDER_FLAG_CLOUD, order.getFlagCloud());
        int result = db.update(TABLE_ORDERS, values, "id=?", new String[]{String.valueOf(order.getId())});
        return result;
    }

    public void clientBatchUpdate(List<Client> clientList){
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            for (Client client : clientList) {
                ContentValues values = new ContentValues();
                values.put(KEY_CLIENT_COD_CLIENT, client.getCodClient());
                values.put(KEY_CLIENT_FIRST_NAME, client.getFirstName());
                values.put(KEY_CLIENT_LAST_NAME, client.getLastName());
                values.put(KEY_CLIENT_ADDRESS, client.getAddress());
                values.put(KEY_CLIENT_RUC, client.getRuc());
                values.put(KEY_CLIENT_DNI, client.getDni());
                values.put(KEY_CLIENT_COD_SELLER, client.getCodSeller());
                values.put(KEY_CLIENT_SEMAPHORE, client.getSemaphore());
                values.put(KEY_CLIENT_DOCUMENTO, client.getDocumento());
                values.put(KEY_CLIENT_COD_ENTIDAD, client.getCodEntidad());
                values.put(KEY_CLIENT_FULL_NAME, client.getFullName());
                values.put(KEY_CLIENT_UBIGEO, client.getUbigeo());
                values.put(KEY_CLIENT_ZONA, client.getZona());
                values.put(KEY_CREATED_AT, DateUtil.getCurrentDateTime());
                values.put(KEY_CLIENT_ID, client.getId());
                int id = (int) db.insertWithOnConflict(TABLE_CLIENTS, null, values, SQLiteDatabase.CONFLICT_IGNORE);
                if (id == -1) {
                    db.update(TABLE_CLIENTS, values, "id=?", new String[] {String.valueOf(client.getId())});
                }
            }
            db.setTransactionSuccessful();
        } catch (Exception e){
            Log.e(TAG, e.toString());
        } finally {
            db.endTransaction();
        }
    }

    public void productBatchUpdate(List<Product> productList) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            for (Product product : productList) {
                ContentValues values = new ContentValues();
                values.put(KEY_PRODUCT_COD, product.getCodProduct());
                values.put(KEY_PRODUCT_NAME, product.getName());
                values.put(KEY_PRODUCT_PRICE_ONE, product.getPriceOne());
                values.put(KEY_PRODUCT_PRICE_TWO, product.getPriceTwo());
                values.put(KEY_PRODUCT_PRICE_THREE, product.getPriceThree());
                values.put(KEY_PRODUCT_PRICE_RANGE_NAME_ONE, product.getPriceLevelList().get(0).getLevel());
                values.put(KEY_PRODUCT_PRICE_RANGE_FROM_ONE, product.getPriceLevelList().get(0).getRangeFrom());
                values.put(KEY_PRODUCT_PRICE_RANGE_TO_ONE, product.getPriceLevelList().get(0).getRangeTo());
                values.put(KEY_PRODUCT_PRICE_VALUE_FROM_ONE, product.getPriceLevelList().get(0).getPriceFrom());
                values.put(KEY_PRODUCT_PRICE_VALUE_TO_ONE, product.getPriceLevelList().get(0).getPriceTo());
                values.put(KEY_PRODUCT_PRICE_RANGE_NAME_TWO, product.getPriceLevelList().get(1).getLevel());
                values.put(KEY_PRODUCT_PRICE_RANGE_FROM_TWO, product.getPriceLevelList().get(1).getRangeFrom());
                values.put(KEY_PRODUCT_PRICE_RANGE_TO_TWO, product.getPriceLevelList().get(1).getRangeTo());
                values.put(KEY_PRODUCT_PRICE_VALUE_FROM_TWO, product.getPriceLevelList().get(1).getPriceFrom());
                values.put(KEY_PRODUCT_PRICE_VALUE_TO_TWO, product.getPriceLevelList().get(1).getPriceTo());
                values.put(KEY_PRODUCT_PRICE_RANGE_NAME_THREE, product.getPriceLevelList().get(2).getLevel());
                values.put(KEY_PRODUCT_PRICE_RANGE_FROM_THREE, product.getPriceLevelList().get(2).getRangeFrom());
                values.put(KEY_PRODUCT_PRICE_RANGE_TO_THREE, product.getPriceLevelList().get(2).getRangeTo());
                values.put(KEY_PRODUCT_PRICE_VALUE_FROM_THREE, product.getPriceLevelList().get(2).getPriceFrom());
                values.put(KEY_PRODUCT_PRICE_VALUE_TO_THREE, product.getPriceLevelList().get(2).getPriceTo());
                values.put(KEY_PRODUCT_PRICE_RANGE_NAME_FOUR, product.getPriceLevelList().get(3).getLevel());
                values.put(KEY_PRODUCT_PRICE_RANGE_FROM_FOUR, product.getPriceLevelList().get(3).getRangeFrom());
                values.put(KEY_PRODUCT_PRICE_RANGE_TO_FOUR, product.getPriceLevelList().get(3).getRangeTo());
                values.put(KEY_PRODUCT_PRICE_VALUE_FROM_FOUR, product.getPriceLevelList().get(3).getPriceFrom());
                values.put(KEY_PRODUCT_PRICE_VALUE_TO_FOUR, product.getPriceLevelList().get(3).getPriceTo());
                values.put(KEY_PRODUCT_PRICE_RANGE_NAME_FIVE, product.getPriceLevelList().get(4).getLevel());
                values.put(KEY_PRODUCT_PRICE_RANGE_FROM_FIVE, product.getPriceLevelList().get(4).getRangeFrom());
                values.put(KEY_PRODUCT_PRICE_RANGE_TO_FIVE, product.getPriceLevelList().get(4).getRangeTo());
                values.put(KEY_PRODUCT_PRICE_VALUE_FROM_FIVE, product.getPriceLevelList().get(4).getPriceFrom());
                values.put(KEY_PRODUCT_PRICE_VALUE_TO_FIVE, product.getPriceLevelList().get(4).getPriceTo());
                values.put(KEY_PRODUCT_BOX_BY, product.getBoxBy());
                values.put(KEY_PRODUCT_TYPE_UNIT, product.getTypeUnit());
                values.put(KEY_PRODUCT_PRICE_OF_LIST, product.getPriceOfList());
                values.put(KEY_PRODUCT_FLAG_PRICE, product.getFlagPrice());
                values.put(KEY_PRODUCT_ID, product.getId());
                int id = (int) db.insertWithOnConflict(TABLE_PRODUCTS, null, values, SQLiteDatabase.CONFLICT_IGNORE);
                if (id == -1) {
                    db.update(TABLE_PRODUCTS, values, "id=?", new String[] {String.valueOf(product.getId())});
                }
            }
            db.setTransactionSuccessful();
        } catch (Exception e){
            Log.e(TAG, e.toString());
        } finally {
            db.endTransaction();
        }
    }

    public long createUser(SalesMan user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USER_ID, user.getId());
        values.put(KEY_USER_COD_SELLER, user.getCodSeller());
        values.put(KEY_USER_NAME, user.getName());
        values.put(KEY_USER_USER, user.getUser());
        values.put(KEY_USER_PASS, user.getPass());
        values.put(KEY_USER_IMEI, user.getImei());

        return db.insert(TABLE_USERS, null, values);
    }

    public void createUserBatch(List<SalesMan> listUsers){
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            for (SalesMan user : listUsers) {
                ContentValues values = new ContentValues();
                values.put(KEY_USER_COD_SELLER, user.getCodSeller());
                values.put(KEY_USER_NAME, user.getName());
                values.put(KEY_USER_USER, user.getUser());
                values.put(KEY_USER_PASS, user.getPass());
                values.put(KEY_USER_IMEI, user.getImei());
                values.put(KEY_USER_ID, user.getId());

                db.insert(TABLE_USERS, null, values);
            }
            db.setTransactionSuccessful();
        } catch (Exception e){
            Log.e(TAG, e.toString());
        } finally {
            db.endTransaction();
        }
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
            client.setSemaphore(c.getString(c.getColumnIndex(KEY_CLIENT_SEMAPHORE)));
            client.setDocumento(c.getString(c.getColumnIndex(KEY_CLIENT_DOCUMENTO)));
            client.setCodEntidad(c.getString(c.getColumnIndex(KEY_CLIENT_COD_ENTIDAD)));
            client.setFullName(c.getString(c.getColumnIndex(KEY_CLIENT_FULL_NAME)));
            client.setUbigeo(c.getString(c.getColumnIndex(KEY_CLIENT_UBIGEO)));
            client.setZona(c.getString(c.getColumnIndex(KEY_CLIENT_ZONA)));
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
                client.setSemaphore(c.getString(c.getColumnIndex(KEY_CLIENT_SEMAPHORE)));
                client.setDocumento(c.getString(c.getColumnIndex(KEY_CLIENT_DOCUMENTO)));
                client.setCodEntidad(c.getString(c.getColumnIndex(KEY_CLIENT_COD_ENTIDAD)));
                client.setFullName(c.getString(c.getColumnIndex(KEY_CLIENT_FULL_NAME)));
                client.setUbigeo(c.getString(c.getColumnIndex(KEY_CLIENT_UBIGEO)));
                client.setZona(c.getString(c.getColumnIndex(KEY_CLIENT_ZONA)));
                // adding to client list
                clients.add(client);
            } while (c.moveToNext());
        }
        return clients;
    }

    public void sellerBatchUpdate(List<SalesMan> salesManList){
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            for (SalesMan user : salesManList) {
                ContentValues values = new ContentValues();
                values.put(KEY_USER_COD_SELLER, user.getCodSeller());
                values.put(KEY_USER_NAME, user.getName());
                values.put(KEY_USER_USER, user.getUser());
                values.put(KEY_USER_PASS, user.getPass());
                values.put(KEY_USER_IMEI, user.getImei());
                values.put(KEY_USER_ID, user.getId());
                int id = (int) db.insertWithOnConflict(TABLE_USERS, null, values, SQLiteDatabase.CONFLICT_IGNORE);
                if (id == -1) {
                    db.update(TABLE_USERS, values, "id=?", new String[] {String.valueOf(user.getId())});
                }

            }
            db.setTransactionSuccessful();
        } catch (Exception e){
            Log.e(TAG, e.toString());
        } finally {
            db.endTransaction();
        }
    }

    public List<Client> getAllClientsFromSeller(String codSeller) {
        List<Client> clients = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_CLIENTS +
                " WHERE " + KEY_CLIENT_COD_SELLER + " = ?";

        Log.i(TAG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, new String[]{codSeller});

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
                client.setCodSeller(c.getString(c.getColumnIndex(KEY_CLIENT_COD_SELLER)));
                client.setDateReg(DateUtil.getDateTime(c.getString(c.getColumnIndex(KEY_CREATED_AT))));
                client.setSemaphore(c.getString(c.getColumnIndex(KEY_CLIENT_SEMAPHORE)));
                client.setDocumento(c.getString(c.getColumnIndex(KEY_CLIENT_DOCUMENTO)));
                client.setCodEntidad(c.getString(c.getColumnIndex(KEY_CLIENT_COD_ENTIDAD)));
                client.setFullName(c.getString(c.getColumnIndex(KEY_CLIENT_FULL_NAME)));
                client.setUbigeo(c.getString(c.getColumnIndex(KEY_CLIENT_UBIGEO)));
                client.setZona(c.getString(c.getColumnIndex(KEY_CLIENT_ZONA)));
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

    public int getNextOrderId(){
        int maxOrder = 1;

        SQLiteDatabase db = this.getReadableDatabase();

        int year = Calendar.getInstance().get(Calendar.YEAR);

        //Cursor c =  db.query(TABLE_ORDERS, new String[]{"MAX(codsale)"}, "strftime('%Y', dateorder) = ?", new String[]{String.valueOf(year)}, null, null, null);
        Cursor c =  db.query(TABLE_ORDERS, new String[]{"ifnull(MAX(id)+1,1)"}, null, null, null, null, null);

        if(c.moveToFirst()){
            maxOrder = c.getInt(0);
        }

        return maxOrder;
    }

    public long getNextOrderInterna(){
        long maxOrderInterna = Long.parseLong(DateUtil.getCurrentNumberDate() + StringUtils.leftPad("1", 3, "0"));
        SQLiteDatabase db = this.getReadableDatabase();
        //Cursor c =  db.query(TABLE_ORDERS, new String[]{"MAX("+KEY_ORDER_COD_ORDER_INTERNA+")"}, "dateorder = ?", new String[]{"date('now', 'localtime')"}, null, null, null);
        //Cursor c =  db.query(TABLE_ORDERS, new String[]{"MAX(ordeninterna)"}, "strftime('%Y%m%d', dateorder) = ?", new String[]{"strftime('%Y%m%d', date('now', 'localtime'))"}, null, null, null);
        Cursor c =  db.rawQuery("select MAX(ordeninterna) from orders where dateorder = date('now', 'localtime')", null);
        //query(TABLE_ORDERS, new String[]{"MAX(ordeninterna)"}, "strftime('%Y%m%d', dateorder) = ?", new String[]{"strftime('%Y%m%d', date('now', 'localtime'))"}, null, null, null);

        if(c.moveToFirst()){
            if(c.getLong(0) != 0L){
                maxOrderInterna = c.getLong(0);
                maxOrderInterna++;
            }
        }

        return maxOrderInterna;
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
                product.setPriceThree(c.getDouble(c.getColumnIndex(KEY_PRODUCT_PRICE_THREE)));
                List<PriceLevel> priceLevels = new ArrayList<>();
                PriceLevel priceLevel = new PriceLevel();
                priceLevel.setLevel(c.getInt(c.getColumnIndex(KEY_PRODUCT_PRICE_RANGE_NAME_ONE)));
                priceLevel.setRangeFrom(c.getDouble(c.getColumnIndex(KEY_PRODUCT_PRICE_RANGE_FROM_ONE)));
                priceLevel.setRangeTo(c.getDouble(c.getColumnIndex(KEY_PRODUCT_PRICE_RANGE_TO_ONE)));
                priceLevel.setPriceFrom(c.getDouble(c.getColumnIndex(KEY_PRODUCT_PRICE_VALUE_FROM_ONE)));
                priceLevel.setPriceTo(c.getDouble(c.getColumnIndex(KEY_PRODUCT_PRICE_VALUE_TO_ONE)));
                priceLevels.add(priceLevel);
                priceLevel = new PriceLevel();
                priceLevel.setLevel(c.getInt(c.getColumnIndex(KEY_PRODUCT_PRICE_RANGE_NAME_TWO)));
                priceLevel.setRangeFrom(c.getDouble(c.getColumnIndex(KEY_PRODUCT_PRICE_RANGE_FROM_TWO)));
                priceLevel.setRangeTo(c.getDouble(c.getColumnIndex(KEY_PRODUCT_PRICE_RANGE_TO_TWO)));
                priceLevel.setPriceFrom(c.getDouble(c.getColumnIndex(KEY_PRODUCT_PRICE_VALUE_FROM_TWO)));
                priceLevel.setPriceTo(c.getDouble(c.getColumnIndex(KEY_PRODUCT_PRICE_VALUE_TO_TWO)));
                priceLevels.add(priceLevel);
                priceLevel = new PriceLevel();
                priceLevel.setLevel(c.getInt(c.getColumnIndex(KEY_PRODUCT_PRICE_RANGE_NAME_THREE)));
                priceLevel.setRangeFrom(c.getDouble(c.getColumnIndex(KEY_PRODUCT_PRICE_RANGE_FROM_THREE)));
                priceLevel.setRangeTo(c.getDouble(c.getColumnIndex(KEY_PRODUCT_PRICE_RANGE_TO_THREE)));
                priceLevel.setPriceFrom(c.getDouble(c.getColumnIndex(KEY_PRODUCT_PRICE_VALUE_FROM_THREE)));
                priceLevel.setPriceTo(c.getDouble(c.getColumnIndex(KEY_PRODUCT_PRICE_VALUE_TO_THREE)));
                priceLevels.add(priceLevel);
                priceLevel = new PriceLevel();
                priceLevel.setLevel(c.getInt(c.getColumnIndex(KEY_PRODUCT_PRICE_RANGE_NAME_FOUR)));
                priceLevel.setRangeFrom(c.getDouble(c.getColumnIndex(KEY_PRODUCT_PRICE_RANGE_FROM_FOUR)));
                priceLevel.setRangeTo(c.getDouble(c.getColumnIndex(KEY_PRODUCT_PRICE_RANGE_TO_FOUR)));
                priceLevel.setPriceFrom(c.getDouble(c.getColumnIndex(KEY_PRODUCT_PRICE_VALUE_FROM_FOUR)));
                priceLevel.setPriceTo(c.getDouble(c.getColumnIndex(KEY_PRODUCT_PRICE_VALUE_TO_FOUR)));
                priceLevels.add(priceLevel);
                priceLevel = new PriceLevel();
                priceLevel.setLevel(c.getInt(c.getColumnIndex(KEY_PRODUCT_PRICE_RANGE_NAME_FIVE)));
                priceLevel.setRangeFrom(c.getDouble(c.getColumnIndex(KEY_PRODUCT_PRICE_RANGE_FROM_FIVE)));
                priceLevel.setRangeTo(c.getDouble(c.getColumnIndex(KEY_PRODUCT_PRICE_RANGE_TO_FIVE)));
                priceLevel.setPriceFrom(c.getDouble(c.getColumnIndex(KEY_PRODUCT_PRICE_VALUE_FROM_FIVE)));
                priceLevel.setPriceTo(c.getDouble(c.getColumnIndex(KEY_PRODUCT_PRICE_VALUE_TO_FIVE)));
                priceLevels.add(priceLevel);
                product.setPriceLevelList(priceLevels);
                product.setBoxBy(c.getInt(c.getColumnIndex(KEY_PRODUCT_BOX_BY)));
                product.setTypeUnit(c.getString(c.getColumnIndex(KEY_PRODUCT_TYPE_UNIT)));
                product.setPriceOfList(c.getInt(c.getColumnIndex(KEY_PRODUCT_PRICE_OF_LIST)));
                product.setFlagPrice(c.getString(c.getColumnIndex(KEY_PRODUCT_FLAG_PRICE)));
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
        values.put(KEY_PRODUCT_PRICE_THREE, product.getPriceThree());
        values.put(KEY_PRODUCT_PRICE_RANGE_NAME_ONE, product.getPriceLevelList().get(0).getLevel());
        values.put(KEY_PRODUCT_PRICE_RANGE_FROM_ONE, product.getPriceLevelList().get(0).getRangeFrom());
        values.put(KEY_PRODUCT_PRICE_RANGE_TO_ONE, product.getPriceLevelList().get(0).getRangeTo());
        values.put(KEY_PRODUCT_PRICE_VALUE_FROM_ONE, product.getPriceLevelList().get(0).getPriceFrom());
        values.put(KEY_PRODUCT_PRICE_VALUE_TO_ONE, product.getPriceLevelList().get(0).getPriceTo());
        values.put(KEY_PRODUCT_PRICE_RANGE_NAME_TWO, product.getPriceLevelList().get(1).getLevel());
        values.put(KEY_PRODUCT_PRICE_RANGE_FROM_TWO, product.getPriceLevelList().get(1).getRangeFrom());
        values.put(KEY_PRODUCT_PRICE_RANGE_TO_TWO, product.getPriceLevelList().get(1).getRangeTo());
        values.put(KEY_PRODUCT_PRICE_VALUE_FROM_TWO, product.getPriceLevelList().get(1).getPriceFrom());
        values.put(KEY_PRODUCT_PRICE_VALUE_TO_TWO, product.getPriceLevelList().get(1).getPriceTo());
        values.put(KEY_PRODUCT_PRICE_RANGE_NAME_THREE, product.getPriceLevelList().get(2).getLevel());
        values.put(KEY_PRODUCT_PRICE_RANGE_FROM_THREE, product.getPriceLevelList().get(2).getRangeFrom());
        values.put(KEY_PRODUCT_PRICE_RANGE_TO_THREE, product.getPriceLevelList().get(2).getRangeTo());
        values.put(KEY_PRODUCT_PRICE_VALUE_FROM_THREE, product.getPriceLevelList().get(2).getPriceFrom());
        values.put(KEY_PRODUCT_PRICE_VALUE_TO_THREE, product.getPriceLevelList().get(2).getPriceTo());
        values.put(KEY_PRODUCT_PRICE_RANGE_NAME_FOUR, product.getPriceLevelList().get(3).getLevel());
        values.put(KEY_PRODUCT_PRICE_RANGE_FROM_FOUR, product.getPriceLevelList().get(3).getRangeFrom());
        values.put(KEY_PRODUCT_PRICE_RANGE_TO_FOUR, product.getPriceLevelList().get(3).getRangeTo());
        values.put(KEY_PRODUCT_PRICE_VALUE_FROM_FOUR, product.getPriceLevelList().get(3).getPriceFrom());
        values.put(KEY_PRODUCT_PRICE_VALUE_TO_FOUR, product.getPriceLevelList().get(3).getPriceTo());
        values.put(KEY_PRODUCT_PRICE_RANGE_NAME_FIVE, product.getPriceLevelList().get(4).getLevel());
        values.put(KEY_PRODUCT_PRICE_RANGE_FROM_FIVE, product.getPriceLevelList().get(4).getRangeFrom());
        values.put(KEY_PRODUCT_PRICE_RANGE_TO_FIVE, product.getPriceLevelList().get(4).getRangeTo());
        values.put(KEY_PRODUCT_PRICE_VALUE_FROM_FIVE, product.getPriceLevelList().get(4).getPriceFrom());
        values.put(KEY_PRODUCT_PRICE_VALUE_TO_FIVE, product.getPriceLevelList().get(4).getPriceTo());
        values.put(KEY_PRODUCT_BOX_BY, product.getBoxBy());
        values.put(KEY_PRODUCT_TYPE_UNIT, product.getTypeUnit());
        values.put(KEY_PRODUCT_PRICE_OF_LIST, product.getPriceOfList());
        values.put(KEY_PRODUCT_FLAG_PRICE, product.getFlagPrice());

        return db.insert(TABLE_PRODUCTS, null, values);
    }

    public long createOrder(Order order){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ORDER_DATE_AT, DateUtil.getFormatDate(order.getDateOrder()));
        values.put(KEY_ORDER_CLIENT_COD, order.getClient().getId());
        values.put(KEY_ORDER_SELLER_COD, order.getSeller().getId());
        values.put(KEY_ORDER_DELIVERY_AT, DateUtil.getFormatDate(order.getDateDelivery()));
        values.put(KEY_ORDER_PAYMENT_TYPE, order.getPaymentType());
        values.put(KEY_ORDER_PAYMENT_VOUCHER_TYPE, order.getPaymentVoucherType());
        values.put(KEY_CREATED_AT, DateUtil.getCurrentDateTime());
        values.put(KEY_ORDER_IMEI, order.getImei());
        values.put(KEY_ORDER_SEMAPHORE, order.getClient().getSemaphore());
        values.put(KEY_ORDER_LATITUDE, order.getLatitude());
        values.put(KEY_ORDER_LONGITUDE, order.getLongitude());
        values.put(KEY_ORDER_FLAG_CLOUD, order.getFlagCloud());
        values.put(KEY_ORDER_COD_ORDER_INTERNA, order.getOrderInterna());

        return db.insert(TABLE_ORDERS, null, values);
    }

    public long createOrderItem(Item item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ORDER_ITEM_ORDER_ID, item.getOrderId());
        values.put(KEY_ORDER_ITEM_COD_PRODUCT, item.getProduct().getId());
        values.put(KEY_ORDER_ITEM_QUANTITY, item.getQuantity());
        values.put(KEY_ORDER_ITEM_PRICE, item.getPrice());
        values.put(KEY_ORDER_ITEM_TYPE_UNIT, item.getProduct().getTypeUnit());
        values.put(KEY_ORDER_ITEM_BOX_BY, item.getProduct().getBoxBy());
        values.put(KEY_ORDER_ITEM_TYPE_PRICE, item.getTypePrice());
        values.put(KEY_CREATED_AT, DateUtil.getCurrentDateTime());
        values.put(KEY_ORDER_ITEM_PRICE_LIST, item.getPriceOfList());
        values.put(KEY_ORDER_ITEM_PRODUCT_COD_LEVEL, item.getLevel());
        values.put(KEY_ORDER_ITEM_PRODUCT_LEVEL_RANGE_FROM, item.getLevelRangeFrom());
        values.put(KEY_ORDER_ITEM_PRODUCT_LEVEL_RANGE_TO, item.getLevelRangeTo());

        return db.insert(TABLE_ORDER_ITEMS, null, values);
    }

    public List<Order> getOrdersFromToday(Integer sellerId){
        List<Order> orders = new ArrayList<>();
        String selectQuery = "SELECT  " +
                "O." + KEY_ORDER_ID + ", " +
                "O." + KEY_ORDER_DATE_AT + "," +
                "O." + KEY_ORDER_DELIVERY_AT + "," +
                "O." + KEY_ORDER_PAYMENT_TYPE + "," +
                "O." + KEY_ORDER_PAYMENT_VOUCHER_TYPE + "," +
                "O." + KEY_ORDER_FLAG_CLOUD + "," +
                "C." + KEY_CLIENT_COD_CLIENT + "," +
                "C." + KEY_CLIENT_FIRST_NAME + "," +
                "C." + KEY_CLIENT_LAST_NAME + "," +
                "C." + KEY_CLIENT_FULL_NAME + "," +
                "U." + KEY_USER_COD_SELLER + "," +
                "U." + KEY_USER_NAME +
                " FROM " + TABLE_ORDERS +
                " O INNER JOIN " + TABLE_CLIENTS  + " C ON O." + KEY_ORDER_CLIENT_COD + " =" +
                " C." + KEY_CLIENT_ID +
                " INNER JOIN " + TABLE_USERS  + " U ON O." + KEY_ORDER_SELLER_COD + " =" +
                " U." + KEY_USER_ID +
                " WHERE " +
                " U." + KEY_USER_ID + " = '" + sellerId + "'" +
                " AND O." + KEY_CREATED_AT + " BETWEEN " +
                "datetime(date('now', 'localtime')||' 00:00:00') AND datetime(date('now', 'localtime')||' 23:59:59')";

        Log.i(TAG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Order order = new Order();
                order.setId(c.getInt(c.getColumnIndex(KEY_ORDER_ID)));
                order.setDateOrder(DateUtil.getDate(c.getString(
                        c.getColumnIndex(KEY_ORDER_DATE_AT))));
                order.setDateDelivery(DateUtil.getDate(c.getString(
                        c.getColumnIndex(KEY_ORDER_DELIVERY_AT))));
                order.setPaymentType(c.getInt(c.getColumnIndex(KEY_ORDER_PAYMENT_TYPE)));
                order.setPaymentVoucherType(c.getInt(c.getColumnIndex(KEY_ORDER_PAYMENT_VOUCHER_TYPE)));
                order.setFlagCloud(c.getInt(c.getColumnIndex(KEY_ORDER_FLAG_CLOUD)));
                Client client = new Client();
                client.setCodClient(c.getInt(c.getColumnIndex(KEY_CLIENT_COD_CLIENT)));
                client.setFirstName(c.getString(c.getColumnIndex(KEY_CLIENT_FIRST_NAME)));
                client.setLastName(c.getString(c.getColumnIndex(KEY_CLIENT_LAST_NAME)));
                client.setFullName(c.getString(c.getColumnIndex(KEY_CLIENT_FULL_NAME)));
                SalesMan seller = new SalesMan();
                seller.setCodSeller(c.getString(c.getColumnIndex(KEY_USER_COD_SELLER)));
                seller.setName(c.getString(c.getColumnIndex(KEY_USER_NAME)));
                order.setSeller(seller);
                order.setClient(client);
                order.setItems(getItemsFromOrder(order.getId()));
                orders.add(order);
            } while (c.moveToNext());
        }
        return orders;
    }

    public List<Order> getOrdersFromToday(String codSeller){
        List<Order> orders = new ArrayList<>();
        String selectQuery = "SELECT  " +
                "O." + KEY_ORDER_ID + ", " +
                "O." + KEY_ORDER_DATE_AT + "," +
                "O." + KEY_ORDER_DELIVERY_AT + "," +
                "O." + KEY_ORDER_PAYMENT_TYPE + "," +
                "O." + KEY_ORDER_PAYMENT_VOUCHER_TYPE + "," +
                "C." + KEY_CLIENT_COD_CLIENT + "," +
                "C." + KEY_CLIENT_FIRST_NAME + "," +
                "C." + KEY_CLIENT_LAST_NAME + "," +
                "U." + KEY_USER_COD_SELLER + "," +
                "U." + KEY_USER_NAME +
                " FROM " + TABLE_ORDERS +
                " O INNER JOIN " + TABLE_CLIENTS  + " C ON O." + KEY_ORDER_CLIENT_COD + " =" +
                " C." + KEY_CLIENT_COD_CLIENT +
                " INNER JOIN " + TABLE_USERS  + " U ON O." + KEY_ORDER_SELLER_COD + " =" +
                " U." + KEY_USER_COD_SELLER +
                " WHERE " +
                " U." + KEY_USER_COD_SELLER + " = '" + codSeller + "'" +
                " AND O." + KEY_CREATED_AT + " BETWEEN " +
                "datetime(date('now', 'localtime')||' 00:00:00') AND datetime(date('now', 'localtime')||' 23:59:59')";

        Log.i(TAG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Order order = new Order();
                order.setId(c.getInt(c.getColumnIndex(KEY_ORDER_ID)));
                order.setDateOrder(DateUtil.getDate(c.getString(
                        c.getColumnIndex(KEY_ORDER_DATE_AT))));
                order.setDateDelivery(DateUtil.getDate(c.getString(
                        c.getColumnIndex(KEY_ORDER_DELIVERY_AT))));
                order.setPaymentType(c.getInt(c.getColumnIndex(KEY_ORDER_PAYMENT_TYPE)));
                order.setPaymentVoucherType(c.getInt(c.getColumnIndex(KEY_ORDER_PAYMENT_VOUCHER_TYPE)));
                Client client = new Client();
                client.setCodClient(c.getInt(c.getColumnIndex(KEY_CLIENT_COD_CLIENT)));
                client.setFirstName(c.getString(c.getColumnIndex(KEY_CLIENT_FIRST_NAME)));
                client.setLastName(c.getString(c.getColumnIndex(KEY_CLIENT_LAST_NAME)));
                SalesMan seller = new SalesMan();
                seller.setCodSeller(c.getString(c.getColumnIndex(KEY_USER_COD_SELLER)));
                seller.setName(c.getString(c.getColumnIndex(KEY_USER_NAME)));
                order.setSeller(seller);
                order.setClient(client);
                order.setItems(getItemsFromOrder(order.getId()));

                orders.add(order);
            } while (c.moveToNext());
        }
        return orders;
    }

    public int countPendingOrder(){
        int pendingOrders = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  count(1) FROM " + TABLE_ORDERS +
                " WHERE " + KEY_ORDER_FLAG_CLOUD + " = ?" +
                " OR " + KEY_ORDER_FLAG_CLOUD + " = ?";
        Cursor c = db.rawQuery(selectQuery, new String[]{"1","2"});
        if (c.moveToFirst()) {
            pendingOrders = c.getInt(0);
        }
        return pendingOrders;

    }

    public List<Order> getPendingOrders(){
        List<Order> orders = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_ORDERS +
                " WHERE " + KEY_ORDER_FLAG_CLOUD + " = ?" +
                " OR " + KEY_ORDER_FLAG_CLOUD + " = ?";

        Log.i(TAG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, new String[]{"1","2"});

        if (c.moveToFirst()) {
            do {
                Order order = new Order();
                order.setId(c.getInt(c.getColumnIndex(KEY_ORDER_ID)));
                order.setDateOrder(DateUtil.getDate(c.getString(c.getColumnIndex(KEY_ORDER_DATE_AT))));
                order.setDateDelivery(DateUtil.getDate(c.getString(c.getColumnIndex(KEY_ORDER_DELIVERY_AT))));
                order.setPaymentType(c.getInt(c.getColumnIndex(KEY_ORDER_PAYMENT_TYPE)));
                order.setPaymentVoucherType(c.getInt(c.getColumnIndex(KEY_ORDER_PAYMENT_VOUCHER_TYPE)));
                order.setImei(c.getString(c.getColumnIndex(KEY_ORDER_IMEI)));
                order.setLatitude(c.getDouble(c.getColumnIndex(KEY_ORDER_LATITUDE)));
                order.setLongitude(c.getDouble(c.getColumnIndex(KEY_ORDER_LONGITUDE)));
                order.setSemaphore(c.getString(c.getColumnIndex(KEY_ORDER_SEMAPHORE)));
                order.setOrderInterna(c.getLong(c.getColumnIndex(KEY_ORDER_COD_ORDER_INTERNA)));
                order.setDateReg(DateUtil.getDate(c.getString(c.getColumnIndex(KEY_CREATED_AT))));
                Client client = new Client();
                client.setId(c.getInt(c.getColumnIndex(KEY_ORDER_CLIENT_COD)));
                SalesMan seller = new SalesMan();
                seller.setId(c.getInt(c.getColumnIndex(KEY_ORDER_SELLER_COD)));
                order.setSeller(seller);
                order.setClient(client);
                order.setItems(getItemsFromOrder(order.getId()));
                orders.add(order);
            } while (c.moveToNext());
        }

        return orders;
    }

    public List<Order> getOrdersFromARangeDate(Date fechaPedido){
        List<Order> orders = new ArrayList<>();
        String selectQuery = "SELECT  " +
                "O." + KEY_ORDER_ID + ", " +
                "O." + KEY_ORDER_DATE_AT + "," +
                "O." + KEY_ORDER_DELIVERY_AT + "," +
                "O." + KEY_ORDER_PAYMENT_TYPE + "," +
                "O." + KEY_ORDER_PAYMENT_VOUCHER_TYPE + "," +
                "C." + KEY_CLIENT_COD_CLIENT + "," +
                "C." + KEY_CLIENT_FIRST_NAME + "," +
                "C." + KEY_CLIENT_LAST_NAME + "," +
                "U." + KEY_USER_COD_SELLER + "," +
                "U." + KEY_USER_NAME +
                " FROM " + TABLE_ORDERS +
                " O INNER JOIN " + TABLE_CLIENTS  + " C ON O." + KEY_ORDER_CLIENT_COD + " =" +
                " C." + KEY_CLIENT_COD_CLIENT +
                " INNER JOIN " + TABLE_USERS  + " U ON O." + KEY_ORDER_SELLER_COD + " =" +
                " U." + KEY_USER_COD_SELLER +
                " WHERE O." + KEY_CREATED_AT + " BETWEEN " +
                "datetime(date('" + DateUtil.getFormatDate(fechaPedido) + "')||' 00:00:00') " +
                "AND datetime(date('" + DateUtil.getFormatDate(fechaPedido) + "')||' 23:59:59')";

        Log.i(TAG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Order order = new Order();
                order.setId(c.getInt(c.getColumnIndex(KEY_ORDER_ID)));
                order.setDateOrder(DateUtil.getDate(c.getString(
                        c.getColumnIndex(KEY_ORDER_DATE_AT))));
                order.setDateDelivery(DateUtil.getDate(c.getString(
                        c.getColumnIndex(KEY_ORDER_DELIVERY_AT))));
                order.setPaymentType(c.getInt(c.getColumnIndex(KEY_ORDER_PAYMENT_TYPE)));
                order.setPaymentVoucherType(c.getInt(c.getColumnIndex(KEY_ORDER_PAYMENT_VOUCHER_TYPE)));
                Client client = new Client();
                client.setCodClient(c.getInt(c.getColumnIndex(KEY_CLIENT_COD_CLIENT)));
                client.setFirstName(c.getString(c.getColumnIndex(KEY_CLIENT_FIRST_NAME)));
                client.setLastName(c.getString(c.getColumnIndex(KEY_CLIENT_LAST_NAME)));
                SalesMan seller = new SalesMan();
                seller.setCodSeller(c.getString(c.getColumnIndex(KEY_USER_COD_SELLER)));
                seller.setName(c.getString(c.getColumnIndex(KEY_USER_NAME)));
                order.setSeller(seller);
                order.setClient(client);
                order.setItems(getItemsFromOrder(order.getId()));

                orders.add(order);
            } while (c.moveToNext());
        }
        return orders;
    }

    public List<Item> getItemsFromOrder(int orderId) {
        List<Item> items = new ArrayList<>();

        String selectQuery = "SELECT  " +
                "O." + KEY_ORDER_ITEM_ID + ", " +
                "O." + KEY_ORDER_ITEM_ORDER_ID + ", " +
                "O." + KEY_ORDER_ITEM_QUANTITY + ", " +
                "O." + KEY_ORDER_ITEM_PRICE + "," +
                "O." + KEY_ORDER_ITEM_TYPE_PRICE + "," +
                "P." + KEY_PRODUCT_ID + "," +
                "P." + KEY_PRODUCT_COD + "," +
                "P." + KEY_PRODUCT_NAME + "," +
                "P." + KEY_PRODUCT_BOX_BY + "," +
                "P." + KEY_PRODUCT_TYPE_UNIT + "," +
                "O." + KEY_ORDER_ITEM_PRICE_LIST + "," +
                "O." + KEY_ORDER_ITEM_PRODUCT_COD_LEVEL + "," +
                "O." + KEY_ORDER_ITEM_PRODUCT_LEVEL_RANGE_FROM + "," +
                "O." + KEY_ORDER_ITEM_PRODUCT_LEVEL_RANGE_TO +
                " FROM " +
                TABLE_ORDER_ITEMS +
                " O INNER JOIN " + TABLE_PRODUCTS  + " P ON O." + KEY_ORDER_ITEM_COD_PRODUCT + " = " +
                "P." + KEY_PRODUCT_ID +
                " WHERE O." + KEY_ORDER_ITEM_ORDER_ID + " = ?";

        Log.i(TAG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, new String[]{
                String.valueOf(orderId)
        });

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Item item = new Item();
                item.setId(c.getInt(c.getColumnIndex(KEY_ORDER_ITEM_ID)));
                item.setOrderId(c.getInt(c.getColumnIndex(KEY_ORDER_ITEM_ORDER_ID)));
                item.setQuantity(c.getDouble(c.getColumnIndex(KEY_ORDER_ITEM_QUANTITY)));
                item.setPrice(c.getDouble(c.getColumnIndex(KEY_ORDER_ITEM_PRICE)));
                item.setTypePrice(c.getString(c.getColumnIndex(KEY_ORDER_ITEM_TYPE_PRICE)));
                Product product = new Product();
                product.setId(c.getInt(c.getColumnIndex(KEY_PRODUCT_ID)));
                product.setCodProduct(c.getString(c.getColumnIndex(KEY_PRODUCT_COD)));
                product.setName(c.getString(c.getColumnIndex(KEY_PRODUCT_NAME)));
                product.setBoxBy(c.getInt(c.getColumnIndex(KEY_PRODUCT_BOX_BY)));
                product.setTypeUnit(c.getString(c.getColumnIndex(KEY_PRODUCT_TYPE_UNIT)));
                item.setProduct(product);
                item.setPriceOfList(c.getInt(c.getColumnIndex(KEY_ORDER_ITEM_PRICE_LIST)));
                item.setLevel(c.getInt(c.getColumnIndex(KEY_ORDER_ITEM_PRODUCT_COD_LEVEL)));
                item.setLevelRangeFrom(c.getDouble(c.getColumnIndex(KEY_ORDER_ITEM_PRODUCT_LEVEL_RANGE_FROM)));
                item.setLevelRangeTo(c.getDouble(c.getColumnIndex(KEY_ORDER_ITEM_PRODUCT_LEVEL_RANGE_TO)));
                // adding to product list
                items.add(item);
            } while (c.moveToNext());
        }

        return items;

    }
}