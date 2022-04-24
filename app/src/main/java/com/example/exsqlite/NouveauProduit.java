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
                if (e1.getText().toString().isEmpty() || e2.getText().toString().isEmpty()
                        || e3.getText().toString().isEmpty() || e4.getText().toString().isEmpty())
                    Toast.makeText(NouveauProduit.this, "Remplissez tous les champs !", Toast.LENGTH_LONG).show();
                else {
                    MyDBProduit.Insert_Produit(db.getWritableDatabase(), new Produit(1, e1.getText().toString(), e2.getText().toString(), Double.valueOf(e3.getText().toString()), Double.valueOf(e4.getText().toString())));
                    Toast.makeText(NouveauProduit.this, "Ajout avec succes !", Toast.LENGTH_SHORT).show();
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
