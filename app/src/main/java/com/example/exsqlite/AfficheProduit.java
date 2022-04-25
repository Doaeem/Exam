package com.example.exsqlite;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AfficheProduit  extends AppCompatActivity {

    MyDBProduit db;
    Spinner spin;
    ArrayList<Produit> prds;
    TextView t1,t2,t3,t4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affiche_produit);

        spin=findViewById(R.id.spin);
        t1=findViewById(R.id.libelle);
        t2=findViewById(R.id.famille);
        t3=findViewById(R.id.prixa);
        t3=findViewById(R.id.prixv);

        db=new MyDBProduit(this);

        prds=MyDBProduit.getListeProduits(db.getReadableDatabase());

        ArrayList<String> lstPrds=new ArrayList<>();
        for(Produit p: prds){
            lstPrds.add(String.format("%d - %s",p.getIdProduit(),p.getLibelle()));
        }
        ArrayAdapter<String> ad=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,lstPrds);
        spin.setAdapter(ad);

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Produit p= prds.get(i);
                t1.setText("Libelle : "+p.getLibelle());
                t2.setText("Famille : "+p.getFamille());
                t3.setText(String.format("Prix Achat :",p.getPrixAchat()));
                t4.setText(String.format("Prix Vente :",p.getPrixVente()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
