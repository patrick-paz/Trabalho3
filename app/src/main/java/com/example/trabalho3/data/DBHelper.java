package com.example.trabalho3.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "produtosdb";
    public static final int DB_VERSION = 1;

    private static final String SQL_DROP = "DROP TABLE IF EXISTS " + ProdutosContract.TABLE_NAME;
    private static final String SQL_CREATE = String.format(
            "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "%s TEXT NOT NULL, %s DOUBLE NOT NULL)", ProdutosContract.TABLE_NAME, ProdutosContract.Columns._ID, ProdutosContract.Columns.NOME, ProdutosContract.Columns.VALOR);

    private static DBHelper instance;

    //singleton
    public static DBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DBHelper(context, DB_NAME, null, DB_VERSION);
        }
        return instance;
    }

    private DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_DROP);
        sqLiteDatabase.execSQL(SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        onCreate(sqLiteDatabase);
    }
}