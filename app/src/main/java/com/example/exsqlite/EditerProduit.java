package com.example.exsqlite;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class EditerProduit  extends AppCompatActivity {

    MyDBProduit db;
    Button btn;
    Spinner spin;
    EditText e1,e2,e3,e4;
    ArrayList<Produit> prds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editer_produit);

        btn = findViewById(R.id.edit);
        e1 = findViewById(R.id.lib);
        e2 = findViewById(R.id.fam);
        e3 = findViewById(R.id.pra);
        e4 = findViewById(R.id.prv);
        spin = findViewById(R.id.spin);

        db = new MyDBProduit(this);
        prds = MyDBProduit.getListeProduits(db.getReadableDatabase());
        ArrayList<String> LstPrds = new ArrayList<>();
        for (Produit p : prds) {
            LstPrds.add(p.getIdProduit() + " - " + p.getLibelle());
        }
        ArrayAdapter<String> ad = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, LstPrds);
        spin.setAdapter(ad);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(e1.getText().toString().isEmpty() || e2.getText().toString().isEmpty()
                        || e3.getText().toString().isEmpty() || e4.getText().toString().isEmpty())
                    Toast.makeText(EditerProduit.this, "Erreur !", Toast.LENGTH_SHORT).show();
                else {
                    Produit p =prds.get(spin.getSelectedItemPosition());
                    p.setLibelle(e1.getText().toString());
                    p.setFamille(e2.getText().toString());
                    p.setPrixAchat(Double.valueOf(e3.getText().toString()));
                    p.setPrixVente(Double.valueOf(e4.getText().toString()));
                    MyDBProduit.Update_Produit(db.getWritableDatabase(),p);
                    Toast.makeText(EditerProduit.this, "Edit avec succes !", Toast.LENGTH_SHORT).show();
                    e1.getText().clear();
                    e2.getText().clear();
                    e3.getText().clear();
                    e4.getText().clear();
                    e1.requestFocus();
                }
            }
        });
    }
}
