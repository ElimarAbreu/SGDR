package com.example.cm_sgdr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.cm_sgdr.modelo.Pessoa;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_cadastrodespesa extends AppCompatActivity {

    private List<String> moradores = new ArrayList<String>();
    private Spinner spinner_morador;
    private String responsavel = "1111111";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cadastrodespesa);

        // Populando o spinner
        // Obtendo lista de moradores da republica

        Intent intent = getIntent();
        final String codigo = intent.getStringExtra("codigo_republica");

        Log.v("MTAa","Codigo");
        Log.v("MTAa",codigo);

        Log.v("MTAa","Cheguei!!!");


        DatabaseReference raiz = FirebaseDatabase.getInstance().getReference();
        Query query1 = raiz.child("Pessoa").orderByChild("cod_republica").equalTo(codigo);

        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                    Pessoa p = objSnapshot.getValue(Pessoa.class);
                    //Log.v("MTA1", p.getNome());
                    if(p.getCod_republica().equals(codigo)) {

                        moradores.add(p.getNome());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, moradores);
        //adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        spinner_morador = (Spinner) findViewById(R.id.spinner_cadastrodespesa_resp);
        spinner_morador.setAdapter(adapter);


        responsavel = moradores.get(1);
        spinner_morador.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                responsavel = moradores.get(i);
                Log.v("MTA1", moradores.get(i));
            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }
}
