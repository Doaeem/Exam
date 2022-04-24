package com.example.exsqlite;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListeProduits extends AppCompatActivity {

    ListView lst;
    MyDBProduit db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_produits);

        db = new MyDBProduit(this);
        lst = findViewById(R.id.lst);

        ArrayList<Produit> all = MyDBProduit.getListeProduits(db.getReadableDatabase());

        ArrayList<String> infos = new ArrayList<>();

        for(Produit p : all){
            infos.add(p.getIdProduit() + " - " + p.getLibelle());
        }

        ArrayAdapter<String> ad = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,infos);
        lst.setAdapter(ad);

    }
}
