package com.example.exsqlite;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NouveauProduit  extends AppCompatActivity {

    MyDBProduit db;
    EditText e1,e2,e3,e4;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nouveau_produit);

        e1 = findViewById(R.id.elib);
        e2 = findViewById(R.id.efamille);
        e3 = findViewById(R.id.eprxachat);
        e4 = findViewById(R.id.eprxvente);
        btn = findViewById(R.id.btnsave);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(e1.getText().toString().isEmpty()){
                    Toast.makeText(NouveauProduit.this, "Libelle vide", Toast.LENGTH_SHORT).show();
                    e1.requestFocus();
                    return;
                }

                if(e2.getText().toString().isEmpty()){
                    Toast.makeText(NouveauProduit.this, "Famille vide", Toast.LENGTH_SHORT).show();
                    e2.requestFocus();
                    return;
                }

                if(e3.getText().toString().isEmpty()){
                    Toast.makeText(NouveauProduit.this, "Prix achat vide", Toast.LENGTH_SHORT).show();
                    e3.requestFocus();
                    return;
                }

                if(e4.getText().toString().isEmpty()){
                    Toast.makeText(NouveauProduit.this, "Prix vente vide", Toast.LENGTH_SHORT).show();
                    e4.requestFocus();
                    return;
                }
                String lib = e1.getText().toString();
                String fam = e2.getText().toString();
                double achat =Double.parseDouble(e3.getText().toString());
                double vente =Double.parseDouble(e4.getText().toString());

                Produit p = new Produit(1,lib,fam,achat,vente);

                if(MyDBProduit.Insert_Produit(db.getWritableDatabase(),p)!=-1) {
                        Toast.makeText(NouveauProduit.this, "Ajout avec succes !", Toast.LENGTH_SHORT).show();
                        e1.getText().clear();
                        e2.getText().clear();
                        e3.getText().clear();
                        e4.getText().clear();
                        e1.requestFocus();
                }
                else
                    Toast.makeText(NouveauProduit.this, "Ajout echoue !", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
