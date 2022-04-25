package com.example.exsqlite;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class EditerProduit  extends AppCompatActivity {

    MyDBProduit db;
    Button btn1,btn2;
    Spinner spin;
    EditText e1,e2,e3,e4;
    ArrayList<Produit> prds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editer_produit);

        btn1 = findViewById(R.id.btnupdate);
        btn2 = findViewById(R.id.btndelete);
        e1 = findViewById(R.id.lib);
        e2 = findViewById(R.id.fam);
        e3 = findViewById(R.id.pra);
        e4 = findViewById(R.id.prv);
        spin = findViewById(R.id.spin);

        db = new MyDBProduit(this);
        prds = MyDBProduit.getListeProduits(db.getReadableDatabase());
        ArrayList<String> LstPrds = new ArrayList<>();
        for (Produit p : prds) {
            LstPrds.add(String.format("%d - %s",p.getIdProduit(),p.getLibelle()));
        }
        ArrayAdapter<String> ad = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, LstPrds);
        spin.setAdapter(ad);

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Produit p = prds.get(i);

                e1.setText(p.getLibelle());
                e2.setText(p.getFamille());
                e3.setText(String.valueOf(p.getPrixAchat()));
                e4.setText(String.valueOf(p.getPrixVente()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(EditerProduit.this);
                alert.setTitle("Mise a jour du produit !");
                alert.setMessage("Voulez-vous vraiment modifier ce produit ?");

                alert.setPositiveButton("Modifier", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String lib = e1.getText().toString();
                        String fam = e2.getText().toString();
                        double prixachat = Double.valueOf(e3.getText().toString());
                        double prixvente = Double.valueOf(e4.getText().toString());
                        int pos = spin.getSelectedItemPosition();
                        int id = prds.get(pos).getIdProduit();

                        Produit p = new Produit(id, lib, fam, prixachat, prixvente);

                        if (MyDBProduit.Update_Produit(db.getWritableDatabase(), p) != -1)
                            Toast.makeText(EditerProduit.this, "Modification effectue", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(EditerProduit.this, "Modification echoue", Toast.LENGTH_SHORT).show();
                    }
                });
                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(EditerProduit.this, "Modification Annulee", Toast.LENGTH_SHORT).show();
                    }
                });
                alert.show();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(EditerProduit.this);
                alert.setTitle("Suppression du produit !");
                alert.setMessage("Voulez-vous vraiment supprimer ce produit ?");

                alert.setPositiveButton("Supprimer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int pos = spin.getSelectedItemPosition();
                        int id = prds.get(pos).getIdProduit();

                        if(MyDBProduit.Delete_Produit(db.getWritableDatabase(),id)!=-1)
                            Toast.makeText(EditerProduit.this, "Suppresion effectue",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(EditerProduit.this, "Suppresion echoue",Toast.LENGTH_LONG).show();
                    }
                });
                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(EditerProduit.this,"Suppression annule",Toast.LENGTH_LONG).show();
                    }
                });
                alert.show();
            }
        });
    }
}
