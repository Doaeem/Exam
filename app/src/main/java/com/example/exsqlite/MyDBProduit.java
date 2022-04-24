package com.example.exsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyDBProduit extends SQLiteOpenHelper {

    public static String DB_NAME = "DBproduit.db";
    public static String TABLE_NAME = "Produit";
    public static String COL1 = "IDProduit";
    public static String COL2 = "LIBELLE";
    public static String COL3 = "FAMILLE";
    public static String COL4 = "PRIXACHAT";
    public static String COL5 = "PRIXVENTE";

    public MyDBProduit(Context c){
        super(c,DB_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table " + TABLE_NAME + " ("+ COL1 +" integer primary key autoincrement, "+COL2+" TEXT,"+COL3+" TEXT,"+COL4+" DOUBLE,"+COL5+" DOUBLE )";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE " + TABLE_NAME;
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

    public static long Insert_Produit(SQLiteDatabase sqLiteDatabase,Produit p){
        ContentValues content = new ContentValues();
        content.put(COL2, p.getLibelle());
        content.put(COL3, p.getFamille());
        content.put(COL4, p.getPrixAchat());
        content.put(COL5, p.getPrixVente());
        return sqLiteDatabase.insert(TABLE_NAME,null,content);
    }

    public static long Update_Produit(SQLiteDatabase sqLiteDatabase,Produit p){
        ContentValues content = new ContentValues();
        content.put(COL2, p.getLibelle());
        content.put(COL3, p.getFamille());
        content.put(COL4, p.getPrixAchat());
        content.put(COL5, p.getPrixVente());
        return sqLiteDatabase.update(TABLE_NAME,content,"IDProduit =" + p.getIdProduit(),null);
    }

    public static long Delete_Produit(SQLiteDatabase sqLiteDatabase,int idPrd){
        return sqLiteDatabase.delete(TABLE_NAME,"IDProduit =" + idPrd,null);
    }

    public static ArrayList<Produit> getListeProduits(SQLiteDatabase sqLiteDatabase){
        ArrayList<Produit> prdts = new ArrayList<>();

        Cursor cur = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME,null);

        while(cur.moveToNext()){
            Produit p = new Produit();

            p.setIdProduit(cur.getInt(0));
            p.setLibelle(cur.getString(1));
            p.setFamille(cur.getString(2));
            p.setPrixAchat(cur.getDouble(3));
            p.setPrixVente(cur.getDouble(4));
            prdts.add(p);
        }
        return prdts;
    }

    public static Produit getProduit(SQLiteDatabase sqLiteDatabase, int id){
        Produit prdts = null;

        Cursor cur = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME + " WHERE IDPRODUIT = " + id,null);

        if(cur.moveToNext()){
            prdts.setIdProduit(cur.getInt(0));
            prdts.setLibelle(cur.getString(1));
            prdts.setFamille(cur.getString(2));
            prdts.setPrixAchat(cur.getDouble(3));
            prdts.setPrixVente(cur.getDouble(4));
        }
        return prdts;
    }

}
