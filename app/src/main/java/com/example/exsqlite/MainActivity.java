package com.example.exsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MyDBProduit db;
    Button b1,b2,b3,b4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new MyDBProduit(this);
        ArrayList<Produit> prds = new ArrayList<>();
        prds.add(new Produit(1,"Lait","Laiterie",3.00,3.50));
        prds.add(new Produit(2,"Fromage Rouge","Fromagerie",95.00,110.00));
        prds.add(new Produit(3,"Rafaello","Chocolat",60.00,110.00));
        prds.add(new Produit(4,"Coca Cola","Boissons",4.00,4.50));
        prds.add(new Produit(5,"Pain","Boulangerie",2.00,2.50));

        for(Produit p : prds)
            MyDBProduit.Insert_Produit(db.getWritableDatabase(),p);

    }
    public void acceder(View v){
        Intent i=null;
        switch (v.getId()){
            case R.id.btn1:
                i=new Intent(this,ListeProduits.class);
                break;
            case R.id.btn2:
                i=new Intent(this,AfficheProduit.class);
                break;
            case R.id.btn3:
                i=new Intent(this,NouveauProduit.class);
                break;
            case R.id.btn4:
                i=new Intent(this,EditerProduit.class);
                break;
        }
        startActivity(i);
    }
}